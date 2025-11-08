package Classes;
import java.awt.Color;
import java.awt.Font;

public class SettingClass {
    private int textSize;
    private Color textColor;
    private Font textStyle;

    public SettingClass() {
        this.textSize = 13;
        this.textColor = new Color(100, 50, 200);
        this.textStyle = new Font("Arial", 0, this.textSize);
    }
    public int getTextSize() {
        return this.textSize;
        }
    public Color getTextColor() {
        return this.textColor;
        }
    public Font getTextStyle()  {
        return this.textStyle;
    }

    public void setColor(Color name) {
        this.textColor = name;
    }

    public void setSize(int size) {
        this.textSize = size;
    }

    public void setStyle(String style) {
        this.textStyle = new Font(style, 0, this.textSize);
    }

}

