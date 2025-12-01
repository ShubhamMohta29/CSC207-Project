package Classes.SaveCard;

import java.awt.image.BufferedImage;

public class SaveCardController {

    private final SaveCardInputBoundary interactor;

    public SaveCardController(SaveCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    public SaveCardResponseModel save(String cardName, BufferedImage cardImage) {
        SaveCardRequestModel request = new SaveCardRequestModel(cardName, cardImage);
        return interactor.save(request);
    }
}
