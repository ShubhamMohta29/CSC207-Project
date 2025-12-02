package Classes.Filter;

public class FilterPromptBuilder {

    /**
     * Converts the filter request into a text prompt for the LLM.
     * @param request takes in the filter conditions
     * @return returns the prompt to send to api
     */
    public String buildPrompt(FilterInput request) {
        final StringBuilder sb = new StringBuilder();

        sb.append("""
                List 15–20 common animal names that satisfy the user's filters.
                - Must match at least one value from EACH selected category (AND across categories).
                - OR logic inside each category.
                - Apply lifespan min/max if provided.
                - Return ONLY a comma-separated list. No commentary.
                """);

        if (!request.getGroups().isEmpty()) {
            sb.append("Groups: ").append(String.join(", ", request.getGroups())).append("\n");
        }

        if (!request.getDiets().isEmpty()) {
            sb.append("Diets: ").append(String.join(", ", request.getDiets())).append("\n");
        }

        if (!request.getLocations().isEmpty()) {
            sb.append("Locations: ").append(String.join(", ", request.getLocations())).append("\n");

        }

        if (request.getMinLifespan() != null) {
            sb.append("Min lifespan: ").append(request.getMinLifespan()).append("\n");
        }

        if (request.getMaxLifespan() != null) {
            sb.append("Max lifespan: ").append(request.getMaxLifespan()).append("\n");
        }

        return sb.toString();
    }
}
