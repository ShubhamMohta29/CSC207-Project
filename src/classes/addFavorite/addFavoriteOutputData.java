package classes.addFavorite;

/**
 * Output data for add favorite use case.
 */
public class addFavoriteOutputData {
    private final String[] favList;

    public addFavoriteOutputData(String[] favList) {
        this.favList = favList;
    }

    public String[] getFavList() {
        return favList;
    }
}
