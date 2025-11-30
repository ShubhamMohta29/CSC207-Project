package Classes.Settings;

import java.awt.*;

public interface TextSettingInputBoundary {

    void editSettings(TextSettingInput request);

    Color getTextColor();

    String getFontName();

    int getTextSize();
}
