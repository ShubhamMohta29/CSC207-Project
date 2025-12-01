package Classes.ViewSavedCards;

import java.awt.image.BufferedImage;
import java.util.List;

public class LoadSavedCardsResponseModel {
    private final List<String> cardNames;
    private final List<BufferedImage> cardImages;

    public LoadSavedCardsResponseModel(List<String> names, List<BufferedImage> images) {
        this.cardNames = names;
        this.cardImages = images;
    }

    public List<String> getCardNames() { return cardNames; }
    public List<BufferedImage> getCardImages() { return cardImages; }
}
