package Classes.Settings;

import java.awt.Color;
import java.awt.Font;
import java.io.*;

public class TextSetting extends Setting {
    private int textSize;
    private Color textColor;
    private Font textStyle;
    private String style;
    final private String filePath;


    public TextSetting(String filePath) {
        /**
         * extract saved setting from the csv file
         * When can't find filePath, it creates a csv file with default settings
         */
        this.filePath = filePath;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Settings file not found — creating one with default value...");
            setDefaultSettings();
            saveSettingsToCSV();
        } else {
            loadSettingsFromCSV();
        }
    }

    // Default constructor (if no file path given)
    public TextSetting() {
        /**
         * reads from a fixed filePath when no filePath is given
         */
        this.filePath = ("settings.csv");
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Settings file not found — creating one with default value...");
            setDefaultSettings();
            saveSettingsToCSV();  // create file with defaults
        } else {
            loadSettingsFromCSV();
        }
    }


    private void loadSettingsFromCSV() {
        /**
         * read and parse setting info from csv, the format is (textSize,textColorR,textColorG,textColorB,style)
         */
        try (BufferedReader info = new BufferedReader(new FileReader(filePath))) {
            String header = info.readLine();
            String line = info.readLine();

            if (line != null) {
                String[] values = line.split(",");

                this.textSize = Integer.parseInt(values[0]);
                int r = Integer.parseInt(values[1]);
                int g = Integer.parseInt(values[2]);
                int b = Integer.parseInt(values[3]);
                this.style = values[4].trim();

                this.textColor = new Color(r, g, b);
                this.textStyle = new Font(this.style, Font.PLAIN, this.textSize);
            } else {
                System.out.println("Empty settings file — loading defaults.");
                setDefaultSettings();
                saveSettingsToCSV();
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading settings file: " + e.getMessage());
            setDefaultSettings();
            saveSettingsToCSV();
        }
    }


    private void saveSettingsToCSV() {
        /**
         * write the current setting to the csv file with format (textSize,textColorR,textColorG,textColorB,style)
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
            bw.write("textSize,textColorR,textColorG,textColorB,style\n");
            bw.write(String.format("%d,%d,%d,%d,%s\n",
                    this.textSize,
                    this.textColor.getRed(),
                    this.textColor.getGreen(),
                    this.textColor.getBlue(),
                    this.style));
            System.out.println("Settings file created/updated at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving settings file: " + e.getMessage());
        }
    }

    private void setDefaultSettings() {
        /**
         * set the SettingClass to a specific default value
         */
        this.textSize = 13;
        this.textColor = new Color(100, 50, 200);
        this.style = "Arial";
        this.textStyle = new Font(this.style, Font.PLAIN, this.textSize);
    }


    public int getTextSize() {
        return this.textSize;
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public Font getTextStyle() {
        return this.textStyle;
    }
    // all setter updates settings.csv

    public void setColor(Color color) {
        this.textColor = color;
        saveSettingsToCSV();
    }

    public void setSize(int size) {
        this.textSize = size;
        this.textStyle = new Font(this.style, Font.PLAIN, this.textSize);
        saveSettingsToCSV();
    }

    public void setStyle(String change) {
        this.style = change;
        this.textStyle = new Font(this.style, Font.PLAIN, this.textSize);
        saveSettingsToCSV();
    }
}