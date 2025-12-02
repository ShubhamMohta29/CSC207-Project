package classes.generateTradingCard;

import java.awt.image.BufferedImage;

public class tradingCardViewModel {
    private BufferedImage image;
    private String animalName;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
}
