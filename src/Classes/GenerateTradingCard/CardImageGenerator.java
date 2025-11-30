package Classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

import Classes.Animal;

/**
 * A generator interface responsible for producing a trading card image.
 */
public interface CardImageGenerator {

    /**
     * Creates a visual trading card of the animal.
     *
     * @param animal to render on the card
     * @return a BufferedImage containing the finished card artwork
     */
    BufferedImage generate(Animal animal);
}
