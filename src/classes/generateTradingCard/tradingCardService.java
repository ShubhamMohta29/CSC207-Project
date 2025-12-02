package Classes.GenerateTradingCard;

import Classes.retrieveInfo.Animal;

/**
 * Handles trading card generation.
 */
public final class TradingCardService {

    private final GenerateTradingCardController controller;

    /**
     * Builds the controller + interactor + presenter + image generator.
     */
    public TradingCardService() {
        TradingCardPresenter presenter = new TradingCardPresenter();
        CardImageGenerator generator = new Java2dCardGenerator();
        GenerateTradingCardInputBoundary interactor =
                new GenerateTradingCardInteractor(presenter, generator);
        this.controller = new GenerateTradingCardController(interactor);
    }

    /**
     * Runs the generation and returns a view model.
     */
    public TradingCardViewModel create(final Animal animal) {
        return controller.generateCard(animal);
    }
}