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
