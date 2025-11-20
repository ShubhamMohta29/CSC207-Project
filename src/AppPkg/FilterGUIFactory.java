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


