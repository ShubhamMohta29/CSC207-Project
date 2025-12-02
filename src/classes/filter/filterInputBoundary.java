/**
 * FilterInputBoundary : Interface called by controller.
 */

package classes.filter;

public interface filterInputBoundary {

    /**
     * Filter Input boundary.
     * @param input filter request object
     * @return list of filtere animals with their display info
     */
    filterOutput filterAnimals(filterInput input);
}
