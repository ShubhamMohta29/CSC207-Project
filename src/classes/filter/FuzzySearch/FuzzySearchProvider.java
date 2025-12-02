package classes.filter.FuzzySearch;

public interface FuzzySearchProvider {
    /**
     * For fuzzy search.
     * @param input entered name
     * @return suggestion
     */
    String getSuggestion(String input);
}

