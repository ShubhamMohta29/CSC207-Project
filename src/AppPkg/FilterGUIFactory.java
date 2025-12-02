/**
 * FilterGUIFactory : Creates the filter gui object with all the necessary parameters.
 */

package AppPkg;

import javax.swing.*;

import classes.filter.*;
import classes.filter.FilterHelpers.candidateCache;
import classes.filter.FilterHelpers.paginationHelper;
import classes.retrieveInfo.APIClass;

public class FilterGUIFactory {
    /**
     * Handles instantiating the GUI.
     * @param parent Jframe
     * @return the filtergui instance
     */
    public static FilterGUI create(JFrame parent) {

        // required parameters
        final APIClass animalProvider = new APIClass();
        final FilterViewModel vm = new FilterViewModel();
        final filterPresenter filterPresenter = new filterPresenter(vm);
        final animalNamesProviderI nameProvider = new animalNamesProvider();
        final filterInteractor interactor = new filterInteractor(nameProvider, filterPresenter, animalProvider, new candidateCache(), new paginationHelper());
        final filterController filterController = new filterController(interactor);

        // build GUI
        return new FilterGUI(parent, filterController, vm);
    }

}
