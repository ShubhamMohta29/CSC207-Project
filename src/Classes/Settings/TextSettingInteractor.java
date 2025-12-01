package Classes.Settings;

import java.awt.Color;

/**
 * Interactor implementing {@link TextSettingInputBoundary}.
 * Handles business logic for editing and retrieving text settings.
 */
public class TextSettingInteractor implements TextSettingInputBoundary {

    private final TextSettingGateway settingFetcher;
    private final TextSetting config;

    /**
     * Creates a new interactor with the specified settings file path.
     *
     * @param filePath the path to the settings file
     */
    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.config = settingFetcher.load();
    }

    /**
     * Updates the current text settings based on the provided request model.
     *
     * <p>This method:
     * <ul>
     *     <li>Maps color names to predefined color constants</li>
     *     <li>Maps size constants to actual font size values</li>
     *     <li>Updates the in-memory {@link TextSetting} configuration</li>
     *     <li>Saves the updated configuration using the gateway</li>
     * </ul>
     *
     * @param request a {@link TextSettingInput} containing the new text settings
     */
    @Override
    public void editSettings(TextSettingInput request) {
        Color finalColor = switch (request.getColor().toLowerCase()) {
            case SettingConstants.NAME_PURPLE -> SettingConstants.PURPLE;
            case SettingConstants.NAME_BLUE -> SettingConstants.BLUE;
            case SettingConstants.NAME_GREEN -> SettingConstants.GREEN;
            default -> SettingConstants.DEFAULT_COLOR;
        };

        int finalSize = switch (request.getSize()) {
            case SettingConstants.ONE -> SettingConstants.FONT_SIZE_ONE;
            case SettingConstants.TWO -> SettingConstants.FONT_SIZE_TWO;
            case SettingConstants.FOUR -> SettingConstants.FONT_SIZE_FOUR;
            case SettingConstants.FIVE -> SettingConstants.FONT_SIZE_FIVE;
            default -> SettingConstants.DEFAULT_FONT_SIZE;
        };

        config.setTextColor(finalColor);
        config.setTextSize(finalSize);
        config.setFontName(request.getStyle());

        settingFetcher.save(config);
    }

    /**
     * Returns the current text color.
     *
     * @return the configured {@link Color}
     */
    public Color getTextColor() {
        return config.getTextColor();
    }

    /**
     * Returns the currently configured font name.
     *
     * @return a {@link String} representing the font name
     */
    public String getFontName() {
        return config.getFontName();
    }

    /**
     * Returns the current text size.
     *
     * @return the numeric font size
     */
    public int getTextSize() {
        return config.getTextSize();
    }

}
