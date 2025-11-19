package Classes.Filter;

import Classes.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds current filtered results and pagination info.
 * GUI reads from this class to render results.
 */
public class ViewModel {

    private List<Animal> animals = new ArrayList<>();
    private boolean hasMore = false;
    private String nextCursor = null;

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void setAnimals(List<Animal> newAnimals) {
        this.animals = new ArrayList<>(newAnimals);
    }

    public void appendAnimals(List<Animal> moreAnimals) {
        this.animals.addAll(moreAnimals);
    }

    public boolean hasMore() {
        return hasMore;
    }

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
