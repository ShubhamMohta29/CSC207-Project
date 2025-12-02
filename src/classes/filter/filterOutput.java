/**
 * FilterOutput : Class modelling filter output object
 */
package classes.filter;

import java.util.*;

import classes.retrieveInfo.animal;

public class filterOutput {

    // field declaration
    private final List<animal> filteredAnimals;
    // flag to keep track of whether the system can generate another lazy-load request
    private final boolean hasMore;
    private final String nextCursor;

    /*
    Constructor
     */
    public filterOutput(List<animal> filteredAnimals, boolean hasMore, String nextCursor) {
        this.filteredAnimals = filteredAnimals != null ? filteredAnimals : Collections.emptyList();
        // just a quick
        // note: using empty list of Collections is better as its a singleton instance and doesn't create new instances
        // every time we want an empty list<T> --> very memory-efficient
        this.hasMore = hasMore;
        this.nextCursor = nextCursor;
    }

    /*
    Getter methods
     */
    public List<animal> getFilteredAnimals() {
        return List.copyOf(filteredAnimals);
    }

    /**
     * A getter method.
     * @return whether more animals can be displayed
     */

    public boolean hasMore() {
        return hasMore;
    }

    /*
     * @return Token to be used in the next filter request to fetch additional results.
     * Only relevant if checkHasMore() returns true.
     */
    public String getNextCursor() {
        return nextCursor; }

}
