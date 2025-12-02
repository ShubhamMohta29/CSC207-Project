package classes.viewSavedCards;

import java.util.List;
import java.awt.image.BufferedImage;

public interface loadSavedCardsDataAccessInterface {
    List<String> loadAllCardNames();
    List<BufferedImage> loadAllCardImages();
}
