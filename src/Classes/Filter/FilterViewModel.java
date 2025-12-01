/**
 * FilterViewModel : Holds current filtered results and pagination info. GUI reads from this class to render results.
 */
package Classes.Filter;


import java.util.ArrayList;
import java.util.List;

import Classes.retrieveInfo.Animal;

public class FilterViewModel {

    // field declarations
    private List<Animal> animals = new ArrayList<>();
    private boolean hasMore;
    private String nextCursor;

    /*
    Constructor
     */
    public List<Animal> getAnimals() {
        return List.copyOf(animals);
        // to prevent external modification
    }

    /*
    Creates the list of animals to be displayed in the results panel
     */
    public void setAnimals(List<Animal> newAnimals) {
        this.animals = new ArrayList<>(newAnimals);
    }

    /**
     * Updates the list of animals to be displayed in the results panel --> load more case.
     * @param newAnimals adds to the list of animals to be displayed
     */
    public void addAnimals(List<Animal> newAnimals) {
        this.animals.addAll(newAnimals);
    }

    /**
     * Checks if more animals are to be displayed --> affects the load more button logic.
     * @return if there is more
     */
    public boolean hasMore() {
        return hasMore;
    }

    /*
    Setters and getters
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
