package Classes.retrieveInfo.FuzzySearch;

import Classes.Filter.LlmClientHandler;

@SuppressWarnings("checkstyle:Indentation")
public class AnimalFuzzySearch implements FuzzySearchProvider {

    private final LlmClientHandler llmClient;
        private static final String PROMPT_TEMPLATE =
            "The user searched for: \"%s\".\n"
                    + "Suggest ONLY one correct animal name they might have meant. "
                    + "Remember its a spelling mistake"
                    + "Return exactly one name, no extra text, no commas, no explanations.";

    /*
    * Constructor
    */
    public AnimalFuzzySearch() {

        this.llmClient = new LlmClientHandler();
        }

    @SuppressWarnings({"checkstyle:ReturnCount", "checkstyle:Indentation", "checkstyle:IllegalCatch", "checkstyle:CatchParameterName"})
    @Override
    public String getSuggestion(String animalName) {
        final String prompt = String.format(PROMPT_TEMPLATE, animalName);

        // Delegate API call to LlmClientHandle
        final String responseBody = llmClient.query(prompt);
        if (responseBody == null) {
            return null;
        }

        // Extract suggestion from API response
        try {
            final org.json.JSONArray choices = new org.json.JSONObject(responseBody).getJSONArray("choices");
            if (!choices.isEmpty()) {
                final org.json.JSONObject choice0 = choices.getJSONObject(0);
                return choice0.getJSONObject("message").getString("content").trim();
                }
        }
        catch (Exception e) {
            System.err.println("Error parsing fuzzy suggestion JSON: " + e.getMessage());
        }
        return null;
        }

}

