package Classes.Filter.FilterHelpers;

import java.util.List;

import Classes.retrieveInfo.Animal;

public class LocationFilter implements AnimalFilter {
    private final List<String> locations;

    public LocationFilter(List<String> locations) {
        this.locations = locations;
    }

    /**
     * Location strategy.
     * @param a the animal object to check
     * @return if it matches the location
     */
    @Override
    public boolean matches(Animal a) {
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
