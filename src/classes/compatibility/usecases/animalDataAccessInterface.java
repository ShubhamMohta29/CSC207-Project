package classes.compatibility.usecases;

import classes.retrieveInfo.animal;

public interface animalDataAccessInterface {
    animal getAnimalByName(String name);
}