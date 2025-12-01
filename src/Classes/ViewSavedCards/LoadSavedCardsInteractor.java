package Classes.ViewSavedCards;

public class LoadSavedCardsInteractor implements LoadSavedCardsInputBoundary {

    private final LoadSavedCardsDataAccessInterface dataAccess;
    private final LoadSavedCardsOutputBoundary presenter;

    public LoadSavedCardsInteractor(LoadSavedCardsDataAccessInterface dataAccess,
                                    LoadSavedCardsOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public LoadSavedCardsResponseModel load(LoadSavedCardsRequestModel requestModel) {
        return presenter.prepareSuccessView(
                new LoadSavedCardsResponseModel(
                        dataAccess.loadAllCardNames(),
                        dataAccess.loadAllCardImages()
                )
        );
    }
}
