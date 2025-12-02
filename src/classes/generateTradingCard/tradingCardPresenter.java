package classes.generateTradingCard;

public class tradingCardPresenter implements generateTradingCardOutputBoundary {

    @Override
    public tradingCardViewModel prepareSuccessView(generateTradingCardResponseModel response) {
        final tradingCardViewModel vm = new tradingCardViewModel();
        vm.setImage(response.getImage());
        vm.setAnimalName(response.getAnimal().getName());
        return vm;
    }
}
