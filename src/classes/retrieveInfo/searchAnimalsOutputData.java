package classes.retrieveInfo;

public class searchAnimalsOutputData {
    private final boolean success;
    private final String errorMessage;
    private final animal[] animals;
    private final String suggestion;

    public searchAnimalsOutputData(boolean success, String errorMessage, animal[] animals, String suggestion) {
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

    public animal[] getAnimals() {
        return animals;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
