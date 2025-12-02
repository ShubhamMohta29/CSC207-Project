package classes.generateTradingCard;

import java.awt.image.BufferedImage;

import classes.retrieveInfo.animal;

/**
 * A generator interface responsible for producing a trading card image.
 */
public interface cardImageGenerator {

    /**
     * Creates a visual trading card of the animal.
     *
     * @param animal to render on the card
     * @return a BufferedImage containing the finished card artwork
     */
    BufferedImage generate(animal animal);
}
