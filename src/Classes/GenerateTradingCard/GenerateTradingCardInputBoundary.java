package Classes.GenerateTradingCard;

/**
 * Defines the input boundary for generating a trading card.
 */
public interface GenerateTradingCardInputBoundary {

    /**
     * Generates a trading card based on the provided request model.
     *
     * @param requestModel the request containing animal data for the card
     * @return a TradingCardViewModel with generated card details
     */
    TradingCardViewModel generate(GenerateTradingCardRequestModel requestModel);
}
