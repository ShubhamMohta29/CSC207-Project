package classes.filter.FilterHelpers;

import java.util.List;

import classes.retrieveInfo.animal;

// Diet filter
public class dietFilter implements animalFilter {
    private final List<String> diets;

    public dietFilter(List<String> diets) {
        this.diets = diets;
    }

    /**
     * Diet strategy.
     * @param a the animal object to check
     * @return if the diet matches
     */
    @Override
    public boolean matches(animal a) {
        if (diets == null || diets.isEmpty()) {
            return true;
        }
        return diets.contains(a.getDiet());
    }
}
