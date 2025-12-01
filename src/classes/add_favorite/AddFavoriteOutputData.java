package classes.add_favorite;

/**
 * Output data for add favorite use case.
 */
public class AddFavoriteOutputData {
    private final String[] favList;

    public AddFavoriteOutputData(String[] favList) {
        this.favList = favList;
    }

    public String[] getFavList() {
        return favList;
    }
}
