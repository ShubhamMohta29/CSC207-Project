package AppPkg.UIStrategy;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JPanelStrategy implements UIStyleStrategy {
    public void apply(Component comp, Color c, Font font) {

        JPanel panel = (JPanel) comp;
        Border oldBorder = panel.getBorder();
        TitledBorder border = null;

        if (oldBorder instanceof TitledBorder) {
            border = (TitledBorder) oldBorder;
            border.setTitleFont(font);
            border.setTitleColor(c);
        } else {
            panel.setFont(font);
            panel.setForeground(c);
        }

        panel.setBorder(border);

        panel.repaint();

    }
}
