/**
 * FilterGUIFactory : Creates the filter gui object with all the necessary parameters.
 */

package AppPkg;

import javax.swing.*;

import Classes.Filter.*;
import Classes.Filter.FilterHelpers.CandidateCache;
import Classes.Filter.FilterHelpers.PaginationHelper;
import Classes.retrieveInfo.APIClass;

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
        final FilterPresenter filterPresenter = new FilterPresenter(vm);
        final AnimalNamesProviderI nameProvider = new AnimalNamesProvider();
        final FilterInteractor interactor = new FilterInteractor(nameProvider, filterPresenter, animalProvider, new CandidateCache(), new PaginationHelper());
        final FilterController filterController = new FilterController(interactor);

        // build GUI
        return new FilterGUI(parent, filterController, vm);
    }

}
