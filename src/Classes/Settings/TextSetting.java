package Classes.Settings;

import java.awt.Color;
import java.awt.Font;

public class TextSetting extends Settings {
    private int textSize;
    private Color textColor;
    private String fontName;


    public TextSetting(int textSize, Color textColor, String fontName) {
        this.textSize = textSize;
        this.textColor = textColor;
        this.fontName = fontName;
    }

    public int getTextSize() {
        return textSize;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getFontName() {
        return fontName;
    }

    public Font getFont() {
        return new Font(fontName, Font.PLAIN, textSize);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTextStyle(String fontName) {
        this.fontName = fontName;
    }
}