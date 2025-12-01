package classes.GenerateTradingCard;

public class TradingCardPresenter implements GenerateTradingCardOutputBoundary {

    @Override
    public TradingCardViewModel prepareSuccessView(GenerateTradingCardResponseModel response) {
        final TradingCardViewModel vm = new TradingCardViewModel();
        vm.setImage(response.getImage());
        vm.setAnimalName(response.getAnimal().getName());
        return vm;
    }
}
