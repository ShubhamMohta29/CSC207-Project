package classes.retrieveInfo;

public class searchAnimalsViewModel {
    private boolean success;
    private String message;
    private animal[] animals;
    private String suggestion;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAnimals(animal[] animals) {
        this.animals = animals;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
