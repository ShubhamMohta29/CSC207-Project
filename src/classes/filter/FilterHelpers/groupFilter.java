package classes.filter.FilterHelpers;

import java.util.List;

import classes.retrieveInfo.animal;

public class groupFilter implements animalFilter {
    private final List<String> groups;

    public groupFilter(List<String> groups) {
        this.groups = groups;
    }

    /**
     * Group strategy.
     * @param a the animal object to check
     * @return it it matches the group
     */
    @Override
    public boolean matches(animal a) {
        if (groups == null || groups.isEmpty()) return true;
        return groups.contains(a.getGroup()) || groups.contains(a.getType());
    }
}
