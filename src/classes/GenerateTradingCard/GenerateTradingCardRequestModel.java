package classes.GenerateTradingCard;

import classes.retrieveInfo.Animal;

public class GenerateTradingCardRequestModel {
    private final Animal animal;

    public GenerateTradingCardRequestModel(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
