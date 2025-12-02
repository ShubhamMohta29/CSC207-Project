/**
 * FilterPresenter :converts domain response to FilterViewModel. The FilterViewModel can then be observed by the view to update the display
 */
package classes.filter;

import java.util.List;

import classes.retrieveInfo.animal;

public class filterPresenter implements filterOutputBoundary {

    // field declarations
    private final FilterViewModel filterViewModel;

    /*
    Constructor
     */
    public filterPresenter(FilterViewModel filterViewModel) {
        this.filterViewModel = filterViewModel;
    }

    /*
    Updates the viewmodel
     */
    @Override
    public void present(filterOutput output) {
        final List<animal> animals = output.getFilteredAnimals();
        filterViewModel.setAnimals(animals);
        filterViewModel.setHasMore(output.hasMore());
        filterViewModel.setNextCursor(output.getNextCursor());
    }
}