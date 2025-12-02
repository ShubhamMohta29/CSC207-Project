package Classes.SaveCard;

public interface SaveCardOutputBoundary {
    SaveCardResponseModel prepareSuccessView(SaveCardResponseModel responseModel);
    SaveCardResponseModel prepareFailView(String errorMessage);
}
