package Classes.Settings;
import java.awt.Font;
import java.awt.Color;

public class ReaderEditor {
    private TextSetting configuration;
    final private TextSettingCSV settingFetcher;
    final private String filePath;

    public ReaderEditor(String filePath) {
        this.filePath = filePath;
        this.settingFetcher = new TextSettingCSV(filePath);
        this.configuration = settingFetcher.load();
    }

    public void editSettings(String color, int size, String style) {

        if (color.equals("purple")) {
            configuration.setTextColor(new Color (70, 20, 124));
        }else if (color.equals("blue")) {
            configuration.setTextColor(new Color(0, 81, 161));
        }else if (color.equals("green")){
            configuration.setTextColor(new Color(28, 101, 3));
        }else {
            configuration.setTextColor(new Color(0, 0, 0));
        }

        if (size == 1){
            configuration.setTextSize(13);
        }else if (size == 2){
            configuration.setTextSize(14);
        }else if (size == 4){
            configuration.setTextSize(16);
        }else if (size == 5){
            configuration.setTextSize(17);
        }else {
            configuration.setTextSize(15);
        }

        if (style.equals("Times New Roman")){
            configuration.setTextStyle("Times New Roman");
        }else if ((style.equals("Serif"))){
            configuration.setTextStyle("Serif");
        }else if (style.equals("DejaVu Sans")){
            configuration.setTextStyle("DejaVu Sans");
        }else{
            configuration.setTextStyle("Arial");
        }

        settingFetcher.save(configuration);

    }// changes for changes

    public Color getColor() {
        return this.configuration.getTextColor();
    }

    public Font getStyle() {
        return this.configuration.getFont();
    }

    public int getSize() {
        return this.configuration.getTextSize();
    }

}
