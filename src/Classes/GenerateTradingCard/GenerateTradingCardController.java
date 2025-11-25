package Classes.GenerateTradingCard;
import Classes.Animal;

public class GenerateTradingCardController {

    private final GenerateTradingCardInputBoundary interactor;

    public GenerateTradingCardController(GenerateTradingCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    public TradingCardViewModel generateCard(Animal animal) {
        GenerateTradingCardRequestModel req = new GenerateTradingCardRequestModel(animal);
        return interactor.generate(req);
    }
}
