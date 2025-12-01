package classes.Compatibility.usecases;

import classes.retrieveInfo.Animal;

public interface AnimalDataAccessInterface {
    Animal getAnimalByName(String name);
}