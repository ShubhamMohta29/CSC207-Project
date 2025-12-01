package Classes.SaveCard;

public class SaveCardPresenter implements SaveCardOutputBoundary {

    @Override
    public SaveCardResponseModel prepareSuccessView(SaveCardResponseModel responseModel) {
        return responseModel;
    }

    @Override
    public SaveCardResponseModel prepareFailView(String errorMessage) {
        throw new RuntimeException(errorMessage);
    }
}
