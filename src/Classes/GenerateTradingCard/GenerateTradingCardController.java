package Classes.GenerateTradingCard;

import Classes.retrieveInfo.Animal;

public class GenerateTradingCardController {

    private final GenerateTradingCardInputBoundary interactor;

    /**
     * Creates a controller that delegates card generation to the interactor.
     *
     * @param interactor the input boundary used to generate trading cards
     */
    public GenerateTradingCardController(GenerateTradingCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Generates a trading card for the given animal by calling the use case interactor.
     *
     * @param animal the animal to place on the card
     * @return the generated trading card view model
     */
    public TradingCardViewModel generateCard(Animal animal) {
        final GenerateTradingCardRequestModel req = new GenerateTradingCardRequestModel(animal);
        return interactor.generate(req);
    }
}
