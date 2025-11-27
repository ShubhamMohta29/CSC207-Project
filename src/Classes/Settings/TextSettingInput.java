package Classes.Settings;

import static Classes.Settings.SettingConstants.*;

public class TextSettingInput {
    private String style = DEFAULT_FONT_NAME;
    private  int size = DEFAULT_FONT_SIZE;
    private String color = NAME_BLACK;

    public TextSettingInput(String color, int size, String style) {
        if (color == null || size == 0 || style == null){
            return;
        }
        this.color = color;
        this.size = size;
        this.style = style;
    }

    public String getColor(){
        return color;
    }

    public String getStyle(){
        return style;
    }

    public int getSize(){
        return size;
    }


}