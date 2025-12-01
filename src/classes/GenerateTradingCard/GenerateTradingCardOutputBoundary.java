package classes.GenerateTradingCard;

/**
 * Defines the output boundary for generating a trading card.
 */
public interface GenerateTradingCardOutputBoundary {

    /**
     * @param response the response model containing image and animal data
     * @return a populated TradingCardViewModel
     */
    TradingCardViewModel prepareSuccessView(GenerateTradingCardResponseModel response);
}

