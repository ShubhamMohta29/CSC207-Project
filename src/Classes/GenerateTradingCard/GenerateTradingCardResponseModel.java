package Classes.GenerateTradingCard;

import Classes.Animal;
import java.awt.image.BufferedImage;

public class GenerateTradingCardResponseModel {

    private final BufferedImage image;
    private final Animal animal;

    public GenerateTradingCardResponseModel(BufferedImage image, Animal animal) {
        this.image = image;
        this.animal = animal;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Animal getAnimal() {
        return animal;
    }
}
