package classes.saveCard;

import java.awt.image.BufferedImage;

public interface saveCardDataAccessInterface {
    boolean cardExists(String cardName);
    void saveCard(String cardName, BufferedImage image) throws Exception;
}
