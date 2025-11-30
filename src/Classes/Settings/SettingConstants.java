package Classes.Settings;

import java.awt.Color;

/**
 * Stores all Constant used in Settings.
 */
public final class SettingConstants {

    private SettingConstants() {

    }

    public static final int DEFAULT_STYLE = 0;
    public static final int DEFAULT_FONT_SIZE = 15;
    public static final int FONT_SIZE_ONE = 13;
    public static final int FONT_SIZE_TWO = 14;
    public static final int FONT_SIZE_FOUR = 16;
    public static final int FONT_SIZE_FIVE = 17;
    public static final int HEADING_FONT_SIZE = 36;

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

    public static final Color ERROR_COLOR = new Color(100, 50, 200);
    public static final Color PURPLE = new Color(70, 20, 124);
    public static final Color GREEN = new Color(28, 101, 3);
    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final Color BLUE = new Color(0, 81, 161);

    public static final String NAME_PURPLE = "purple";
    public static final String NAME_GREEN = "green";
    public static final String NAME_BLACK = "black";
    public static final String NAME_BLUE = "blue";
    public static final String DEFAULT_FONT_NAME = "Arial";
    public static final String TITLE_SETTINGS = "Settings";
    public static final String RESTORE_DEFAULT_BUTTON = "Restore Defaults";
    public static final String SAVE_SETTING_BUTTON = "Save";
    public static final String COLOR_LABEL = "Color";
    public static final String SIZE_LABEL = "Font Size";
    public static final String FONT_LABEL = "Font";
    public static final String DEFAULT_SETTINGS_FILE = "settings.csv";
    public static final String HOME_ICON_FILE = "/imagesPkg/home.png";
    public static final String SAVE_SETTING_HEADER_REGEX = "textSize,textColorR,textColorG,textColorB,fontName\n";
    public static final String SAVE_SETTING_INFO_FORMAT = "%d,%d,%d,%d,%s\n";
    public static final String FAILED_TO_LOAD = "Error loading settings:";
    public static final String FAILED_TO_SAVE = "Error saving settings:";
    public static final String FILE_NOT_FOUND = "Settings file not found — creating default...";

    public static final String[] SUPPORTED_COLORS = {NAME_BLACK, NAME_PURPLE, NAME_GREEN, NAME_BLUE};
    public static final String[] FONT_SIZE_OPTIONS = {"1", "2", "3", "4", "5"};

}
