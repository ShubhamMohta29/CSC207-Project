package Classes.add_favorite;

/**
 * The add favorite interactor
 */
public class AddFavoriteInteractor implements AddFavoriteInputBoundary{
    private final AddFavoriteDataAccessInterface addFavoriteDataAccessObject;

    public AddFavoriteInteractor(AddFavoriteDataAccessInterface addFavoriteDataAccessInterface){
        this.addFavoriteDataAccessObject = addFavoriteDataAccessInterface;
    }

    @Override
    /**
     * Add name to a favorite list.
     */
    public void execute(AddFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        addFavoriteDataAccessObject.addFavorite(name);
    }

    /**
     * Remove name from a favorite list.
     */
    public void execute1(AddFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        addFavoriteDataAccessObject.removeFavorite(name);
    }
}
