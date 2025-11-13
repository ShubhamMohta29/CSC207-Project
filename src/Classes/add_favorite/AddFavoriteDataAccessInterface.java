package Classes.add_favorite;

/**
 * A DAO interface for the add favorite use case.
 */
public interface AddFavoriteDataAccessInterface {
    /**
     * Returns the list of favorite animals of the current user.
     */
    String[] getFavoriteList();
}
