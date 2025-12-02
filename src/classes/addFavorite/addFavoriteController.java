package classes.addFavorite;

/**
 * The controller for add favorite use case.
 */
public class addFavoriteController {
    private final addFavoriteInputBoundary addFavoriteInteractor;

    public addFavoriteController(addFavoriteInputBoundary addFavoriteInteractor) {
        this.addFavoriteInteractor = addFavoriteInteractor;
    }

    public void execute(String name) {
        final addFavoriteInputData addFavoriteInputData = new addFavoriteInputData(name);
        addFavoriteInteractor.execute(addFavoriteInputData);
    }

    public void execute1(String name) {
        final addFavoriteInputData addFavoriteInputData = new addFavoriteInputData(name);
        addFavoriteInteractor.execute1(addFavoriteInputData);
    }
}
