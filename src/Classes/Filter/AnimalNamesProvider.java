/**
 * AnimalNamesProvider :LLM - OpenRouterAPI
  /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

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
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String apiKey;
    private final HttpClient client;

    /*
     * Constructor
     */
    public AnimalNamesProvider(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    /*
     * Function call to get the potential candidates satisfying the user's applied filters
     */
    @Override
    public List<String> getCandidateNames(FilterInput request) {
        List<String> candidates = new ArrayList<>();
        System.out.println("im in the animal names provider now");

        // 1. Build the prompt from the filter request
        String prompt = buildPrompt(request);
        System.out.println("this is the prompt im sending to the OpenRouter API:" + prompt);
        // 2. Create JSON payload (the data inside the Http Request)
        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", prompt);

        JSONArray messages = new JSONArray().put(userMessage);

        JSONObject payload = new JSONObject()
                .put("model", "openai/gpt-4o")  //selected model
                .put("messages", messages)
                .put("max_completion_tokens", 500)
                .put("stream", false);

        // 3. Build the HTTP request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            // 4. Send request
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // 5. Parse the JSON response
                JSONObject json = new JSONObject(response.body());
                JSONArray choices = json.getJSONArray("choices");

                if (choices.length() > 0) {
                    JSONObject choice0 = choices.getJSONObject(0);
                    String content;

                    if (choice0.has("message")) {
                        content = choice0.getJSONObject("message").getString("content");
                    } else {
                        content = choice0.optString("text", "");
                    }

                    // 6. Split content into candidate names
                    String[] names = content.split("[,\\n]");
                    for (String name : names) {
                        name = name.trim();
                        if (!name.isEmpty()) {
                            candidates.add(name);
                        }
                    }
                }
            } else {
                System.err.println("OpenRouter API error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return candidates;
    }

    /*
     * Converts the filter request into a text prompt for the LLM.
     */
    private String buildPrompt(FilterInput request) {
        StringBuilder prompt = new StringBuilder("List animal common names that match the following criteria exactly. " +
                "The animals must satisfy at least one criteria from each of the categories:\n");

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

        prompt.append("Return only 10-15 common names as a comma-separated list. ensure they are common names and preferably, more common animals as well. Also, try to provide more one-word named animals. No extra text.");
        return prompt.toString();
    }

    /*
     * Function call to mimic fuzzy search suggestion? Bonus, rather
     */
    @Override
    public String fuzzySuggestion(String animalName) {
        System.out.println("Getting fuzzy suggestion for: " + animalName);

        // Build a prompt for a single suggestion
        String prompt = "The user searched for: \"" + animalName + "\"\n\n" +
                "Suggest ONE correct animal name they might have meant. " +
                "Return ONLY the single animal name, nothing else. No explanations, no commas, just one name.";

        JSONObject userMessage = new JSONObject()
                .put("role", "user")
                .put("content", prompt);

        JSONArray messages = new JSONArray().put(userMessage);

        JSONObject payload = new JSONObject()
                .put("model", "openai/gpt-4o")
                .put("messages", messages)
                .put("max_completion_tokens", 50)
                .put("stream", false);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                JSONArray choices = json.getJSONArray("choices");

                if (!choices.isEmpty()) {
                    JSONObject choice0 = choices.getJSONObject(0);
                    String content = choice0.getJSONObject("message").getString("content").trim();

                    System.out.println("Fuzzy suggestion result: " + content);
                    return content;
                }
            } else {
                System.out.println("OpenRouter API error in fuzzy suggestion: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error getting fuzzy suggestion: " + e.getMessage());
        }

        return null; //null if no suggestion
    }
}