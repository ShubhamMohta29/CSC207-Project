/**
 * AnimalNamesProvider :LLM - OpenRouterAPI
 */
package Classes.Filter;
import Config.ProjectConfig;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class AnimalNamesProvider implements AnimalNamesProviderI {

    //field declarations
    private static final String API_URL = ProjectConfig.OPEN_ROUTER_API_URL;
    private static final String apiKey = ProjectConfig.getOpenRouterApiKey();
    private final HttpClient client;

    /*
     * Constructor
     */
    public AnimalNamesProvider() {
        this.client = HttpClient.newHttpClient();

    }


        /*
     * Function call to get the potential candidates satisfying the user's applied filters
     */
    @Override
    public List<String> getCandidateNames(FilterInput request) {
        List<String> candidates = new ArrayList<>();
        System.out.println("im in the animal names provider now");

        String prompt = buildPrompt(request);
        System.out.println("this is the prompt im sending to the OpenRouter API:" + prompt);

        JSONObject payload = buildPayload(prompt, "openai/gpt-4o", 500);

        String responseBody = sendRequest(payload);
        if (responseBody != null) {
            candidates.addAll(parseCandidateNames(responseBody));
        }
    System.out.println("API return " +candidates);
        return candidates;
    }

    /*
     * Converts the filter request into a text prompt for the LLM.
     */
    private String buildPrompt(FilterInput request) {
        StringBuilder prompt = new StringBuilder(
                "List 10-15 common animal names that satisfy the user's selected filters. " +
                        "Rules:\n" +
                        "- Each animal must match at least one option from every category specified (AND logic across categories).\n" +
                        "- Within each category (Groups, Diets, Locations), include at least one matching option (OR logic within category).\n" +
                        "- Respect lifespan constraints: include only animals whose lifespan is within the given min and max, if provided.\n" +
                        "- Prefer single-word names and widely known species.\n" +
                        "- Return exactly 10-15 unique animal names, as a comma-separated list. Do not include extra text, numbers, explanations, or newlines.\n" +
                        "- If fewer than 10 animals satisfy all criteria, return the closest matches instead, still comma-separated.\n"
        );

        if (request.getGroups() != null && !request.getGroups().isEmpty()) {
            prompt.append("Groups: ").append(String.join(", ", request.getGroups())).append("\n");
        }
        if (request.getDiets() != null && !request.getDiets().isEmpty()) {
            prompt.append("Diets: ").append(String.join(", ", request.getDiets())).append("\n");
        }
        if (request.getLocations() != null && !request.getLocations().isEmpty()) {
            prompt.append("Locations: ").append(String.join(", ", request.getLocations())).append("\n");
        }
        if (request.getMinLifespan() != null) {
            prompt.append("Minimum lifespan: ").append(request.getMinLifespan()).append("\n");
        }
        if (request.getMaxLifespan() != null) {
            prompt.append("Maximum lifespan: ").append(request.getMaxLifespan()).append("\n");
        }

        return prompt.toString();
    }


    /*
     * Build JSON payload for OpenRouter API
     */
    private JSONObject buildPayload(String prompt, String model, int maxTokens) {
        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", prompt);

        JSONArray messages = new JSONArray().put(userMessage);

        return new JSONObject()
                .put("model", model)
                .put("messages", messages)
                .put("max_completion_tokens", maxTokens)
                .put("stream", false);
    }

    /*
     * Send the HTTP request and return response body as string
     */
    private String sendRequest(JSONObject payload) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("OpenRouter API error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Parse the JSON response for candidate names
     */
    private List<String> parseCandidateNames(String responseBody) {
        List<String> candidates = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(responseBody);
            JSONArray choices = json.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject choice0 = choices.getJSONObject(0);
                String content;
                if (choice0.has("message")) {
                    content = choice0.getJSONObject("message").getString("content");
                } else {
                    content = choice0.optString("text", "");
                }
                String[] names = content.split("[,\\n]");
                for (String name : names) {
                    name = name.trim();
                    if (!name.isEmpty()) {
                        candidates.add(name);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing candidate names: " + e.getMessage());
        }
        return candidates;
    }
}
