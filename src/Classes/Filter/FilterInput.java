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
    // private final double min_speed;
    // private final double max_speed;
    private final double min_lifespan;
    private final double max_lifespan;
    // private final double min_weight;
    // private final double max_weight;
    int limit;  //max number of items to fetch per request
    String cursor; //pagination token


    //constructor1
    public FilterInput(List<String> animal_groups, List<String> animal_diets, List<String> animal_locations,
                       double min_lifespan, double max_lifespan) {
        this.animal_groups = animal_groups;
        this.animal_diets = animal_diets;
        this.animal_locations = animal_locations;
        this.min_lifespan = min_lifespan;
        this.max_lifespan = max_lifespan;
    }

    //constructor2
    FilterInput(List<String> animal_groups, List<String> animal_diets, List<String> animal_locations, double min_speed,
                double max_speed, double min_lifespan, double max_lifespan, double min_weight, double max_weight){
        this.animal_groups = animal_groups;
        this.animal_diets = animal_diets;
        this.animal_locations = animal_locations;
        // this.min_speed = min_speed;
        // this.max_speed = max_speed;
        this.min_lifespan = min_lifespan;
        this.max_lifespan = max_lifespan;
        // this.min_weight = min_weight;
        // this.max_weight = max_weight;
    }

    //getters
    public List<String> getAnimalGroups() { return animal_groups; }
    public List<String> getAnimalDiets() { return animal_diets; }
    public List<String> getAnimalLocations() { return animal_locations; }
    // public double getMinSpeed() { return min_speed; }
    //public double getMaxSpeed() { return max_speed; }
    public double getMinLifespan() { return min_lifespan; }
    public double getMaxLifespan() { return max_lifespan; }
    // public double getMinWeight() { return min_weight; }
    // public double getMaxWeight() { return max_weight; }
    public int getLimit(){return limit;}


}

