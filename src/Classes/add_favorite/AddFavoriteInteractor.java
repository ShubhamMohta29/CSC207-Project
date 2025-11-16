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
    public void execute(AddFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        final FavoriteList favorites = addFavoriteDataAccessObject.getFavoriteList();
        addFavoriteDataAccessObject.addFavorite(name);
        favorites.addFavorite(name);
    }
}
