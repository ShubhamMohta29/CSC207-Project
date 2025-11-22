package Classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

public class GenerateTradingCardResponseModel {
    private final BufferedImage image;

    public GenerateTradingCardResponseModel(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}