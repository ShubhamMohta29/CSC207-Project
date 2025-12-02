package classes.generateTradingCard;

import classes.retrieveInfo.animal;

/**
 * Handles trading card generation.
 */
public final class tradingCardService {

    private final generateTradingCardController controller;

    /**
     * Builds the controller + interactor + presenter + image generator.
     */
    public tradingCardService() {
        tradingCardPresenter presenter = new tradingCardPresenter();
        cardImageGenerator generator = new java2DCardGenerator();
        generateTradingCardInputBoundary interactor =
                new generateTradingCardInteractor(presenter, generator);
        this.controller = new generateTradingCardController(interactor);
    }

    /**
     * Runs the generation and returns a view model.
     */
    public tradingCardViewModel create(final animal animal) {
        return controller.generateCard(animal);
    }
}