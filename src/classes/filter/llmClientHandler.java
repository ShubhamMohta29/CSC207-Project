package classes.filter;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import config.projectConfig;

public class llmClientHandler {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String apiUrl = projectConfig.OPEN_ROUTER_API_URL;
    private final String apiKey = projectConfig.getOpenRouterApiKey();
    private final String model = filterConstants.MODEL;
    private final int maxTokens = filterConstants.MAX_TOKENS;

    /**
     * LLM handler.
     * @param prompt to send to api
     * @return creates the api query
     */
    public String query(String prompt) {

        // Build JSON payload for OpenRouter API
        final JSONObject payload = new JSONObject()
                .put("model", model)
                .put("messages", new JSONArray()
                        .put(new JSONObject()
                                .put("role", "user")
                                .put("content", prompt)))
                .put("max_completion_tokens", maxTokens);

        // Send the HTTP request and return response body as string
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
