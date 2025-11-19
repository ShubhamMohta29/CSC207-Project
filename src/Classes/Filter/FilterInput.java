/**
 * FilterInput : Class modelling filter requests
  /)/)
 ( . .)
 ( づ♡
 */

package Classes.Filter;

import java.util.*;

public class FilterInput {
    //define all the filter criterion - immutable class
    //constant(s)
    private static final int PAGE_SIZE = 5; // fixed page size
    //filter fields
    private final List<String> animal_groups;
    private final List<String> animal_diets;
    private final List<String> animal_locations;
    private final Integer min_lifespan; //since the user can choose not to apply any lifespan filter in which case it
    // would allow it to be Null (also autoboxing)
    private final Integer max_lifespan;
    //pagination fields
    private final int pageSize;  //current number of items fetched for this request
    private final String cursor; //pagination token


    // Private constructor - use builder. Why? --> in case we want to add more filter criterion
    FilterInput(Builder builder) {
        this.animal_groups = List.copyOf(builder.groups);
        this.animal_locations = List.copyOf(builder.locations);
        this.animal_diets = List.copyOf(builder.diets);
        this.min_lifespan = builder.min_lifespan;
        this.max_lifespan = builder.max_lifespan;
        this.cursor = builder.cursor;
        this.pageSize = PAGE_SIZE;
    }

    // Builder pattern for flexible construction
    /*
    Static inner class because : It doesn’t need the outer instance.
        want to logically group related code.
        want better memory efficiency.
        want encapsulation for helper classes.
     */
    public static class Builder {
        private List<String> groups = List.of();
        private List<String> locations = List.of();
        private List<String> diets = List.of();
        private Integer min_lifespan;
        private Integer max_lifespan;
        private String cursor;

        public Builder groups(List<String> groups) {
            this.groups = groups != null ? groups : List.of();
            return this;
        }

        public Builder locations(List<String> locations) {
            this.locations = locations != null ? locations : Collections.emptyList(); //empty list
            return this;
        }

        public Builder diets(List<String> diets) {
            this.diets = diets != null ? diets : Collections.emptyList();
            return this;
        }


        public Builder lifespanRange(Integer min, Integer max) {
            this.min_lifespan = min;
            this.max_lifespan = max;
            return this;
        }

        public Builder cursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        public FilterInput build() {
            return new FilterInput(this);
        }
    }

    // Getters
    public List<String> getGroups() { return animal_groups; }
    public List<String> getLocations() { return animal_locations; }
    public List<String> getDiets() { return animal_diets; }
    public Integer getMinLifespan() { return min_lifespan; }
    public Integer getMaxLifespan() { return max_lifespan; }
    public String getCursor() { return cursor; }
    public int getPageSize() { return pageSize; }


}