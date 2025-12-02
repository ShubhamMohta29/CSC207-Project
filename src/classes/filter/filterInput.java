/**
 * FilterInput : Class modelling filter requests
 */

package classes.filter;

import java.util.*;

public class filterInput {
    // field declarations
    // define all the filter criterion - immutable class
    // constant(s)

    // fixed page size
    private static final int PAGE_SIZE = 7;
    // filter fields
    private final List<String> animalGroups;
    private final List<String> animalDiets;
    private final List<String> animalLocations;

    // since the user can choose not to apply any lifespan filter in which case it
    // would allow it to be Null (also autoboxing)
    private final Integer minLifespan;
    private final Integer maxLifespan;

    // pagination fields
    // current number of items fetched for this request
    private final int pageSize;
    // pagination token
    private final String cursor;

    /*
      Constructor --> Private constructor - use builder. Why? --> in case we want to add more filter criterion
     */
    filterInput(Builder builder) {
        this.animalGroups = List.copyOf(builder.groups);
        this.animalLocations = List.copyOf(builder.locations);
        this.animalDiets = List.copyOf(builder.diets);
        this.minLifespan = builder.minLifespan;
        this.maxLifespan = builder.maxLifespan;
        this.cursor = builder.cursor;
        this.pageSize = PAGE_SIZE;
    }

    /*
    Getter methods
     */
    public List<String> getGroups() {
        return animalGroups;
    }

    public List<String> getLocations() {
        return animalLocations;
    }

    public List<String> getDiets() {
        return animalDiets;
    }

    public Integer getMinLifespan() {
        return minLifespan;
    }

    public Integer getMaxLifespan() {
        return maxLifespan;
    }

    public String getCursor() {
        return cursor;
    }

    public int getPageSize() {
        return pageSize;
    }

    /*
      Builder pattern for flexible construction
        Note: Static inner class because : It doesn’t need the outer instance.
                                            want to logically group related code.
                                            want better memory efficiency.
                                            want encapsulation for helper classes.
     */
    public static class Builder {
        private List<String> groups = List.of();
        private List<String> locations = List.of();
        private List<String> diets = List.of();
        private Integer minLifespan;
        private Integer maxLifespan;
        private String cursor;
        private int pageSize;

        /**
         * Creates a filter based on groups.
         *
         * @param groups selected group
         * @return a builder filter object
         */
        public Builder groups(List<String> groups) {
            this.groups = groups != null ? groups : List.of();
            return this;
        }

        /**
         * Creates a filter based on groups.
         *
         * @param locations selected locations
         * @return a builder filter object
         */
        public Builder locations(List<String> locations) {
            this.locations = locations != null ? locations : Collections.emptyList();
            return this;
        }

        /**
         * Creates a filter based on groups.
         *
         * @param diets selected diets
         * @return a builder filter object
         */
        public Builder diets(List<String> diets) {
            this.diets = diets != null ? diets : Collections.emptyList();
            return this;
        }

        /**
         * Creates a filter based on groups.
         *
         * @param min min range
         * @param max max range
         * @return a builder filter object
         */
        public Builder lifespanRange(Integer min, Integer max) {
            this.minLifespan = min;
            this.maxLifespan = max;
            return this;
        }

        /**
         * Creates a filter based on groups.
         *
         * @param cursor initilizes cursor
         * @return a builder filter object
         */
        public Builder cursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        /**
         * Creates a filter based on groups.
         *
         * @return a builder filter logic
         */

        public filterInput build() {
            return new filterInput(this);
        }
    }
}

