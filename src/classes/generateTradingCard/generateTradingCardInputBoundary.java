package classes.generateTradingCard;

/**
 * Defines the input boundary for generating a trading card.
 */
public interface generateTradingCardInputBoundary {

    /**
     * Generates a trading card based on the provided request model.
     *
     * @param requestModel the request containing animal data for the card
     * @return a TradingCardViewModel with generated card details
     */
    tradingCardViewModel generate(generateTradingCardRequestModel requestModel);
}
