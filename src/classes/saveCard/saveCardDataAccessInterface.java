package Classes.SaveCard;

import java.awt.image.BufferedImage;

public interface SaveCardDataAccessInterface {
    boolean cardExists(String cardName);
    void saveCard(String cardName, BufferedImage image) throws Exception;
}
