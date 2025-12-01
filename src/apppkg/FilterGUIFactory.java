/**
 * FilterGUIFactory : Creates the filter gui object with all the necessary parameters
 */
package apppkg;

import classes.retrieveInfo.ApiClass;
import classes.Filter.*;

import javax.swing.*;

public class FilterGUIFactory {
    public static FilterGUI create(JFrame parent) {

        // required parameters
        ApiClass animalProvider = new ApiClass();
        FilterViewModel vm = new FilterViewModel();
        FilterPresenter filterPresenter = new FilterPresenter(vm);
        AnimalNamesProviderI nameProvider = new AnimalNamesProvider();
        FilterInteractor interactor = new FilterInteractor(nameProvider, filterPresenter, animalProvider);
        FilterController filterController = new FilterController(interactor);

        // build GUI
        return new FilterGUI(parent, filterController, vm);
    }

}