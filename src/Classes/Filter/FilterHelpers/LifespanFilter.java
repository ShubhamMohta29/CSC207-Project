package Classes.Filter.FilterHelpers;

import Classes.retrieveInfo.Animal;

// Lifespan filter
public class LifespanFilter implements AnimalFilter {
    private final Integer minLifespan;
    private final Integer maxLifespan;

    public LifespanFilter(Integer minLifespan, Integer maxLifespan) {
        this.minLifespan = minLifespan;
        this.maxLifespan = maxLifespan;
    }

    /**
     * Lifespan strategy.
     * @param a the animal object to check
     * @return if it matches the lifespan filter
     */
    @Override
    public boolean matches(Animal a) {
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
