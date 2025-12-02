/**
 * FilterInputBoundary : Interface called by controller.
 */

package Classes.Filter;

public interface FilterInputBoundary {

    /**
     * Filter Input boundary.
     * @param input filter request object
     * @return list of filtere animals with their display info
     */
    FilterOutput filterAnimals(FilterInput input);
}
