package Classes.Settings;

import java.awt.Color;

import static Classes.Settings.SettingConstants.*;

/**
 * Presenter for {@link TextSettingOutput} that implements {@link TextSettingOutputBoundary}.
 * Provides access to display-ready text settings: color, font, and size.
 */
public class TextSettingPresenter implements TextSettingOutputBoundary {

    /**
     * The text color.
     */
    private Color fg;

    /**
     * The font name.
     */
    private String font;

    /**
     * The text size in points.
     */
    private int size;

    /**
     * Constructs a presenter using the given output configuration.
     */
    TextSettingPresenter() {
        this.fg = DEFAULT_COLOR;
        this.font = DEFAULT_FONT_NAME;
        this.size = DEFAULT_FONT_SIZE;
    }

    public void updateUI(TextSettingOutput output) {
        this.fg = output.getColor();
        this.font = output.getFont();
        this.size = output.getSize();
    }

    /**
     * Returns the text color.
     *
     * @return the color
     */
    @Override
    public Color getColor() {
        return fg;
    }

    /**
     * Returns the font name.
     *
     * @return the font
     */
    @Override
    public String getFont() {
        return font;
    }

    /**
     * Returns the text size.
     *
     * @return the size
     */
    @Override
    public int getSize() {
        return size;
    }
}
