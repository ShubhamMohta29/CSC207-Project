package classes.settings;

import java.awt.Color;

/**
 * Input boundary for the Text Setting use case.
 * Defines operations for editing and retrieving text-related settings.
 */
public interface textSettingInputBoundary {

    /**
     * Edits the current text settings using the given request model.
     *
     * @param request the input data required to update the text settings
     */
    void editSettings(textSettingInput request);

    /**
     * Returns the configured text color.
     *
     * @return the current text color
     */
    Color getTextColor();

    /**
     * Returns the configured font name.
     *
     * @return the current font name
     */
    String getFontName();

    /**
     * Returns the configured text size.
     *
     * @return the current text size
     */
    int getTextSize();
}
