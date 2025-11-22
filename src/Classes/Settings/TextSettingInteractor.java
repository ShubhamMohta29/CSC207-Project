package Classes.Settings;
import java.awt.*;

public class TextSettingInteractor implements TextSettingInputBoundary {

    private final TextSettingDataAccess settingFetcher;
    private TextSetting configuration;

    public TextSettingInteractor(String filePath) {
        this.settingFetcher = new TextSettingDataAccess(filePath);
        this.configuration = settingFetcher.load();
    }

    @Override
    public void editSettings(TextSettingRequest request) {

        // Color mapping
        switch (request.color.toLowerCase()) {
            case "purple" -> configuration.setTextColor(new Color(70, 20, 124));
            case "blue" -> configuration.setTextColor(new Color(0, 81, 161));
            case "green" -> configuration.setTextColor(new Color(28, 101, 3));
            default -> configuration.setTextColor(Color.BLACK);
        }

        // Size mapping
        int finalSize = switch (request.size) {
            case 1 -> 13;
            case 2 -> 14;
            case 4 -> 16;
            case 5 -> 17;
            default -> 15;
        };
        configuration.setTextSize(finalSize);

        // Style (font name)
        configuration.setTextStyle(request.style);

        // Save changes
        settingFetcher.save(configuration);
    }

    // Optional getters if needed by presenter or UI
    public Color getColor() {
        return configuration.getTextColor();
    }

    public int getSize() {
        return configuration.getTextSize();
    }

    public String getStyleName() {
        return configuration.getFontName();
    }

    public Font getStyle() {
        return configuration.getFont();
    }
}
