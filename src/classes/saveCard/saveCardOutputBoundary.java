package classes.saveCard;

public interface saveCardOutputBoundary {
    saveCardResponseModel prepareSuccessView(saveCardResponseModel responseModel);
    saveCardResponseModel prepareFailView(String errorMessage);
}
