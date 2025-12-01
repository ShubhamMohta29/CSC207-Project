package apppkg.Controllers;

import classes.retrieveInfo.Animal;

/**
 * Simple result holder returned by SearchController.
 */
public class SearchResult {
    private final boolean success;
    private final String message;
    private final Animal[] animals;
    private final String suggestion;

    public SearchResult(boolean success, String message, Animal[] animals, String suggestion) {
        this.success = success;
        this.message = message;

        if (animals == null) {
            this.animals = new Animal[0];
        }
        else {
            this.animals = animals;
        }

        this.suggestion = suggestion;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
