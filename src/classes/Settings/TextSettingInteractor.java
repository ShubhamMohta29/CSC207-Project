package classes.Settings;

import java.awt.Color;

import static classes.Settings.SettingConstants.*;

/**
 * Interactor implementing {@link TextSettingInputBoundary}.
 * Handles business logic for editing and retrieving text settings.
 */
public class TextSettingInteractor implements TextSettingInputBoundary {

    /**
     * Data access object for loading and saving text settings.
     */
    private final TextSettingGateway settingFetcher;

    /**
     * The current text setting configuration.
     */
    private final TextSetting config;

    /**
     * Creates a new interactor with the specified settings file path.
     *
     * @param filePath path to the settings file
     */
    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.config = settingFetcher.load();
    }

    /**
     * Updates text settings based on the input request.
     * Maps color and size inputs to predefined constants, updates the config,
     * saves it, and refreshes the presenter.
     *
     * @param request the {@link TextSettingInput} containing new values
     */
    @Override
    public void editSettings(TextSettingInput request) {
        Color finalColor = switch (request.getColor().toLowerCase()) {
            case NAME_PURPLE -> PURPLE;
            case NAME_BLUE -> BLUE;
            case NAME_GREEN -> GREEN;
            default -> DEFAULT_COLOR;
        };

        int finalSize = switch (request.getSize()) {
            case 1 -> FONT_SIZE_ONE;
            case 2 -> FONT_SIZE_TWO;
            case 4 -> FONT_SIZE_FOUR;
            case 5 -> FONT_SIZE_FIVE;
            default -> DEFAULT_FONT_SIZE;
        };

        config.setTextColor(finalColor);
        config.setTextSize(finalSize);
        config.setFontName(request.getStyle());

        settingFetcher.save(config);
    }


    public Color getTextColor() {
        return config.getTextColor();
    }

    public String getFontName() {
        return config.getFontName();
    }

    public int getTextSize() {
        return config.getTextSize();
    }

}
