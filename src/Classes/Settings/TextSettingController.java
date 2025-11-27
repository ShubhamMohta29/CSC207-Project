package Classes.Settings;


public class TextSettingController {
    private TextSettingInputBoundary config;
    private final String filePath;

    public TextSettingController(String filePath) {
        this.config = new TextSettingInteractor(filePath);
        this.filePath = filePath;
    }

    public void updateSettings(String color, int size, String style) {
        TextSettingInput request = new TextSettingInput(color, size, style);
        config.editSettings(request);
        config = new TextSettingInteractor(filePath);
    }

    public ViewModel getViewModel(){
        TextSettingOutputBoundary presenter = new TextSettingPresenter(config.getOutput());
        TextSettingOutput output = new TextSettingOutput(
                presenter.getColor(),
                presenter.getFont(),
                presenter.getSize());
        return new ViewModel(output);

    }

}