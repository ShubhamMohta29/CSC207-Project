package classes.retrieveInfo;

import java.io.IOException;

import classes.filter.FuzzySearch.FuzzySearchProvider;

/**
 * Interactor responsible for searching animals via a data source,
 * handling multiple results, and providing suggestions if not found.
 */
public class searchAnimalsInteractor implements searchAnimalsInputBoundary {

    private final animalDataSource dataSource;
    private final searchAnimalsOutputBoundary presenter;
    private final animalFactory factory;
    private final FuzzySearchProvider fuzzyProvider;

    public searchAnimalsInteractor(
            final animalDataSource dataSource,
            final searchAnimalsOutputBoundary presenter,
            final animalFactory factory,
            final FuzzySearchProvider fuzzyProvider) {
        this.dataSource = dataSource;
        this.presenter = presenter;
        this.factory = factory;
        this.fuzzyProvider = fuzzyProvider;
    }

    @Override
    public void execute(final searchAnimalsInputData requestModel) {
        final String query = normalizeQuery(requestModel.getQuery());
        final searchAnimalsOutputData result;

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
    private searchAnimalsOutputData emptyQueryResult() {
        return new searchAnimalsOutputData(false,
                "Please enter an animal name.",
                new animal[0],
                null);
    }

    /**
     * Handles the full search process: fetch JSON, parse results, handle suggestions.
     *
     * @param query the normalized query string
     * @return SearchAnimalsOutputData containing the search result
     */
    private searchAnimalsOutputData searchAnimals(final String query) {
        searchAnimalsOutputData result;
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
    private searchAnimalsOutputData processJsonResult(final String query, final String json) {
        final searchAnimalsOutputData result;
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
    private searchAnimalsOutputData networkErrorResult(final Exception networkEx) {
        return new searchAnimalsOutputData(false,
                "Network error: " + networkEx.getMessage(),
                new animal[0],
                null);
    }

    /**
     * Returns a result for a query that produced no JSON.
     *
     * @param query the original query string
     * @return SearchAnimalsOutputData indicating not found
     */
    private searchAnimalsOutputData handleNotFound(final String query) {
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

        return new searchAnimalsOutputData(false, message, new animal[0], suggestion);
    }

    /**
     * Returns a result indicating no animals were found.
     *
     * @return SearchAnimalsOutputData for zero results
     */
    private searchAnimalsOutputData noResults() {
        return new searchAnimalsOutputData(false, "No results found.", new animal[0], null);
    }

    /**
     * Returns a result when exactly one animal is found.
     *
     * @param json the JSON string for one animal
     * @return SearchAnimalsOutputData containing the single animal
     */
    private searchAnimalsOutputData singleResult(final String json) {
        final animal a = factory.fromJsonArrayString(json);
        return new searchAnimalsOutputData(true, null, new animal[]{a}, null);
    }

    /**
     * Returns a result when multiple animals are found.
     *
     * @param json the JSON string containing multiple animals
     * @return SearchAnimalsOutputData containing all animals
     */
    private searchAnimalsOutputData multipleResults(final String json) {
        final org.json.JSONArray arr = new org.json.JSONArray(json);
        final animal[] animals = new animal[arr.length()];

        for (int i = 0; i < arr.length(); i++) {
            final org.json.JSONArray singleArray = new org.json.JSONArray();
            singleArray.put(arr.getJSONObject(i));
            animals[i] = factory.fromJsonArrayString(singleArray.toString());
        }

        return new searchAnimalsOutputData(true, null, animals, null);
    }
}
