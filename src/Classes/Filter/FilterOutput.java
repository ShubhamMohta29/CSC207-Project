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
    boolean hasMore; //flag to keep track of whether the system can generate another lazy-load request
    String nextCursor;
    private double time; //maybe need this for optimization proof?
    private int total_results; //maybe matters at some point? edge cases perhaps?


    //constructor
    FilterOutput(List<Animal> filtered_animals, boolean hasMore, String nextCursor) {
        this.filtered_animals = filtered_animals;
        this.hasMore = hasMore;
        this.nextCursor = nextCursor;
    }

    //getters
    public List<Animal> getFilteredAnimals() { return filtered_animals; }
    public boolean checkHasMore(){ return hasMore;}
    public String getNextCursor(){ return nextCursor;}
    public double getTime() { return time; }
    public int getTotalResults() { return total_results; }

}

