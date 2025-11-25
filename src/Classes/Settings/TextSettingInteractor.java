package Classes.Settings;

import javax.swing.*;
import java.awt.*;

public class TextSettingInteractor implements TextSettingInputBoundary {

    private final TextSettingDataAccess settingFetcher;
    private TextSettingOutputBoundary presenter;
    private final TextSetting config;

    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.config = settingFetcher.load();
        this.presenter = new TextSettingOutput(config);

    }

    @Override
    public void editSettings(TextSettingRequest request) {

        // Color mapping
        Color finalColor = switch (request.color.toLowerCase()) {
            case "purple" -> new Color(70, 20, 124);
            case "blue" -> new Color(0, 81, 161);
            case "green" -> new Color(28, 101, 3);
            default -> Color.BLACK;
        };

        // Size mapping
        int finalSize = switch (request.size) {
            case 1 -> 13;
            case 2 -> 14;
            case 4 -> 16;
            case 5 -> 17;
            default -> 15;
        };

        // Color（font color）
        config.setTextColor(finalColor);

        // Font Size
        config.setTextSize(finalSize);

        // String (font name)
        config.setTextStyle(request.style);

        // Save changes
        settingFetcher.save(config);

        presenter = new TextSettingOutput(config);

    }

    public void updateALL(Window obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    public void updateALL(JDialog obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    public void updateALL(JFrame obj) {
        // Display the UI changes
        presenter.presentSettingOutput(obj);
    }

    // Optional getters if needed by presenter or UI
    public Color getColor() {
        return config.getTextColor();
    }

    public int getSize() {
        return config.getTextSize();
    }

    public String getStyleName() {
        return config.getFontName();
    }

    public Font getStyle() {
        return config.getFont();
    }
}
