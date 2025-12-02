/**
 * FilterController :controller creates the input data and calls the use case. It gets called by the view with the user actions
 */
package Classes.Filter;

import java.util.List;

public class FilterController {
    // field declarations
    private final FilterInputBoundary inputBoundary;

    /*
    Constructor
     */
    public FilterController(FilterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
      * Function creates the filter request object (filter input).
     * @param groups for selected animal group
     * @param locations for selected locations
     * @param cursor to keep track of the last animal displayed
     * @param diets for selected diets
     * @param lifespanMax for selected minimum
     * @param lifespanMin for selected max
     */
    public void filterAnimals(List<String> groups,
                              List<String> locations,
                              List<String> diets,
                              Integer lifespanMin, Integer lifespanMax,
                              String cursor) {
        final FilterInput request = new FilterInput.Builder()
                .groups(groups)
                .locations(locations)
                .diets(diets)
                .lifespanRange(lifespanMin, lifespanMax)
                .cursor(cursor)
                .build();
        inputBoundary.filterAnimals(request);
    }
}
