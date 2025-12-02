package classes.settings;

import java.awt.Color;
import java.awt.Font;

/**
 * Represents the business model for storing and managing text-related settings,
 * including font size, color, and font family name.
 */
public class textSetting {

    /**
     * The size of the text in points.
     */
    private int textSize;

    /**
     * The color used to render the text.
     */
    private Color textColor;

    /**
     * The name of the font family used for rendering the text.
     */
    private String fontName;

    /**
     * Constructs a TextSetting object with the given size, color, and font name.
     *
     * @param textSize  the size of the text in points
     * @param textColor the color of the text
     * @param fontName  the name of the font family
     */
    public textSetting(int textSize, Color textColor, String fontName) {
        this.textSize = textSize;
        this.textColor = textColor;
        this.fontName = fontName;
    }

    /**
     * Returns the size of the text.
     *
     * @return the text size in points
     */
    public int getTextSize() {
        return textSize;
    }

    /**
     * Returns the color of the text.
     *
     * @return the text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Returns the font name used to render the text.
     *
     * @return the font family name
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * Returns a {@link Font} object constructed from the stored font name and size.
     * This uses {@link Font#PLAIN} as the default font style.
     *
     * @return a Font instance representing the stored text settings
     */
    public Font getFont() {
        return new Font(fontName, Font.PLAIN, textSize);
    }

    /**
     * Sets the size of the text.
     *
     * @param textSize the new text size in points
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * Sets the color of the text.
     *
     * @param textColor the new text color
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Sets the font family name used for rendering the text.
     *
     * @param fontName the new font family name
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
}
