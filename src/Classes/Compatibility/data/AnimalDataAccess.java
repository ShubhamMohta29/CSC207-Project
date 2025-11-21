package Classes.Compatibility.data;

import Classes.APIClass;
import Classes.Animal;
import Classes.Compatibility.usecases.AnimalDataAccessInterface;

public class AnimalDataAccess implements AnimalDataAccessInterface {
    private final APIClass api;

    public AnimalDataAccess() {
        this.api = new APIClass();
    }

    @Override
    public Animal getAnimalByName(String name) {
        String animalData = api.getAnimalData(name);

        if (animalData == null || animalData.equals("[]")) {
            return null;
        }

        return new Animal(animalData);
    }
}