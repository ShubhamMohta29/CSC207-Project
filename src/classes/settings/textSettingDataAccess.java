package Classes.Settings;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides file-based data access for reading and saving {@link TextSetting}
 * objects. Handles loading from a CSV file and writing updates back to disk.
 */
public class TextSettingDataAccess implements TextSettingGateway {

    /**
     * Path to the settings CSV file.
     */
    private final String filePath;

    /**
     * Creates a new data access object that reads from and writes to the given file.
     *
     * @param filePath the path of the settings file
     */
    public TextSettingDataAccess(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads text settings from the CSV file.
     * If the file does not exist or is invalid, default settings are returned.
     *
     * @return a {@link TextSetting} object representing stored or default values
     */
    public TextSetting load() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println(SettingConstants.FILE_NOT_FOUND);
            TextSetting defaultSetting = getDefaultSetting();
            save(defaultSetting);
            return defaultSetting;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line = br.readLine();

            if (line == null || line.trim().isEmpty()) {
                return getDefaultSetting();
            }

            String[] values = line.split(",");
            int size = Integer.parseInt(values[0]);
            int r = Integer.parseInt(values[1]);
            int g = Integer.parseInt(values[2]);
            int b = Integer.parseInt(values[2 + 1]);
            String fontName = values[2 + 2].trim();

            return new TextSetting(size, new Color(r, g, b), fontName);
        }
        catch (IOException | NumberFormatException exception) {
            System.err.println(SettingConstants.FAILED_TO_LOAD + exception.getMessage());
            return getDefaultSetting();
        }
    }

    /**
     * Saves text settings to the CSV file, overwriting previous content.
     *
     * @param setting the settings to save
     */
    public void save(TextSetting setting) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(SettingConstants.SAVE_SETTING_HEADER_REGEX);
            bw.write(String.format(SettingConstants.SAVE_SETTING_INFO_FORMAT,
                    setting.getTextSize(),
                    setting.getTextColor().getRed(),
                    setting.getTextColor().getGreen(),
                    setting.getTextColor().getBlue(),
                    setting.getFontName()));
        }
        catch (IOException ex) {
            System.err.println(SettingConstants.FAILED_TO_SAVE + ex.getMessage());
        }
    }

    /**
     * Returns the system default text settings.
     *
     * @return a default {@link TextSetting}
     */
    private TextSetting getDefaultSetting() {
        return new TextSetting(
                SettingConstants.DEFAULT_FONT_SIZE,
                SettingConstants.ERROR_COLOR,
                SettingConstants.DEFAULT_FONT_NAME
        );
    }
}
