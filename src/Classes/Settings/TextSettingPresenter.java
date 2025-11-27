package Classes.Settings;

import java.awt.Color;

/**
 * Presenter for {@link TextSettingOutput} that implements {@link TextSettingOutputBoundary}.
 * Provides access to display-ready text settings: color, font, and size.
 */
public class TextSettingPresenter implements TextSettingOutputBoundary {

    /**
     * The text color.
     */
    private final Color fg;

    /**
     * The font name.
     */
    private final String font;

    /**
     * The text size in points.
     */
    private final int size;

    /**
     * Constructs a presenter using the given output configuration.
     *
     * @param config the {@link TextSettingOutput} containing the text settings
     */
    TextSettingPresenter(TextSettingOutput config) {
        this.fg = config.getColor();
        this.font = config.getFont();
        this.size = config.getSize();
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
