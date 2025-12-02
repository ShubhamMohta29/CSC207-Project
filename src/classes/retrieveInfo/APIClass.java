package Classes.retrieveInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import Config.ProjectConfig;

public class APIClass {

    public static final String API_URL = ProjectConfig.NINJA_API_URL;
    private static final String API_KEY = ProjectConfig.getNinjaApiKey();

    private List<String> searchedAnimals;
    private String responseBody;

    public APIClass() {
        searchedAnimals = new ArrayList<>();
        responseBody = "";
    }

    /**
     * Fetches animal data from the API for the given animal name.
     *
     * @param animalName the name of the animal to search
     * @return the JSON response as a String, or null if empty or on error
     */
    public String getAnimalData(final String animalName) {
        String responseBodyLocal = null;
        final boolean isValidName = animalName != null && !animalName.isBlank();

        if (isValidName) {
            final String encodedName = animalName.replace(" ", "%20");

            try {
                final HttpClient client = HttpClient.newHttpClient();
                final HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL + encodedName))
                        .header("X-Api-Key", API_KEY)
                        .GET()
                        .build();

                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                final int successStatus = 200;
                if (response.statusCode() == successStatus) {
                    responseBodyLocal = response.body();

                    final String emptyJsonArray = "[]";
                    if (responseBodyLocal == null || responseBodyLocal.isBlank()
                            || emptyJsonArray.equals(responseBodyLocal)) {
                        System.err.println("API returned empty data for: " + animalName);
                        responseBodyLocal = null;
                    }
                    else {
                        searchedAnimals.add(responseBodyLocal);
                        System.out.println("Received data from API for: " + animalName + " -> " + responseBodyLocal);
                    }
                }
                else {
                    System.out.println("API Error: " + response.statusCode() + " for: " + animalName);
                    responseBodyLocal = null;
                }

            }
            catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return responseBodyLocal;
    }

    /**
     * Returns the number of animal results in the current response body.
     *
     * @return the number of results, or 0 if the response body is null or empty
     */
    public int numResults() {
        int result = 0;

        if (responseBody != null && !responseBody.isBlank()) {
            final JSONArray animalsArray = new JSONArray(responseBody);
            result = animalsArray.length();
        }

        return result;
    }

}
