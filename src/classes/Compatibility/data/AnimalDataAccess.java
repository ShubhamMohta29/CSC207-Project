package classes.Compatibility.data;

import classes.retrieveInfo.ApiClass;
import classes.retrieveInfo.Animal;
import classes.retrieveInfo.AnimalFactory;
import classes.Compatibility.usecases.AnimalDataAccessInterface;

public class AnimalDataAccess implements AnimalDataAccessInterface {
    private final ApiClass api;
    private final AnimalFactory factory;

    public AnimalDataAccess() {
        this.api = new ApiClass();
        this.factory = new AnimalFactory();
    }

    @Override
    public Animal getAnimalByName(String name) {
        String animalData = api.getAnimalData(name);

        if (animalData == null || animalData.trim().isEmpty() || animalData.equals("[]")) {
            return null;
        }

        // Convert JSON from API into a proper Animal object
        try {
            return factory.fromJsonArrayString(animalData);
        } catch (Exception e) {
            // API response was not parseable or invalid → treat as no result
            return null;
        }
    }
}
