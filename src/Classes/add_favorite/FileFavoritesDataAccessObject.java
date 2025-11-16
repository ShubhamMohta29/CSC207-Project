package Classes.add_favorite;

import java.io.*;

/**
 * A DAO for saving to and reading from a local file.
 */
public class FileFavoritesDataAccessObject implements AddFavoriteDataAccessInterface{

    private final File csvFile;
    private FavoriteList favorites =  new FavoriteList();

    public FileFavoritesDataAccessObject(String csvpath) {
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

    public FavoriteList getFavoriteList(){
        return favorites;
    }

    @Override
    public void addFavorite(String name) {
        favorites.addFavorite(name);
        save();
    }
}
