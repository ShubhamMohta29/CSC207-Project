package classes.addFavorite;

import java.io.*;

/**
 * A DAO for saving to and reading from a local file.
 */
public class fileFavoritesDataAccessObject implements addFavoriteDataAccessInterface {

    private final File csvFile;
    private final favoriteList favorites =  new favoriteList();

    public fileFavoritesDataAccessObject(String csvpath) {
        csvFile = new File(csvpath);
        try {
            csvFile.createNewFile();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String row;
            while ((row = reader.readLine()) != null) {
                favorites.addFavorite(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            for (String name : favorites.getFavorites()) {
                writer.write(name);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public favoriteList getFavoriteList(){
        return favorites;
    }

    @Override
    public void addFavorite(String name) {
        if (!isFavorite(name)) {
            favorites.addFavorite(name);
        }
        save();
    }

    public void removeFavorite(String name) {
        favorites.removeFavorite(name);
        save();
    }

    public boolean isFavorite(String name) {
        return favorites.isFavorite(name);
    }
}
