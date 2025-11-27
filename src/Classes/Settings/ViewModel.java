package Classes.Settings;

import java.awt.*;

import static Classes.Settings.SettingConstants.DEFAULT_STYLE;

public class ViewModel {
    private final Color color;
    private final Font font;

    public ViewModel(TextSettingOutput presenter){
        this.color = presenter.getColor();
        this.font = new Font (presenter.getFont(),
                    DEFAULT_STYLE,
                    presenter.getSize());
    }

    public Color getColor(){
        return color;
    }

    public Font getFont(){
            return font;
    }
}
