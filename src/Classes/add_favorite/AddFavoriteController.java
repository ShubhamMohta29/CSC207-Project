package Classes.add_favorite;

/**
 * The controller for add favorite use case.
 */
public class AddFavoriteController {
    private AddFavoriteInputBoundary addFavoriteInteractor;

    public AddFavoriteController(AddFavoriteInputBoundary addFavoriteInteractor) {
        this.addFavoriteInteractor = addFavoriteInteractor;
    }

    public void execute(String name) {
        final AddFavoriteInputData addFavoriteInputData = new AddFavoriteInputData(name);
        addFavoriteInteractor.execute(addFavoriteInputData);
    }

    public void execute1(String name) {
        final AddFavoriteInputData addFavoriteInputData = new AddFavoriteInputData(name);
        addFavoriteInteractor.execute1(addFavoriteInputData);
    }
}
