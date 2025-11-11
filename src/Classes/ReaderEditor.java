package Classes;
import java.awt.Font;
import java.awt.Color;

public class ReaderEditor {
    private SettingClass configuration;

    public ReaderEditor(String s) {
        this.configuration = new SettingClass(s);
    }
//??
    public ReaderEditor() {
        this.configuration = new SettingClass();
    }

    public void editSettings(String color, int size, String style) {

        if (color.equals("purple")) {
            configuration.setColor(new Color(70, 20, 124));
        }else if (color.equals("blue")) {
            configuration.setColor(new Color(0, 81, 161));
        }else if (color.equals("green")){
            configuration.setColor(new Color(28, 101, 3));
        }else {
            configuration.setColor(new Color(0, 0, 0));
        }

        if (size == 1){
            configuration.setSize(11);
        }else if (size == 2){
            configuration.setSize(13);
        }else if (size == 4){
            configuration.setSize(15);
        }else if (size == 5){
            configuration.setSize(17);
        }else {
            configuration.setSize(12);
        }

        if (style.equals("tnr")){
            configuration.setStyle("Times New Roman");
        }else if ((style.equals("dsg"))){
            configuration.setStyle("Droid Sans Georgian");
        }else if (style.equals("dvs")){
            configuration.setStyle("DejaVu Sans");
        }else{
            configuration.setStyle("Arial");
        }
    }// changes for changes

    public Color getColor() {
        return this.configuration.getTextColor();
    }

    public Font getStyle() {
        return this.configuration.getTextStyle();
    }

    public int getSize() {
        return this.configuration.getTextSize();
    }
}
