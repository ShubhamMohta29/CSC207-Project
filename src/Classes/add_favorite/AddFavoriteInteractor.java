package Classes.add_favorite;

/**
 * The add favorite interactor
 */
public class AddFavoriteInteractor implements AddFavoriteInputBoundary{
    private final AddFavoriteDataAccessInterface addFavoriteDataAccessObject;
    // private final AddFavoriteOutputBoundary addFavoritePresenter;

    public AddFavoriteInteractor(AddFavoriteDataAccessInterface addFavoriteDataAccessInterface){
        // AddFavoriteOutputBoundary addFavoriteOutputBoundary{
        this.addFavoriteDataAccessObject = addFavoriteDataAccessInterface;
        // this.addFavoritePresenter = addFavoriteOutputBoundary;
    }

    @Override
    public void execute(AddFavoriteInputData addFavoriteInputData) {
        final String name = addFavoriteInputData.getName();
        final FavoriteList favorites = addFavoriteDataAccessObject.getFavoriteList();
        // final AddFavoriteOutputData addFavoriteOutputData =
        // new AddFavoriteOutputData(favorites.getFavorites().toArray(new String[favorites.getFavorites().size()]));
        addFavoriteDataAccessObject.addFavorite(name);
        favorites.addFavorite(name);
    }
}
