package classes.retrieveInfo;

public interface SearchAnimalsInputBoundary {

    /**
     * Executes a search for animals based on the given input data.
     *
     * @param requestModel the input data containing search parameters
     */
    void execute(SearchAnimalsInputData requestModel);
}
