package classes.settings;

import java.awt.Color;

/**
 * Interactor implementing {@link textSettingInputBoundary}.
 * Handles business logic for editing and retrieving text settings.
 */
public class textSettingInteractor implements textSettingInputBoundary {

    private final textSettingGateway settingFetcher;
    private final textSetting config;

    /**
     * Creates a new interactor with the specified settings file path.
     *
     * @param filePath the path to the settings file
     */
    public textSettingInteractor(String filePath) {
        this.settingFetcher = new textSettingDataAccess(filePath);
        this.config = settingFetcher.load();
    }

    /**
     * Updates the current text settings based on the provided request model.
     *
     * <p>This method:
     * <ul>
     *     <li>Maps color names to predefined color constants</li>
     *     <li>Maps size constants to actual font size values</li>
     *     <li>Updates the in-memory {@link textSetting} configuration</li>
     *     <li>Saves the updated configuration using the gateway</li>
     * </ul>
     *
     * @param request a {@link textSettingInput} containing the new text settings
     */
    @Override
    public void editSettings(textSettingInput request) {
        Color finalColor = switch (request.getColor().toLowerCase()) {
            case settingConstants.NAME_PURPLE -> settingConstants.PURPLE;
            case settingConstants.NAME_BLUE -> settingConstants.BLUE;
            case settingConstants.NAME_GREEN -> settingConstants.GREEN;
            default -> settingConstants.DEFAULT_COLOR;
        };

        int finalSize = switch (request.getSize()) {
            case settingConstants.ONE -> settingConstants.FONT_SIZE_ONE;
            case settingConstants.TWO -> settingConstants.FONT_SIZE_TWO;
            case settingConstants.FOUR -> settingConstants.FONT_SIZE_FOUR;
            case settingConstants.FIVE -> settingConstants.FONT_SIZE_FIVE;
            default -> settingConstants.DEFAULT_FONT_SIZE;
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
