package classes.settings;

import java.awt.Color;

/**
 * Output model representing processed text settings for display.
 * Stores color, font name, and font size.
 */
public class textSettingOutput {

    /**
     * The text color.
     */
    private final Color color;

    /**
     * The font name.
     */
    private final String font;

    /**
     * The text size in points.
     */
    private final int size;

    /**
     * Constructs a TextSettingOutput with the given color, font, and size.
     *
     * @param color the text color
     * @param font  the font name
     * @param size  the font size
     */
    public textSettingOutput(Color color, String font, int size) {
        this.color = color;
        this.font = font;
        this.size = size;
    }

    /**
     * Returns the text color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the font name.
     *
     * @return the font
     */
    public String getFont() {
        return font;
    }

    /**
     * Returns the text size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
