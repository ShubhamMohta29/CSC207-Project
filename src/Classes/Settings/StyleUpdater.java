package Classes.Settings;

import javax.swing.*;
import java.awt.*;

public class StyleUpdater {
    private final ReaderEditor config;

    public StyleUpdater(ReaderEditor config) {
        this.config = config;
    }

    /**
     * Updates the style of *all* JComponents in the given frame
     * based on the provided color, size, and font style.
     */
    public void updateChangesAll(String color, int size, String fontStyle, JFrame frame) {
        config.editSettings(color, size, fontStyle);
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

    /**
     * Recursively traverses through all components in a container
     * and applies the given color and font.
     */
    private void updateComponentsRecursively(Container container, Color c, Font font) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JComponent) {
                comp.setForeground(c);
                comp.setFont(font);
            } else if (comp instanceof Container) {
                updateComponentsRecursively((Container) comp, c, font);
            }
        }
    }
}