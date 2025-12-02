package classes.settings;

import java.awt.Color;

/**
 * Interface representing the output boundary for text settings.
 * Provides methods to retrieve color, font size, and font name for display.
 */
public interface textSettingOutputBoundary {

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

    /**
     * Returns the view model.
     * @param output viewModel for UI manager
     * @return viewModel
     */
    viewModel updateView(textSettingOutput output);
}
