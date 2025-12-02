package classes.saveCard;

public class saveCardPresenter implements saveCardOutputBoundary {

    @Override
    public saveCardResponseModel prepareSuccessView(saveCardResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public saveCardResponseModel prepareFailView(String errorMessage) {
        throw new RuntimeException(errorMessage);
    }
}
