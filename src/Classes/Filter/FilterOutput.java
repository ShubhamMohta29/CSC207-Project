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
    private final String nextCursor;


    public FilterOutput(List<Animal> filtered_animals, boolean hasMore, String nextCursor) {
        this.filtered_animals = filtered_animals != null ? filtered_animals : Collections.emptyList(); //just a quick
        // note: using empty list of Collections is better as its a singleton instance and doesn't create new instances
        // every time we want an empty list<T> --> very memory-efficient
        this.hasMore = hasMore;
        this.nextCursor = nextCursor;
    }

    //getters
    public List<Animal> getFilteredAnimals() { return filtered_animals; }
    public boolean checkHasMore(){ return hasMore;}
    public String getNextCursor() { return nextCursor; }

}


