package classes.add_favorite;

import java.util.ArrayList;

/**
 * An entity for Favorites list, which includes user's favorite animals
 */

public class FavoriteList {
    private final ArrayList<String> favorites;

    public FavoriteList() {
        favorites = new ArrayList<String>();
    }

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public void addFavorite(String name){
        favorites.add(name);
    }

    public void removeFavorite(String name){
        favorites.remove(name);
    }

    public boolean isFavorite(String name){
        return favorites.contains(name);
    }

}
