/**
 * FilterViewModel : Holds current filtered results and pagination info. GUI reads from this class to render results.
 */
package Classes.Filter;

import Classes.Animal;

import java.util.ArrayList;
import java.util.List;

public class FilterViewModel {

    //field declarations
    private List<Animal> animals = new ArrayList<>();
    private boolean hasMore = false;
    private String nextCursor = null;

    /*
    Constructor
     */
    public List<Animal> getAnimals() {
        return List.copyOf(animals); //to prevent external modification
    }

    /*
    Creates the list of animals to be displayed in the results panel
     */
    public void setAnimals(List<Animal> newAnimals) {
        this.animals = new ArrayList<>(newAnimals);
        System.out.println("im updating the set animals method to this: " +animals);
    }

    /*
    Updates the list of animals to be displayed in the results panel --> load more case
     */
    public void addAnimals(List<Animal> newAnimals) {
        this.animals.addAll(newAnimals);
    }

    /*
    Checks if more animals are to be displayed --> affects the load more button logic
     */
    public boolean hasMore() {
        return hasMore;
    }

    /*
    Setters and getters
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        System.out.println("do i have more animals to display? " + hasMore);
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}