package Classes.Filter.FilterHelpers;

import java.util.List;

import Classes.retrieveInfo.Animal;

// Diet filter
public class DietFilter implements AnimalFilter {
    private final List<String> diets;

    public DietFilter(List<String> diets) {
        this.diets = diets;
    }

    /**
     * Diet strategy.
     * @param a the animal object to check
     * @return if the diet matches
     */
    @Override
    public boolean matches(Animal a) {
        if (diets == null || diets.isEmpty()) {
            return true;
        }
        return diets.contains(a.getDiet());
    }
}
