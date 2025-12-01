package Classes.SaveCard;

import java.awt.image.BufferedImage;

public class SaveCardRequestModel {
    private final String cardName;
    private final BufferedImage cardImage;

    public SaveCardRequestModel(String cardName, BufferedImage cardImage) {
        this.cardName = cardName;
        this.cardImage = cardImage;
    }

    public String getCardName() { return cardName; }
    public BufferedImage getCardImage() { return cardImage; }
}
