package Classes.ViewSavedCards;

import java.util.List;
import java.awt.image.BufferedImage;

public interface LoadSavedCardsDataAccessInterface {
    List<String> loadAllCardNames();
    List<BufferedImage> loadAllCardImages();
}
