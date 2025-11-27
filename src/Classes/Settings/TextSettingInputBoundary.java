package Classes.Settings;

public interface TextSettingInputBoundary {
    /**
     * Handle all the request from controller
     *
     * @param request from controller
     */
    void editSettings(TextSettingInput request);
    TextSettingOutput getOutput();
}