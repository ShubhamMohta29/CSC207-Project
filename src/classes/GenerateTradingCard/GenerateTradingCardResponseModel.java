package classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

import classes.retrieveInfo.Animal;

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

