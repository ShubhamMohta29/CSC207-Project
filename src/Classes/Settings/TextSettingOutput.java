package Classes.Settings;

import java.awt.Color;
import java.awt.Font;

import static Classes.Settings.SettingConstants.*;

public class TextSettingOutput {
    private final Color color;
    private final String font;
    private final int size;

    public TextSettingOutput(Color color, String font, int size){
        this.color = color;
        this.font = font;
        this.size = size;
    }

    public TextSettingOutput(){
        this.color = DEFAULT_COLOR;
        this.size = DEFAULT_FONT_SIZE;
        this.font = DEFAULT_FONT_NAME;

    }

    public Color getColor(){
        return color;
    }
    public String getFont(){
        return font;
    }
    public int getSize(){ return size;}


}
