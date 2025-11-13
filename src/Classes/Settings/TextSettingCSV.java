package Classes.Settings;

import java.awt.Color;
import java.io.*;

public class TextSettingCSV {
    private final String filePath;

    public TextSettingCSV(String filePath) {
        this.filePath = filePath;
    }

    public TextSetting load() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Settings file not found — creating default...");
            TextSetting defaultSetting = getDefaultSetting();
            save(defaultSetting);
            return defaultSetting;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // Skip header
            String line = br.readLine();

            if (line == null || line.trim().isEmpty()) {
                return getDefaultSetting();
            }

            String[] values = line.split(",");
            int size = Integer.parseInt(values[0]);
            int r = Integer.parseInt(values[1]);
            int g = Integer.parseInt(values[2]);
            int b = Integer.parseInt(values[3]);
            String fontName = values[4].trim();

            return new TextSetting(size, new Color(r, g, b), fontName);

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading settings: " + e.getMessage());
            return getDefaultSetting();
        }
    }

    public void save(TextSetting setting) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("textSize,textColorR,textColorG,textColorB,fontName\n");
            bw.write(String.format("%d,%d,%d,%d,%s\n",
                    setting.getTextSize(),
                    setting.getTextColor().getRed(),
                    setting.getTextColor().getGreen(),
                    setting.getTextColor().getBlue(),
                    setting.getFontName()));
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    private TextSetting getDefaultSetting() {
        return new TextSetting(13, new Color(100, 50, 200), "Arial");
    }
}