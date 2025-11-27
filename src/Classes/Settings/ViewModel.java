package Classes.Settings;

import java.awt.*;

import static Classes.Settings.SettingConstants.DEFAULT_STYLE;

public class ViewModel {
    private final Color color;
    private final Font font;

    public ViewModel(Color color, String font, int size){
        this.color = color;
        this.font = new Font (font,
                    DEFAULT_STYLE,
                    size);
    }

    public Color getColor(){
        return color;
    }

    public Font getFont(){
            return font;
    }
}
