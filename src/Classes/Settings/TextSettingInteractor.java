package Classes.Settings;

import java.awt.*;

import static Classes.Settings.SettingConstants.*;

public class TextSettingInteractor implements TextSettingInputBoundary {

    private final TextSettingDataAccess settingFetcher;
    private TextSettingOutputBoundary presenter;

    private final TextSetting config;

    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.presenter = new TextSettingPresenter(new TextSettingOutput());
        this.config = settingFetcher.load();

    }

    @Override
    public void editSettings(TextSettingInput request) {

        // Color mapping
        Color finalColor = switch (request.getColor().toLowerCase()) {
            case NAME_PURPLE -> PURPLE;
            case NAME_BLUE -> BLUE;
            case NAME_GREEN -> GREEN;
            default -> DEFAULT_COLOR;
        };
        // Size mapping
        int finalSize = switch (request.getSize()) {
            case 1 -> FONT_SIZE_ONE;
            case 2 -> FONT_SIZE_TWO;
            case 4 -> FONT_SIZE_FOUR;
            case 5 -> FONT_SIZE_FIVE;
            default -> DEFAULT_FONT_SIZE;
        };
        // Color（font color）
        config.setTextColor(finalColor);
        // Font Size
        config.setTextSize(finalSize);
        // String (font name)
        config.setFontName(request.getStyle());
        // Save changes
        settingFetcher.save(config);

        TextSettingOutput output = new TextSettingOutput(
                config.getTextColor(),
                config.getFontName(),
                config.getTextSize());

        presenter =  new TextSettingPresenter(output);


    }

    public TextSettingPresenter getOutput(){
        TextSettingOutput output = new TextSettingOutput(
                config.getTextColor(),
                config.getFontName(),
                config.getTextSize());

        return new TextSettingPresenter(output);
    }
}
