package classes.retrieveInfo;

public class searchAnimalsPresenter implements searchAnimalsOutputBoundary {
    private final searchAnimalsViewModel viewModel;

    public searchAnimalsPresenter(searchAnimalsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(searchAnimalsOutputData responseModel) {
        viewModel.setSuccess(responseModel.isSuccess());
        viewModel.setMessage(responseModel.getErrorMessage());
        viewModel.setAnimals(responseModel.getAnimals());
        viewModel.setSuggestion(responseModel.getSuggestion());
    }
}
