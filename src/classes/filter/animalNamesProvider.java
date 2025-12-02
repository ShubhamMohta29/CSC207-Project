/**
 * AnimalNamesProvider :LLM - OpenRouterAPI.
 */

package classes.filter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class animalNamesProvider implements animalNamesProviderI {

    private final FilterPromptBuilder promptBuilder;
    private final llmClientHandler llmClient;

    public animalNamesProvider() {
        this.promptBuilder = new FilterPromptBuilder();
        this.llmClient = new llmClientHandler();
    }

    @Override
    public List<String> getCandidateNames(filterInput request) {
        final String prompt = promptBuilder.buildPrompt(request);
        final String response = llmClient.query(prompt);

        return parseNames(response);
    }

    private List<String> parseNames(String responseBody) {
        final List<String> names = new ArrayList<>();

        if (responseBody == null) {
            return names;
        }

        final JSONObject json = new JSONObject(responseBody);
        final JSONArray choices = json.getJSONArray("choices");

        if (choices.length() == 0) {
            return names;
        }

        final String content = choices.getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

        for (String name : content.split(",")) {
            final String clean = name.trim();
            if (!clean.isEmpty()) {
                names.add(clean);
            }
        }

        return names;
    }
}
