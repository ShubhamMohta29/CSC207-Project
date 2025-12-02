package classes.addFavorite;

/**
 * The add favorite interactor
 */
public class addFavoriteInteractor implements addFavoriteInputBoundary {
    private final addFavoriteDataAccessInterface addFavoriteDataAccessObject;

    public addFavoriteInteractor(addFavoriteDataAccessInterface addFavoriteDataAccessInterface){
        this.addFavoriteDataAccessObject = addFavoriteDataAccessInterface;
    }

    @Override
    /**
     * Add name to a favorite list.
     */
    public void execute(addFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        addFavoriteDataAccessObject.addFavorite(name);
    }

    /**
     * Remove name from a favorite list.
     */
    public void execute1(addFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        addFavoriteDataAccessObject.removeFavorite(name);
    }
}
