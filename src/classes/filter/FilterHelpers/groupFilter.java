package Classes.Filter.FilterHelpers;

import java.util.List;

import Classes.retrieveInfo.Animal;

public class GroupFilter implements AnimalFilter {
    private final List<String> groups;

    public GroupFilter(List<String> groups) {
        this.groups = groups;
    }

    /**
     * Group strategy.
     * @param a the animal object to check
     * @return it it matches the group
     */
    @Override
    public boolean matches(Animal a) {
        if (groups == null || groups.isEmpty()) return true;
        return groups.contains(a.getGroup()) || groups.contains(a.getType());
    }
}
