package classes.Settings;

/**
 * Interface for data access operations for {@link TextSetting}.
 * Provides methods to load and save text settings.
 */
public interface TextSettingGateway {

    /**
     * Loads text settings from a data source.
     *
     * @return the loaded {@link TextSetting} object
     */
    TextSetting load();

    /**
     * Saves the given text settings to a data source.
     *
     * @param setting the {@link TextSetting} object to save
     */
    void save(TextSetting setting);
}
