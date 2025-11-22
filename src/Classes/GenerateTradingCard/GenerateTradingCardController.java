package Classes.GenerateTradingCard;

public class GenerateTradingCardController {

    private final GenerateTradingCardInputBoundary interactor;

    public GenerateTradingCardController(GenerateTradingCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    public TradingCardViewModel generateCard(String animalName, Classes.Animal animal) {

        // Create the request model.
        GenerateTradingCardRequestModel request = new GenerateTradingCardRequestModel(animal);

        // Call the interactor -> presenter -> return ViewModel
        return interactor.generate(request);
    }
}