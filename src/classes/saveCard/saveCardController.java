package classes.saveCard;

import java.awt.image.BufferedImage;

public class saveCardController {

    private final saveCardInputBoundary interactor;

    public saveCardController(saveCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    public saveCardResponseModel save(String cardName, BufferedImage cardImage) {
        saveCardRequestModel request = new saveCardRequestModel(cardName, cardImage);
        return interactor.save(request);
    }
}
