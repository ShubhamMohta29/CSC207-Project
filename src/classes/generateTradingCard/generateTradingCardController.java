package classes.generateTradingCard;

import classes.retrieveInfo.animal;

public class generateTradingCardController {

    private final generateTradingCardInputBoundary interactor;

    /**
     * Creates a controller that delegates card generation to the interactor.
     *
     * @param interactor the input boundary used to generate trading cards
     */
    public generateTradingCardController(generateTradingCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Generates a trading card for the given animal by calling the use case interactor.
     *
     * @param animal the animal to place on the card
     * @return the generated trading card view model
     */
    public tradingCardViewModel generateCard(animal animal) {
        final generateTradingCardRequestModel req = new generateTradingCardRequestModel(animal);
        return interactor.generate(req);
    }
}
