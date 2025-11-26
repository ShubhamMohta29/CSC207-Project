package Classes.Settings;

import javax.swing.*;
import java.awt.*;

public interface TextSettingInputBoundary {
    void editSettings(TextSettingRequest request);
    void updateALL(Window obj);
    void updateALL(JDialog obj);
    void updateALL(JFrame obj);
    Color getColor();
    int getSize();
    String getStyleName();
    Font getStyle();

}