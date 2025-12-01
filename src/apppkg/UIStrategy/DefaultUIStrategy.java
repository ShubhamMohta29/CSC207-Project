package apppkg.UIStrategy;

import java.awt.*;

public class DefaultUIStrategy implements UIStyleStrategy {
    public void apply(Component comp, Color c, Font font) {
        comp.setFont(font);
        comp.setForeground(c);
    }
}
