package Classes.SaveCard;

import java.time.LocalDateTime;

public class SaveCardInteractor implements SaveCardInputBoundary {

    private final SaveCardDataAccessInterface dataAccess;
    private final SaveCardOutputBoundary presenter;

    public SaveCardInteractor(SaveCardDataAccessInterface dataAccess,
                              SaveCardOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public SaveCardResponseModel save(SaveCardRequestModel requestModel) {
        try {
            if (dataAccess.cardExists(requestModel.getCardName())) {
                return presenter.prepareFailView("Card name already exists.");
            }

            dataAccess.saveCard(requestModel.getCardName(), requestModel.getCardImage());

            return presenter.prepareSuccessView(new SaveCardResponseModel(
                    requestModel.getCardName(),
                    LocalDateTime.now()
            ));
        } catch (Exception e) {
            return presenter.prepareFailView("Failed to save card: " + e.getMessage());
        }
    }
}
