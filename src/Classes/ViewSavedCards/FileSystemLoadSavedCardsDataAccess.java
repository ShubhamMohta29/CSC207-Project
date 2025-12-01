package Classes.ViewSavedCards;

import Classes.retrieveInfo.Animal;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class FileSystemLoadSavedCardsDataAccess implements LoadSavedCardsDataAccessInterface {

    private final File directory = new File("cards/");

    @Override
    public List<Animal> loadAnimals() {
        List<Animal> animals = new ArrayList<>();

        if (!directory.exists()) return animals;

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".json")) {

                try {
                    String jsonText = Files.readString(file.toPath());
                    JSONObject json = new JSONObject(jsonText);

                    animals.add(new Animal(
                            json.getString("name"),
                            null, // taxonomy (optional to add later)
                            json.optString("habitat", "N/A"),
                            null,
                            null,
                            null,
                            0,
                            json.optString("diet", "N/A"),
                            json.optString("lifestyle", "N/A"),
                            json.optDouble("weightKg", 0.0),
                            json.optDouble("heightCm", 0.0),
                            json.optString("group", "N/A"),
                            json.optString("type", "N/A")
                    ));
                } catch (Exception ignored) {}
            }
        }
        return animals;
    }

    @Override
    public List<BufferedImage> loadImages() {
        List<BufferedImage> images = new ArrayList<>();

        if (!directory.exists()) return images;

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".png")) {
                try { images.add(ImageIO.read(file)); }
                catch (Exception ignored) {}
            }
        }
        return images;
    }
}
