package classes.retrieveInfo;

public class SearchAnimalsOutputData {
    private final boolean success;
    private final String errorMessage;
    private final Animal[] animals;
    private final String suggestion;

    public SearchAnimalsOutputData(boolean success, String errorMessage, Animal[] animals, String suggestion) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.animals = animals;
        this.suggestion = suggestion;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
