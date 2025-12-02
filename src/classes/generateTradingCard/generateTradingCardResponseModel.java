package classes.generateTradingCard;

import java.awt.image.BufferedImage;

import classes.retrieveInfo.animal;

public class generateTradingCardResponseModel {

    private final BufferedImage image;
    private final animal animal;

    public generateTradingCardResponseModel(BufferedImage image, animal animal) {
        this.image = image;
        this.animal = animal;
    }

    public BufferedImage getImage() {
        return image;
    }

    public animal getAnimal() {
        return animal;
    }
}

