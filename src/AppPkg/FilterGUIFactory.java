/**
 * FilterGUIFactory : Created the filter gui object with all the necessary parameters
 /)/)
 ( . .)
 ( づ♡
 */
package AppPkg;

import Classes.APIClass;
import Classes.Settings.*;
import Classes.Filter.*;

import javax.swing.*;

public class FilterGUIFactory {
    public static FilterGUI create(JFrame parent) {

        // required parameters
        APIClass animalProvider = new APIClass();
        ViewModel vm = new ViewModel();
        Presenter presenter = new Presenter(vm);
        AnimalNamesProviderI nameProvider = new AnimalNamesProvider("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");
        FilterInteractor interactor = new FilterInteractor(nameProvider, presenter, animalProvider);
        FilterController filterController = new FilterController(interactor);

        // build GUI
        return new FilterGUI(parent, filterController, vm);
    }
}


