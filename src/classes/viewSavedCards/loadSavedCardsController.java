package Classes.ViewSavedCards;

public class LoadSavedCardsController {

    private final LoadSavedCardsInputBoundary interactor;

    public LoadSavedCardsController(LoadSavedCardsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public LoadSavedCardsResponseModel load() {
        return interactor.load(new LoadSavedCardsRequestModel());
    }
}
