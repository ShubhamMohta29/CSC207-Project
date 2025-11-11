package Classes.UseCaseFilter;

import java.util.*;

public class FilterInput {
    //define all the filter criterion (better to define as enum?)
    private List<String> animal_groups;
    private List<String> animal_diets;
    private List<String> animal_locations;
    private double min_speed;
    private double max_speed;
    private double min_lifespan;
    private double max_lifespan;
    private double min_weight;
    private double max_weight;


    //constructor
    FilterInput(List<String> animal_groups, List<String> animal_diets, List<String> animal_locations, double min_speed,
                double max_speed, double min_lifespan, double max_lifespan, double min_weight, double max_weight){
        this.animal_groups = animal_groups;
        this.animal_diets = animal_diets;
        //this.
    }

    //getters

}
