package classes.Settings;

import static classes.Settings.SettingConstants.*;

/**
 * Request model representing user input for text settings.
 * Encapsulates color, font size, and font style information.
 */
public class TextSettingInput {

    /**
     * The font style or name.
     */
    private String style = DEFAULT_FONT_NAME;

    /**
     * The font size.
     */
    private int size = DEFAULT_FONT_SIZE;

    /**
     * The text color as a string.
     */
    private String color = NAME_BLACK;

    /**
     * Constructs a new TextSettingInput with the given color, size, and style.
     * If any parameter is invalid (null or 0 for size), defaults are used.
     *
     * @param color the text color
     * @param size  the font size
     * @param style the font name or style
     */
    public TextSettingInput(String color, int size, String style) {

        this.color = color;
        this.size = size;
        this.style = style;
    }

    /**
     * Returns the text color.
     *
     * @return the color as a string
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the font style or name.
     *
     * @return the font style
     */
    public String getStyle() {
        return style;
    }

    /**
     * Returns the font size.
     *
     * @return the font size
     */
    public int getSize() {
        return size;
    }
}
