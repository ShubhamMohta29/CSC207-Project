package Classes.retrieveInfo;

public class SearchAnimalsPresenter implements SearchAnimalsOutputBoundary {
    private final SearchAnimalsViewModel viewModel;

    public SearchAnimalsPresenter(SearchAnimalsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(Classes.retrieveInfo.SearchAnimalsOutputData responseModel) {
        viewModel.setSuccess(responseModel.isSuccess());
        viewModel.setMessage(responseModel.getErrorMessage());
        viewModel.setAnimals(responseModel.getAnimals());
        viewModel.setSuggestion(responseModel.getSuggestion());
    }
}
