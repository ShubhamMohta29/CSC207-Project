package Classes.add_favorite;

import java.util.ArrayList;

/**
 * An entity for Favorites list, which includes user's favorite animals
 */

public class FavoriteList {
    private ArrayList<String> favorites;

    public FavoriteList() {
        favorites = new ArrayList<String>();
    }

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public void addFavorite(String name){
        if (!favorites.contains(name)) {
            favorites.add(name);
        }
    }

    public void removeFavorite(String name){
        favorites.remove(name);
    }

}
