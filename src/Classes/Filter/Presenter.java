package Classes.Filter;

import Classes.Animal;

import java.util.List;

//converts domain response to ViewModel (UI-friendly) and updates the view model which is being viewed by the view
public class Presenter implements FilterOutputBoundary{

    private final ViewModel viewModel;

    public Presenter(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(FilterOutput output) {
        List<Animal> animals = output.getFilteredAnimals();
        viewModel.setAnimals(animals);
        viewModel.setHasMore(output.checkHasMore());
        viewModel.setNextCursor(output.getNextCursor());
    }
}
