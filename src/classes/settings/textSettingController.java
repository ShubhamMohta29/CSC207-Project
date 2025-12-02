package classes.settings;

/**
 * Controller responsible for handling user actions related to text settings.
 * It receives input from the UI, packages it into request models, and delegates
 * the update operation to the {@link textSettingInputBoundary}. After updating,
 * it refreshes the interactor to ensure the latest settings are loaded.
 */
public class textSettingController {

    private final textSettingInputBoundary config;
    private final textSettingOutputBoundary presenter;
    private final String filePath;

    /**
     * Creates a controller that manages text settings stored in the given file path.
     * This constructor initializes the interactor that performs the business logic.
     *
     * @param filePath the path to the settings file
     */
    public textSettingController(String filePath) {
        this.config = new textSettingInteractor(filePath);
        this.presenter = new textSettingPresenter();
        this.filePath = filePath;
    }

    /**
     * Updates the text settings using the provided values.
     * A request model is created and passed to the input boundary for processing.
     * After updating, the interactor is refreshed to load the new settings.
     * @param color the new text color as a string
     * @param size  the new text size in points
     * @param style the new font style or name
     */
    public void updateSettings(String color, int size, String style) {
        textSettingInput request = new textSettingInput(color, size, style);
        config.editSettings(request);
    }

    /**
     * Returns a {@link viewModel} representing the current text settings.
     * A presenter is created from the interactor’s output, and the view model is
     * constructed using the presenter’s processed values.
     * @return a {@link viewModel} containing display-ready text settings
     */
    public viewModel getViewModel() {

        textSettingOutput output = new textSettingOutput(
                config.getTextColor(),
                config.getFontName(),
                config.getTextSize()
        );

        return presenter.updateView(output);
    }
}
