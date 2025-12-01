package classes.retrieveInfo;

public interface SearchAnimalsOutputBoundary {

    /**
     * Presents the result of a search operation to the output layer.
     *
     * @param responseModel the output data containing the search results,
     *                      status, and any suggestions
     */
    void present(SearchAnimalsOutputData responseModel);

}
