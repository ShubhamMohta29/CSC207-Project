package classes.settings;

/**
 * Interface for data access operations for {@link textSetting}.
 * Provides methods to load and save text settings.
 */
public interface textSettingGateway {

    /**
     * Loads text settings from a data source.
     *
     * @return the loaded {@link textSetting} object
     */
    textSetting load();

    /**
     * Saves the given text settings to a data source.
     *
     * @param setting the {@link textSetting} object to save
     */
    void save(textSetting setting);
}
