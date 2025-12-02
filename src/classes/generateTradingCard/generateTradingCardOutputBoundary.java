package classes.generateTradingCard;

/**
 * Defines the output boundary for generating a trading card.
 */
public interface generateTradingCardOutputBoundary {

    /**
     * @param response the response model containing image and animal data
     * @return a populated TradingCardViewModel
     */
    tradingCardViewModel prepareSuccessView(generateTradingCardResponseModel response);
}

