package Classes.GenerateTradingCard;

import Classes.retrieveInfo.Animal;

public class GenerateTradingCardRequestModel {
    private final Animal animal;

    public GenerateTradingCardRequestModel(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
