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


