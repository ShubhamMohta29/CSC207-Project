package Classes.Compatibility.data;

import Classes.retrieveInfo.APIClass;
import Classes.retrieveInfo.Animal;
import Classes.retrieveInfo.AnimalFactory;
import Classes.Compatibility.usecases.AnimalDataAccessInterface;

public class AnimalDataAccess implements AnimalDataAccessInterface {
    private final APIClass api;
    private final AnimalFactory factory;

    public AnimalDataAccess() {
        this.api = new APIClass();
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
