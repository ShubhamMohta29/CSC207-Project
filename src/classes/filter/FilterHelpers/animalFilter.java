package classes.filter.FilterHelpers;

import classes.retrieveInfo.animal;

public interface animalFilter {
    /**
     * Base interface for filters.
     * @param a the animal object to check
     * @return if it matches the applied filters
     */
    boolean matches(animal a);
}


