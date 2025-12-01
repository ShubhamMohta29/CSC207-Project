package apppkg.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;

import classes.Filter.FuzzySearch.AnimalFuzzySearch;
import classes.Filter.FuzzySearch.FuzzySearchProvider;
import classes.retrieveInfo.Animal;
import classes.retrieveInfo.AnimalFactory;
import classes.retrieveInfo.ApiClass;

/**
 * Controller responsible for searching animals via the API,
 * handling single/multiple results and fuzzy suggestions.
 */
public class SearchController {

    private final ApiClass api;
    private final FuzzySearchProvider fuzzy;
    private final AnimalFactory factory;

    public SearchController() {
        this.api = new ApiClass();
        this.fuzzy = new AnimalFuzzySearch();
        this.factory = new AnimalFactory();
    }

    public SearchController(ApiClass api, FuzzySearchProvider fuzzy, AnimalFactory factory) {
        if (api == null) {
            this.api = new ApiClass();
        }
        else {
            this.api = api;
        }
        if (fuzzy == null) {
            this.fuzzy = new AnimalFuzzySearch();
        }
        else {
            this.fuzzy = fuzzy;
        }
        if (factory == null) {
            this.factory = new AnimalFactory();
        }
        else {
            this.factory = factory;
        }
    }

    /**
     * Searches for animals using the API.
     *
     * @param rawQuery user input
     * @return SearchResult containing success, message, animals, and suggestion
     */
    public SearchResult search(final String rawQuery) {

        final String query = normalizeQuery(rawQuery);
        final SearchResult result;

        if (query.isEmpty()) {
            result = noQueryResult();
        }
        else {
            final String apiQuery = encodeSpaces(query);
            final String jsonData = api.getAnimalData(apiQuery);

            if (jsonData == null || jsonData.trim().isEmpty()) {
                result = handleNoData(query);
            }
            else {
                result = parseJsonResults(jsonData);
            }
        }

        return result;
    }

    private String normalizeQuery(final String input) {
        String result = "";
        if (input != null) {
            result = input.toLowerCase().trim();
        }
        return result;
    }

    private String encodeSpaces(final String input) {
        String result = input;
        if (input.contains(" ")) {
            result = input.replace(" ", "%20");
        }
        return result;
    }

    private SearchResult noQueryResult() {
        return new SearchResult(false, "Please select an animal name.", new Animal[0], null);
    }

    private SearchResult handleNoData(final String query) {
        final SearchResult result;
        final String suggestion = fuzzy.getSuggestion(query);
        if (suggestion != null && !suggestion.isEmpty()) {
            final String htmlText = "<html>Animal not found. Did you mean: <a href=''>" + suggestion + "</a>?</html>";
            result = new SearchResult(false, htmlText, new Animal[0], suggestion);
        }
        else {
            result = new SearchResult(false, "Animal '" + query + "' not found.", new Animal[0], null);
        }
        return result;
    }

    private SearchResult parseJsonResults(final String jsonData) {
        final SearchResult result;
        final JSONArray array = new JSONArray(jsonData);
        final int count = array.length();

        if (count == 0) {
            result = new SearchResult(false, "Please double check animal name.", new Animal[0], null);
        }
        else if (count == 1) {
            final Animal singleAnimal = factory.fromJsonArrayString(jsonData);
            result = new SearchResult(true, null, new Animal[]{singleAnimal}, null);
        }
        else {
            final Animal[] animals = new Animal[count];
            for (int i = 0; i < count; i++) {
                final JSONObject obj = array.getJSONObject(i);
                final JSONArray singleArray = new JSONArray();
                singleArray.put(obj);
                animals[i] = factory.fromJsonArrayString(singleArray.toString());
            }
            result = new SearchResult(true, null, animals, null);
        }

        return result;
    }
}
