package classes.settings;

import java.awt.Color;

/**
 * Presenter for {@link textSettingOutput} that implements {@link textSettingOutputBoundary}.
 */
public class textSettingPresenter implements textSettingOutputBoundary {

    /**
     * The text color currently prepared for display.
     */
    private Color fg;

    /**
     * The font name currently prepared for display.
     */
    private String font;

    /**
     * The text size currently prepared for display.
     */
    private int size;

    /**
     * Constructs a presenter initialized with default text settings.
     * These default values match the application's defined fallback settings.
     */
    textSettingPresenter() {
        this.fg = settingConstants.DEFAULT_COLOR;
        this.font = settingConstants.DEFAULT_FONT_NAME;
        this.size = settingConstants.DEFAULT_FONT_SIZE;
    }

    /**
     * Updates the presenter with new output data and returns a view model containing
     * the UI-ready values.
     *
     * <p>This method:
     * <ul>
     *     <li>Extracts color, font, and size from the {@link textSettingOutput}</li>
     *     <li>Updates the presenter’s internal state</li>
     *     <li>Creates and returns a new {@link viewModel} for UI rendering</li>
     * </ul>
     *
     * @param output the output data from the interactor containing updated text settings
     * @return a {@link viewModel} containing display-ready values
     */
    public viewModel updateView(textSettingOutput output) {
        this.fg = output.getColor();
        this.font = output.getFont();
        this.size = output.getSize();

        return new viewModel(fg, font, size);
    }

    /**
     * Returns the currently stored text color.
     *
     * @return the text color prepared by the presenter
     */
    @Override
    public Color getColor() {
        return fg;
    }

    /**
     * Returns the currently stored font name.
     *
     * @return the font name prepared by the presenter
     */
    @Override
    public String getFont() {
        return font;
    }

    /**
     * Returns the currently stored font size.
     *
     * @return the text size prepared by the presenter
     */
    @Override
    public int getSize() {
        return size;
    }
}
