package Classes.Settings;

import Classes.Settings.UIStrategy.StrategyMap;
import Classes.Settings.UIStrategy.UIStyleStrategy;

import javax.swing.*;
import java.awt.*;

public class TextSettingPresenter implements TextSettingOutputBoundary {
    private final StrategyMap strategies;
    private Color fg;
    private Font font;

    public TextSettingPresenter(TextSettingInteractor config) {
        this.fg = config.getColor();
        this.font = config.getStyle();
        this.strategies = new StrategyMap();

    }

    public void updateOutput(TextSetting config) {
        fg = config.getTextColor();
        font = config.getFont();
    }

    /**
     * Updates the style of *all* JComponents in the given frame
     * based on the provided color, size, and font style.
     */
    @Override
    public void presentSettingOutput(JFrame frame) {

        updateComponentsRecursively(frame.getContentPane(), fg, font);
        frame.revalidate();
        frame.pack();
    }

    @Override
    public void presentSettingOutput(JDialog dialog) {

        updateComponentsRecursively(dialog.getContentPane(), fg, font);
        dialog.revalidate();
        dialog.pack();
    }

    @Override
    public void presentSettingOutput(Window window) {

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