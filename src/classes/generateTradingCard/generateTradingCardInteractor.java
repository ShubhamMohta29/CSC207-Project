package classes.generateTradingCard;

import classes.retrieveInfo.animal;

import java.awt.image.BufferedImage;

public class generateTradingCardInteractor implements generateTradingCardInputBoundary {

    private final generateTradingCardOutputBoundary presenter;
    private final cardImageGenerator generator;

    public generateTradingCardInteractor(generateTradingCardOutputBoundary presenter,
                                         cardImageGenerator generator) {
        this.presenter = presenter;
        this.generator = generator;
    }

    @Override
    public tradingCardViewModel generate(generateTradingCardRequestModel requestModel) {
        final animal animal = requestModel.getAnimal();
        final BufferedImage img = generator.generate(animal);
        final generateTradingCardResponseModel response =
                new generateTradingCardResponseModel(img, animal);
        return presenter.prepareSuccessView(response);
    }
}
