package classes.retrieveInfo;

public interface searchAnimalsInputBoundary {

    /**
     * Executes a search for animals based on the given input data.
     *
     * @param requestModel the input data containing search parameters
     */
    void execute(searchAnimalsInputData requestModel);
}
