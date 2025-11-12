/**
 * FilterInput : Class modelling filter requests
 /)/)
 ( . .)
 ( づ♡
 */

package Classes.Filter;

import java.util.*;

public class FilterInput {
    //define all the filter criterion
    private final List<String> animal_groups;
    private final List<String> animal_diets;
    private final List<String> animal_locations;
    private final int min_lifespan;
    private final int max_lifespan;
    int limit;  //max number of items to fetch per request
    String cursor; //pagination token


    // Private constructor - use builder. Why? --> in case we want to add more filter criterion
    private FilterInput(Builder builder) {
        this.animal_groups = List.copyOf(builder.groups);
        this.animal_locations = List.copyOf(builder.locations);
        this.animal_diets = List.copyOf(builder.diets);
        this.min_lifespan = builder.min_lifespan;
        this.max_lifespan = builder.max_lifespan;
        this.cursor = builder.cursor;
        this.limit = builder.limit;
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
        private int min_lifespan;
        private int max_lifespan;
        private String cursor;
        private int limit = 7;

        public Builder groups(List<String> groups) {
            this.groups = groups != null ? groups : List.of();
            return this;
        }

        public Builder locations(List<String> locations) {
            this.locations = locations != null ? locations : List.of(); //empty list
            return this;
        }

        public Builder diets(List<String> diets) {
            this.diets = diets != null ? diets : List.of();
            return this;
        }


        public Builder lifespanRange(int min, int max) {
            this.min_lifespan = min;
            this.max_lifespan = max;
            return this;
        }

        public Builder cursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = Math.max(1, limit);
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
    public int getMinLifespan() { return min_lifespan; }
    public int getMaxLifespan() { return max_lifespan; }
    public String getCursor() { return cursor; }
    public int getLimit() { return limit; }


}