package Classes.Compatibility.usecases;

import Classes.Animal;

public interface AnimalDataAccessInterface {
    Animal getAnimalByName(String name);
}