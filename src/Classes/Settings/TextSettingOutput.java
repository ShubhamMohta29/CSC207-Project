package Classes.Settings;

import Classes.Settings.UIStrategy.StrategyMap;
import Classes.Settings.UIStrategy.UIStyleStrategy;

import javax.swing.*;
import java.awt.*;

public class TextSettingOutput {
    private final TextSettingInteractor config;
    private StrategyMap strategies;
    private TextSettingInputBoundary interactor;

    public TextSettingOutput(TextSettingInteractor config) {
        this.config = config;
        this.strategies = new StrategyMap();

    }

    /**
     * Updates the style of *all* JComponents in the given frame
     * based on the provided color, size, and font style.
     */
    public void updateChangesAll(TextSettingRequest request, JFrame frame) {

        interactor.editSettings(request);
        Color fg = config.getColor();
        Font font = config.getStyle();

        updateComponentsRecursively(frame.getContentPane(), fg, font);

        frame.revalidate();
        frame.pack();
    }

    public void updateAll(JFrame frame) {
        Color fg = config.getColor();
        Font font = config.getStyle();

        updateComponentsRecursively(frame.getContentPane(), fg, font);

        frame.revalidate();
        frame.pack();
    }

    public void updateALL(JDialog dialog) {
        Color fg = config.getColor();
        Font font = config.getStyle();

        updateComponentsRecursively(dialog.getContentPane(), fg, font);
        dialog.revalidate();
        dialog.pack();
    }

    public void updateALL(Window window) {
        Color fg = config.getColor();
        Font font = config.getStyle();

        updateComponentsRecursively(window, fg, font);
        window.revalidate();
        window.pack();
    }

    /**
     * Recursively traverses through all components in a container
     * and applies the given color and font.
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