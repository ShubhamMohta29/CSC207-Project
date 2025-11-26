package Classes.Settings;


import javax.swing.*;
import java.awt.*;

public class TextSettingController {
    private final TextSettingInputBoundary config;

    public TextSettingController(String filePath) {
        this.config = new TextSettingInteractor(filePath);
    }

    public void updateChangesAll(String color, int size, String style) {
        TextSettingRequest request = new TextSettingRequest(color, size, style);
        config.editSettings(request);
    }

    public void updateALL(Window obj) {
        // Display the UI changes
        config.updateALL(obj);
    }

    public void updateALL(JDialog obj) {
        // Display the UI changes
        config.updateALL(obj);
    }

    public void updateALL(JFrame obj) {
        // Display the UI changes
        config.updateALL(obj);
    }

    public Color getColor() {
        return config.getColor();
    }

    public int getSize() {
        return config.getSize();
    }

    public String getStyleName() {
        return config.getStyleName();
    }

    public Font getStyle() {
        return config.getStyle();
    }

}