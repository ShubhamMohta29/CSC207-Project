package classes.filter.FilterHelpers;

import classes.retrieveInfo.animal;

// Lifespan filter
public class lifespanFilter implements animalFilter {
    private final Integer minLifespan;
    private final Integer maxLifespan;

    public lifespanFilter(Integer minLifespan, Integer maxLifespan) {
        this.minLifespan = minLifespan;
        this.maxLifespan = maxLifespan;
    }

    /**
     * Lifespan strategy.
     * @param a the animal object to check
     * @return if it matches the lifespan filter
     */
    @Override
    public boolean matches(animal a) {
        boolean flag = true;
        if (minLifespan != null && a.getLifespan() < minLifespan) {
            flag = true;
        }
        if (maxLifespan != null && a.getLifespan() > maxLifespan) {
            flag = true;
        }
        return flag;
    }
}
