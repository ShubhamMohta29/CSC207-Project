package classes.compatibility.data;

import classes.retrieveInfo.APIClass;
import classes.retrieveInfo.animal;
import classes.retrieveInfo.animalFactory;
import classes.compatibility.usecases.animalDataAccessInterface;

public class animalDataAccess implements animalDataAccessInterface {
    private final APIClass api;
    private final animalFactory factory;

    public animalDataAccess() {
        this.api = new APIClass();
        this.factory = new animalFactory();
    }

    @Override
    public animal getAnimalByName(String name) {
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
