package classes.retrieveInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;

import config.projectConfig;

public class APIAnimalDataSource implements animalDataSource {

    private static final String PARENS = "[]";

    private final String apiUrl;
    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();
    private String lastResponse = "[]";

    public APIAnimalDataSource() {
        this.apiUrl = projectConfig.NINJA_API_URL;
        this.apiKey = projectConfig.getNinjaApiKey();
    }

    @Override
    public String getAnimalJson(String animalQuery) throws IOException, InterruptedException {
        String result = null;
        final String q = animalQuery.replace(" ", "%20");
        final HttpRequest request = HttpRequest.newBuilder(URI.create(apiUrl + q))
                .header("X-Api-Key", apiKey)
                .GET()
                .build();
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == animalConstants.HTTP_OK) {
            lastResponse = response.body();
            if (lastResponse != null && !lastResponse.trim().isEmpty() && !lastResponse.equals(PARENS)) {
                result = lastResponse;
            }
        }
        else {
            lastResponse = PARENS;
        }
        return result;
    }

    @Override
    public int lastNumResults() {
        int result = 0;
        if (lastResponse != null && !lastResponse.trim().isEmpty()) {
            try {
                final JSONArray arr = new JSONArray(lastResponse);
                result = arr.length();
            }
            catch (org.json.JSONException exception) {
                // keep result as 0 if parsing fails
            }
        }
        return result;
    }
}
