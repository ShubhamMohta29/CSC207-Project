/**
 * FilterOutput : Class modelling filter output object
  /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import Classes.Animal;
import java.util.*;

public class FilterOutput {

    //field declaration
    private final List<Animal> filtered_animals;
    private final boolean hasMore; //flag to keep track of whether the system can generate another lazy-load request
    private final boolean success;
    private final String errorMsg;

    //optional
    private double time; //maybe need this for optimization proof?
    private int total_results; //maybe matters at some point? edge cases perhaps?


    public FilterOutput(List<Animal> filtered_animals, boolean hasMore, boolean success, String errorMsg) {
        this.filtered_animals = filtered_animals != null ? filtered_animals : List.of();
        this.hasMore = hasMore;
        this.success = success;
        this.errorMsg = errorMsg;
    }

    //getters
    public List<Animal> getFilteredAnimals() { return filtered_animals; }
    public boolean checkHasMore(){ return hasMore;}
    public double getTime() { return time; }
    public int getTotalResults() { return total_results; }
    public String getErrorMsg() {
        return errorMsg;
    }
}


