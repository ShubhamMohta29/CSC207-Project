package Classes.Compatibility.usecases;

import Classes.retrieveInfo.Animal;

public interface AnimalDataAccessInterface {
    Animal getAnimalByName(String name);
}