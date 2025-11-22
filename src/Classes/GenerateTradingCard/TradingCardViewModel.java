package Classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

public class TradingCardViewModel {
    private final BufferedImage image;

    public TradingCardViewModel(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}