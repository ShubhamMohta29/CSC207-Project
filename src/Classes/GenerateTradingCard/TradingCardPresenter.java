package Classes.GenerateTradingCard;

public class TradingCardPresenter implements GenerateTradingCardOutputBoundary {

    @Override
    public TradingCardViewModel prepareSuccessView(GenerateTradingCardResponseModel responseModel) {
        return new TradingCardViewModel(responseModel.getImage());
    }
}