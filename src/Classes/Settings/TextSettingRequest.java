package Classes.Settings;

public class TextSettingRequest {
    public String style;
    public int size;
    public String color;

    public TextSettingRequest(String color, int size, String style) {
        this.color = color;
        this.size = size;
        this.style = style;
    }
}