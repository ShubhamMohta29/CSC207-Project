package Classes.Settings;

import javax.swing.*;
import java.awt.*;

public interface TextSettingInputBoundary {
    /**
     * Handle all the request from controller
     * @param request
     */
    void editSettings(TextSettingRequest request);
    Color getColor();
    int getSize();
    String getStyleName();
    Font getStyle();

}