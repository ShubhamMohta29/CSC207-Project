package AppPkg.Controllers;

import classes.retrieveInfo.animal;

/**
 * Simple result holder returned by SearchController.
 */
public class SearchResult {
    private final boolean success;
    private final String message;
    private final animal[] animals;
    private final String suggestion;

    public SearchResult(boolean success, String message, animal[] animals, String suggestion) {
        this.success = success;
        this.message = message;

        if (animals == null) {
            this.animals = new animal[0];
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

    public animal[] getAnimals() {
        return animals;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
