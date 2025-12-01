package Classes.Compatibility.usecases;

import Classes.retrieveInfo.Animal;

/**
 * Data Access for animal comparison use case.
 * Defines AnimalDataAccessInterface method.
 */
public interface AnimalDataAccessInterface {
    Animal getAnimalByName(String name);
}