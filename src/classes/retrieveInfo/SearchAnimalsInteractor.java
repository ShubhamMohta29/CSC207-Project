package classes.retrieveInfo;

import java.io.IOException;

import classes.Filter.FuzzySearch.FuzzySearchProvider;

/**
 * Interactor responsible for searching animals via a data source,
 * handling multiple results, and providing suggestions if not found.
 */
public class SearchAnimalsInteractor implements SearchAnimalsInputBoundary {

    private final AnimalDataSource dataSource;
    private final SearchAnimalsOutputBoundary presenter;
    private final AnimalFactory factory;
    private final FuzzySearchProvider fuzzyProvider;

    public SearchAnimalsInteractor(
            final AnimalDataSource dataSource,
            final SearchAnimalsOutputBoundary presenter,
            final AnimalFactory factory,
            final FuzzySearchProvider fuzzyProvider) {
        this.dataSource = dataSource;
        this.presenter = presenter;
        this.factory = factory;
        this.fuzzyProvider = fuzzyProvider;
    }

    @Override
    public void execute(final SearchAnimalsInputData requestModel) {
        final String query = normalizeQuery(requestModel.getQuery());
        final SearchAnimalsOutputData result;

        if (query.isEmpty()) {
            result = emptyQueryResult();
        }
        else {
            result = searchAnimals(query);
        }

        presenter.present(result);
    }

    // ---------------- Helper Methods ----------------

    /**
     * Normalizes a raw query: trims, lowercases, or returns empty string if null.
     *
     * @param rawQuery the raw query string provided by the user
     * @return normalized query string
     */
    private String normalizeQuery(final String rawQuery) {
        final String normalized;
        if (rawQuery == null) {
            normalized = "";
        }
        else {
            normalized = rawQuery.trim().toLowerCase();
        }
        return normalized;
    }

    /**
     * Returns a result indicating that the query was empty.
     *
     * @return SearchAnimalsOutputData for empty query
     */
    private SearchAnimalsOutputData emptyQueryResult() {
        return new SearchAnimalsOutputData(false,
                "Please enter an animal name.",
                new Animal[0],
                null);
    }

    /**
     * Handles the full search process: fetch JSON, parse results, handle suggestions.
     *
     * @param query the normalized query string
     * @return SearchAnimalsOutputData containing the search result
     */
    private SearchAnimalsOutputData searchAnimals(final String query) {
        SearchAnimalsOutputData result;
        try {
            final String json = dataSource.getAnimalJson(query);
            result = processJsonResult(query, json);
        }
        catch (IOException | InterruptedException networkEx) {
            result = networkErrorResult(networkEx);
        }
        return result;
    }

    /**
     * Processes the JSON response from the data source.
     *
     * @param query the original query string
     * @param json the JSON returned by the data source
     * @return SearchAnimalsOutputData representing the result
     */
    private SearchAnimalsOutputData processJsonResult(final String query, final String json) {
        final SearchAnimalsOutputData result;
        if (json == null) {
            result = handleNotFound(query);
        }
        else {
            final int numResults = dataSource.lastNumResults();
            if (numResults == 0) {
                result = noResults();
            }
            else if (numResults == 1) {
                result = singleResult(json);
            }
            else {
                result = multipleResults(json);
            }
        }
        return result;
    }

    /**
     * Returns a result for network errors.
     *
     * @param networkEx the network exception
     * @return SearchAnimalsOutputData indicating network error
     */
    private SearchAnimalsOutputData networkErrorResult(final Exception networkEx) {
        return new SearchAnimalsOutputData(false,
                "Network error: " + networkEx.getMessage(),
                new Animal[0],
                null);
    }

    /**
     * Returns a result for a query that produced no JSON.
     *
     * @param query the original query string
     * @return SearchAnimalsOutputData indicating not found
     */
    private SearchAnimalsOutputData handleNotFound(final String query) {
        final String suggestion;
        if (fuzzyProvider == null) {
            suggestion = null;
        }
        else {
            suggestion = fuzzyProvider.getSuggestion(query);
        }

        final String message;
        if (suggestion == null) {
            message = "Animal '" + query + "' not found.";
        }
        else {
            message = "Animal not found. Suggestion: " + suggestion;
        }

        return new SearchAnimalsOutputData(false, message, new Animal[0], suggestion);
    }

    /**
     * Returns a result indicating no animals were found.
     *
     * @return SearchAnimalsOutputData for zero results
     */
    private SearchAnimalsOutputData noResults() {
        return new SearchAnimalsOutputData(false, "No results found.", new Animal[0], null);
    }

    /**
     * Returns a result when exactly one animal is found.
     *
     * @param json the JSON string for one animal
     * @return SearchAnimalsOutputData containing the single animal
     */
    private SearchAnimalsOutputData singleResult(final String json) {
        final Animal a = factory.fromJsonArrayString(json);
        return new SearchAnimalsOutputData(true, null, new Animal[]{a}, null);
    }

    /**
     * Returns a result when multiple animals are found.
     *
     * @param json the JSON string containing multiple animals
     * @return SearchAnimalsOutputData containing all animals
     */
    private SearchAnimalsOutputData multipleResults(final String json) {
        final org.json.JSONArray arr = new org.json.JSONArray(json);
        final Animal[] animals = new Animal[arr.length()];

        for (int i = 0; i < arr.length(); i++) {
            final org.json.JSONArray singleArray = new org.json.JSONArray();
            singleArray.put(arr.getJSONObject(i));
            animals[i] = factory.fromJsonArrayString(singleArray.toString());
        }

        return new SearchAnimalsOutputData(true, null, animals, null);
    }
}
