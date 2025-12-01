package classes.add_favorite;

/**
 * In-memory implemention of the DAO for storing animal name.
 * This does not persist data between runs.
 */
public class InMemoryFavoritesDataAccessObject implements AddFavoriteDataAccessInterface{

    private final FavoriteList favorites =  new FavoriteList();

    public FavoriteList getFavoriteList(){
        return favorites;
    }

    @Override
    public void addFavorite(String name) {
        if (!isFavorite(name)) {
            favorites.addFavorite(name);
        }
    }

    @Override
    public void removeFavorite(String name) {
        favorites.removeFavorite(name);
    }

    public boolean isFavorite(String name){
        return favorites.isFavorite(name);
    }
}
