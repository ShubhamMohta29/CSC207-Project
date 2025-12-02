/**
 * FilterPresenter :converts domain response to FilterViewModel. The FilterViewModel can then be observed by the view to update the display
 */
package Classes.Filter;

import java.util.List;

import Classes.retrieveInfo.Animal;

public class FilterPresenter implements FilterOutputBoundary {

    // field declarations
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
        final List<Animal> animals = output.getFilteredAnimals();
        filterViewModel.setAnimals(animals);
        filterViewModel.setHasMore(output.hasMore());
        filterViewModel.setNextCursor(output.getNextCursor());
    }
}