/**
 * Presenter :converts domain response to ViewModel (UI-friendly) and updates the view model which is being viewed by the view
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import Classes.Animal;
import java.util.List;


public class Presenter implements FilterOutputBoundary{

    //field declarations
    private final ViewModel viewModel;

    /*
    Constructor
     */
    public Presenter(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /*
    Updates the viewmodel
     */
    @Override
    public void present(FilterOutput output) {
        System.out.println("im in the presenter");
        List<Animal> animals = output.getFilteredAnimals();
        System.out.println("This is my list of filtered animals: " + animals.toArray());
        viewModel.setAnimals(animals);
        viewModel.setHasMore(output.checkHasMore());
        viewModel.setNextCursor(output.getNextCursor());
    }
}
