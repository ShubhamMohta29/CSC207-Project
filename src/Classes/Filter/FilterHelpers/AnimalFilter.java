package Classes.Filter.FilterHelpers;

import Classes.retrieveInfo.Animal;

public interface AnimalFilter {
    /**
     * Base interface for filters.
     * @param a the animal object to check
     * @return if it matches the applied filters
     */
    boolean matches(Animal a);
}


