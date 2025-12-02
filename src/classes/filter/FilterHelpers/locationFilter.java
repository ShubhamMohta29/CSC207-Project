package classes.filter.FilterHelpers;

import java.util.List;

import classes.retrieveInfo.animal;

public class locationFilter implements animalFilter {
    private final List<String> locations;

    public locationFilter(List<String> locations) {
        this.locations = locations;
    }

    /**
     * Location strategy.
     * @param a the animal object to check
     * @return if it matches the location
     */
    @Override
    public boolean matches(animal a) {
        boolean flag = false;
        if (locations == null || locations.isEmpty()) {
            flag = true;
        }
        for (String loc : locations) {
            if (List.of(a.getLocation()).contains(loc)) {
                flag = true;
            }
        }
        return flag;
    }
}
