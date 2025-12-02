package classes.generateTradingCard;

import classes.retrieveInfo.animal;

public class generateTradingCardRequestModel {
    private final animal animal;

    public generateTradingCardRequestModel(animal animal) {
        this.animal = animal;
    }

    public animal getAnimal() {
        return animal;
    }
}
