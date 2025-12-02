package classes.viewSavedCards;

public class loadSavedCardsController {

    private final loadSavedCardsInputBoundary interactor;

    public loadSavedCardsController(loadSavedCardsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public loadSavedCardsResponseModel load() {
        return interactor.load(new loadSavedCardsRequestModel());
    }
}
