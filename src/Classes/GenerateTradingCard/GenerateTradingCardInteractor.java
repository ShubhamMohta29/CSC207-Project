package Classes.GenerateTradingCard;

import java.awt.image.BufferedImage;

import Classes.Animal;

public class GenerateTradingCardInteractor implements GenerateTradingCardInputBoundary {

    private final GenerateTradingCardOutputBoundary presenter;
    private final CardImageGenerator generator;

    public GenerateTradingCardInteractor(GenerateTradingCardOutputBoundary presenter,
                                         CardImageGenerator generator) {
        this.presenter = presenter;
        this.generator = generator;
    }

    @Override
    public TradingCardViewModel generate(GenerateTradingCardRequestModel requestModel) {
        final Animal animal = requestModel.getAnimal();
        final BufferedImage img = generator.generate(animal);
        final GenerateTradingCardResponseModel response =
                new GenerateTradingCardResponseModel(img, animal);
        return presenter.prepareSuccessView(response);
    }
}
