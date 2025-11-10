package Classes;
import java.awt.Color;
import java.awt.Font;

public class SettingClass {
    private int textSize;
    private Color textColor;
    private Font textStyle;
    private String style;

    public SettingClass() {
        this.textSize = 13;
        this.textColor = new Color(100, 50, 200);
        this.style = new String("Arial");
        this.textStyle = new Font( this.style, 0, this.textSize);

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
        this.textStyle = new Font(this.style, 0, this.textSize);
    }

    public void setStyle(String change) {
        this.textStyle = new Font(change, 0, this.textSize);
    }

}

