package Classes.Filter.FuzzySearch;
import Config.ProjectConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnimalFuzzySearch implements FuzzySearchProvider {

        private static final String API_URL = ProjectConfig.OPEN_ROUTER_API_URL;
        private static final String apiKey = ProjectConfig.getOpenRouterApiKey();;
        private final HttpClient client;

        /*
         * Constructor
         */
        public AnimalFuzzySearch() {
            this.client = HttpClient.newHttpClient();
        }

        /*
         * Function call to get the potential candidates satisfying the user's applied filters
         */
        @Override
        public String getSuggestion(String animalName) {
            System.out.println("Getting fuzzy suggestion for: " + animalName);

            String prompt = "The user searched for: \"" + animalName + "\".\n" +
                    "Suggest ONLY one correct animal name they might have meant. " +
                    "Return exactly one name, no extra text, no commas, no explanations.";


            JSONObject payload = buildPayload(prompt, "openai/gpt-4o", 50);

            String responseBody = sendRequest(payload);
            if (responseBody != null) {
                try {
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray choices = json.getJSONArray("choices");
                    if (!choices.isEmpty()) {
                        JSONObject choice0 = choices.getJSONObject(0);
                        return choice0.getJSONObject("message").getString("content").trim();
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing fuzzy suggestion JSON: " + e.getMessage());
                }
            }

            return null; //null if no suggestion
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

}

