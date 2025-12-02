package classes.addFavorite;

import java.util.ArrayList;

/**
 * An entity for Favorites list, which includes user's favorite animals
 */

public class favoriteList {
    private final ArrayList<String> favorites;

    public favoriteList() {
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
