package classes.settings;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides file-based data access for reading and saving {@link textSetting}
 * objects. Handles loading from a CSV file and writing updates back to disk.
 */
public class textSettingDataAccess implements textSettingGateway {

    /**
     * Path to the settings CSV file.
     */
    private final String filePath;

    /**
     * Creates a new data access object that reads from and writes to the given file.
     *
     * @param filePath the path of the settings file
     */
    public textSettingDataAccess(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads text settings from the CSV file.
     * If the file does not exist or is invalid, default settings are returned.
     *
     * @return a {@link textSetting} object representing stored or default values
     */
    public textSetting load() {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println(settingConstants.FILE_NOT_FOUND);
            textSetting defaultSetting = getDefaultSetting();
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

            return new textSetting(size, new Color(r, g, b), fontName);
        }
        catch (IOException | NumberFormatException exception) {
            System.err.println(settingConstants.FAILED_TO_LOAD + exception.getMessage());
            return getDefaultSetting();
        }
    }

    /**
     * Saves text settings to the CSV file, overwriting previous content.
     *
     * @param setting the settings to save
     */
    public void save(textSetting setting) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(settingConstants.SAVE_SETTING_HEADER_REGEX);
            bw.write(String.format(settingConstants.SAVE_SETTING_INFO_FORMAT,
                    setting.getTextSize(),
                    setting.getTextColor().getRed(),
                    setting.getTextColor().getGreen(),
                    setting.getTextColor().getBlue(),
                    setting.getFontName()));
        }
        catch (IOException ex) {
            System.err.println(settingConstants.FAILED_TO_SAVE + ex.getMessage());
        }
    }

    /**
     * Returns the system default text settings.
     *
     * @return a default {@link textSetting}
     */
    private textSetting getDefaultSetting() {
        return new textSetting(
                settingConstants.DEFAULT_FONT_SIZE,
                settingConstants.ERROR_COLOR,
                settingConstants.DEFAULT_FONT_NAME
        );
    }
}
