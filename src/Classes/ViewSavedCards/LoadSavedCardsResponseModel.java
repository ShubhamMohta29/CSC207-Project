package Classes.ViewSavedCards;

import Classes.retrieveInfo.Animal;
import java.awt.image.BufferedImage;
import java.util.List;

public class LoadSavedCardsResponseModel {

    private final List<Animal> animals;
    private final List<BufferedImage> images;

    public LoadSavedCardsResponseModel(List<Animal> animals, List<BufferedImage> images) {
        this.animals = animals;
        this.images  = images;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<BufferedImage> getImages() {
        return images;
    }
}
