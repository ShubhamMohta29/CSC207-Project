/**
 * FilterPresenter :converts domain response to FilterViewModel. The FilterViewModel can then be observed by the view to update the display
 */
package Classes.Filter;

import Classes.Animal;
import java.util.List;


public class FilterPresenter implements FilterOutputBoundary{

    //field declarations
    private final FilterViewModel filterViewModel;

    /*
    Constructor
     */
    public FilterPresenter(FilterViewModel filterViewModel) {
        this.filterViewModel = filterViewModel;
    }

    /*
    Updates the viewmodel
     */
    @Override
    public void present(FilterOutput output) {
        System.out.println("im in the presenter");
        List<Animal> animals = output.getFilteredAnimals();
        System.out.println("This is my list of filtered animals: " + animals.toArray());
        filterViewModel.setAnimals(animals);
        filterViewModel.setHasMore(output.hasMore());
        filterViewModel.setNextCursor(output.getNextCursor());
    }
}