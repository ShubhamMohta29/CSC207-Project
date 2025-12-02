package classes.viewSavedCards;

public class loadSavedCardsInteractor implements loadSavedCardsInputBoundary {

    private final loadSavedCardsDataAccessInterface dataAccess;
    private final loadSavedCardsOutputBoundary presenter;

    public loadSavedCardsInteractor(loadSavedCardsDataAccessInterface dataAccess,
                                    loadSavedCardsOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public loadSavedCardsResponseModel load(loadSavedCardsRequestModel requestModel) {
        return presenter.prepareSuccessView(
                new loadSavedCardsResponseModel(
                        dataAccess.loadAllCardNames(),
                        dataAccess.loadAllCardImages()
                )
        );
    }
}
