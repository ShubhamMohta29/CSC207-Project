package classes.viewSavedCards;

import java.awt.image.BufferedImage;
import java.util.List;

public class loadSavedCardsResponseModel {
    private final List<String> cardNames;
    private final List<BufferedImage> cardImages;

    public loadSavedCardsResponseModel(List<String> names, List<BufferedImage> images) {
        this.cardNames = names;
        this.cardImages = images;
    }

    public List<String> getCardNames() { return cardNames; }
    public List<BufferedImage> getCardImages() { return cardImages; }
}
