package classes.retrieveInfo;

public class SearchAnimalsViewModel {
    private boolean success;
    private String message;
    private Animal[] animals;
    private String suggestion;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
