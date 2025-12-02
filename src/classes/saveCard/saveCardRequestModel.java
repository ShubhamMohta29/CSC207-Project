package classes.saveCard;

import java.awt.image.BufferedImage;

public class saveCardRequestModel {
    private final String cardName;
    private final BufferedImage cardImage;

    public saveCardRequestModel(String cardName, BufferedImage cardImage) {
        this.cardName = cardName;
        this.cardImage = cardImage;
    }

    public String getCardName() { return cardName; }
    public BufferedImage getCardImage() { return cardImage; }
}
