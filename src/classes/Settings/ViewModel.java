package classes.Settings;

import static classes.Settings.SettingConstants.DEFAULT_STYLE;

import java.awt.Color;
import java.awt.Font;

/**
 * View model representing text settings for display in the UI.
 * Encapsulates the color and font (with style and size) for rendering text.
 */
public class ViewModel {

    /**
     * The text color.
     */
    private final Color color;

    /**
     * The font including name, style, and size.
     */
    private final Font font;

    /**
     * Constructs a ViewModel with the specified color, font name, and size.
     * Uses {@link SettingConstants#DEFAULT_STYLE} for the font style.
     *
     * @param color the text color
     * @param font  the font name
     * @param size  the font size
     */
    public ViewModel(Color color, String font, int size) {
        this.color = color;
        this.font = new Font(font, DEFAULT_STYLE, size);
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
     * Returns the font including name, style, and size.
     *
     * @return the font
     */
    public Font getFont() {
        return font;
    }
}
