package Classes.Filter;
//The controller creates the input data and calls the use case. It gets called by the view with the user actions
public class Controller {
    private final FilterInputBoundary interactor;

    public Controller(FilterInteractor interactor) {
        this.interactor = interactor;
    }

    public void filterAnimals(FilterInput inputData) {
        interactor.filterAnimals(inputData);
    }
}
