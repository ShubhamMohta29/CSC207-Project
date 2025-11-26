package Classes.Settings;


import javax.swing.*;
import java.awt.*;

public class TextSettingController {
    private TextSettingInputBoundary config;
    private TextSettingOutputBoundary presenter;
    private final String filePath;

    public TextSettingController(String filePath) {
        this.config = new TextSettingInteractor(filePath);
        this.presenter = new TextSettingPresenter((TextSettingInteractor) config);
        this.filePath = filePath;
    }

    public void updateSettings(String color, int size, String style) {
        TextSettingRequest request = new TextSettingRequest(color, size, style);
        config.editSettings(request);
        this.presenter = new TextSettingPresenter((TextSettingInteractor) config);
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

}