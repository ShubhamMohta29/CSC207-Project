package apppkg;

import classes.Settings.TextSettingController;
import apppkg.UIStrategy.StrategyMap;
import apppkg.UIStrategy.UIStyleStrategy;
import classes.Settings.ViewModel;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    private final StrategyMap strategies;
    private final String filePath;
    private Color fg;
    private Font font;

    /**
     * UIChanger now builds:
     * - Controller
     * - Presenter
     * - ViewModel
     * internally from the filePath.
     */
    public UIManager(String filePath) {

        // Build the controller
        TextSettingController controller = new TextSettingController(filePath);

        ViewModel viewModel = controller.getViewModel();

        // Store UI-ready values
        this.filePath= filePath;
        this.fg = viewModel.getColor();
        this.font = viewModel.getFont();
        this.strategies = new StrategyMap();
    }

    public  Font getFont(){
        return font;
    }

    public void updateALL(JFrame frame) {
        TextSettingController controller = new TextSettingController(filePath);

        // Build the ViewModel from presenter
        ViewModel viewModel = controller.getViewModel();

        // Store UI-ready values
        this.fg = viewModel.getColor();
        this.font = viewModel.getFont();
        updateComponentsRecursively(frame.getContentPane(), fg, font);
        frame.revalidate();
        frame.pack();
    }

    /**
     * Recursively applies styling to all components.
     */
    private void updateComponentsRecursively(Container container, Color c, Font font) {
        for (Component comp : container.getComponents()) {

            UIStyleStrategy strategy = this.strategies.getStrategy(comp.getClass());
            strategy.apply(comp, c, font);

            if (comp instanceof Container) {
                updateComponentsRecursively((Container) comp, c, font);
            }
        }
    }
}