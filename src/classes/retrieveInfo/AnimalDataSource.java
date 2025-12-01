package classes.retrieveInfo;

import java.io.IOException;

public interface AnimalDataSource {

    /**
     * Fetches the JSON representation of an animal from the API.
     *
     * @param animalQuery the query string for the animal
     * @return the JSON string representing the animal
     * @throws IOException if there is a network or IO error
     * @throws InterruptedException if the request is interrupted
     */
    String getAnimalJson(String animalQuery) throws IOException, InterruptedException;

    /**
     * Returns the number of results from the last API response.
     *
     * @return the number of results from the last response
     */
    int lastNumResults();
}
