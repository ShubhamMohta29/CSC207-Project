package Classes.Settings;

import javax.swing.*;
import java.awt.*;

public interface TextSettingOutputBoundary {
    void presentSettingOutput(JFrame frame);

    void presentSettingOutput(JDialog dialog);

    void presentSettingOutput(Window window);
}
