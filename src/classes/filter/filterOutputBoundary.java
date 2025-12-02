/**
 * FilterOutputBoundary : Interface used by the interactor to pass the filter response to the presenter.
 * The presenter implements this to receive a FilterOutput and format or display it as needed.
 */
package classes.filter;

public interface filterOutputBoundary {

    /**
     * Command to presenter.
     * @param output the filter output object
     */
    void present(filterOutput output);
}
