package classes.retrieveInfo;

public class SearchAnimalsInputData {

    private final String query;

    /**
     * Constructs a new SearchAnimalsInputData with the given query.
     *
     * @param query the search query string
     */
    public SearchAnimalsInputData(String query) {
        this.query = query;
    }

    /**
     * Returns the search query.
     *
     * @return the query string
     */
    public String getQuery() {
        return query;
    }
}
