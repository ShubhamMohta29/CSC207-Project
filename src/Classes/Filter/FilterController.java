/**
 * FilterController :controller creates the input data and calls the use case. It gets called by the view with the user actions
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import java.util.List;


public class FilterController {
    //field declarations
    private final FilterInputBoundary inputBoundary;

    /*
    Constructor
     */
    public FilterController(FilterInteractor inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /*
      Function creates the filter request object (filter input)
     */
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
        System.out.println("okay i have created the filter request object");
        inputBoundary.filterAnimals(request);
    }
}
