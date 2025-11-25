package Classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

public class TradingCardViewModel {

    private BufferedImage image;
    private String animalName;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setAnimalName(String name) {
        this.animalName = name;
    }

    public String getAnimalName() {
        return animalName;
    }
}