package Classes.Settings;

import java.awt.Color;

/**
 * Interface representing the output boundary for text settings.
 * Provides methods to retrieve color, font size, and font name for display.
 */
public interface TextSettingOutputBoundary {

    /**
     * Returns the text color.
     *
     * @return the color
     */
    Color getColor();

    /**
     * Returns the text size in points.
     *
     * @return the size
     */
    int getSize();

    /**
     * Returns the font name.
     *
     * @return the font
     */
    String getFont();
}
