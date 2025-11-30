package AppPkg.UIStrategy;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneStrategy implements UIStyleStrategy {
    public void apply(Component comp, Color c, Font font) {


        JScrollPane sp = (JScrollPane) comp;
        sp.setFont(font);
        sp.setForeground(c);

        Component view = sp.getViewport().getView();
        if (view instanceof Container) {
            Container container = (Container) view;
            container.setFont(font);
            container.setForeground(c);
        }
    }
}
