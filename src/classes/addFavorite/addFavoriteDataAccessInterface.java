package classes.addFavorite;

/**
 * A DAO interface for the add favorite use case.
 */
public interface addFavoriteDataAccessInterface {
    /**
     * Returns the list of favorite animals of the current user.
     */
    favoriteList getFavoriteList();

    void addFavorite(String name);

    void removeFavorite(String name);

    boolean isFavorite(String name);
}
