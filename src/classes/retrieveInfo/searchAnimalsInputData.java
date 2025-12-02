package classes.retrieveInfo;

public class searchAnimalsInputData {

    private final String query;

    /**
     * Constructs a new SearchAnimalsInputData with the given query.
     *
     * @param query the search query string
     */
    public searchAnimalsInputData(String query) {
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
