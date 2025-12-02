package classes.saveCard;

import java.time.LocalDateTime;

public class saveCardInteractor implements saveCardInputBoundary {

    private final saveCardDataAccessInterface dataAccess;
    private final saveCardOutputBoundary presenter;

    public saveCardInteractor(saveCardDataAccessInterface dataAccess,
                              saveCardOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public saveCardResponseModel save(saveCardRequestModel requestModel) {
        try {
            if (dataAccess.cardExists(requestModel.getCardName())) {
                return presenter.prepareFailView("Card name already exists.");
            }

            dataAccess.saveCard(requestModel.getCardName(), requestModel.getCardImage());

            return presenter.prepareSuccessView(new saveCardResponseModel(
                    requestModel.getCardName(),
                    LocalDateTime.now()
            ));
        } catch (Exception e) {
            return presenter.prepareFailView("Failed to save card: " + e.getMessage());
        }
    }
}
