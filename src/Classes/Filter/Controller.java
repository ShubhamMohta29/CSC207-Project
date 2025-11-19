package Classes.Filter;

import java.util.List;

//The controller creates the input data and calls the use case. It gets called by the view with the user actions
public class Controller {
    private final FilterInputBoundary inputBoundary;

    public Controller(FilterInteractor inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void filterAnimals(List<String> groups,
                              List<String> locations,
                              List<String> diets,
                              Integer lifespanMin, Integer lifespanMax,
                              String cursor) {
        FilterInput request = new FilterInput.Builder()
                .groups(groups)
                .locations(locations)
                .diets(diets)
                .lifespanRange(lifespanMin, lifespanMax)
                .cursor(cursor)
                .build();
        inputBoundary.filterAnimals(request);
    }
}
