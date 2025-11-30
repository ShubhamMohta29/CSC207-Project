package Classes.Compatibility.data;

import Classes.APIClass;
import Classes.Animal;
import Classes.Compatibility.usecases.AnimalDataAccessInterface;

/**
 * Data access implementation for retrieving animal data.
 * Uses APIClass to fetch animal information.
 */
public class AnimalDataAccess implements AnimalDataAccessInterface {
    private final APIClass api;

    /**
     * Constructs a new AnimalDataAccess with a new APIClass instance.
     */
    public AnimalDataAccess() {
        this.api = new APIClass();
    }

    @Override
    public Animal getAnimalByName(final String name) {
        final String animalData = api.getAnimalData(name);

        if (animalData == null || "[]".equals(animalData)) {
            return null;
        }

        return new Animal(animalData);
    }
}
