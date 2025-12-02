package classes.viewSavedCards;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class fileSystemLoadSavedCardsDataAccess implements loadSavedCardsDataAccessInterface {

    private final File saveDir = new File("cards/");

    @Override
    public List<String> loadAllCardNames() {
        List<String> names = new ArrayList<>();
        if (!saveDir.exists()) return names;

        for (File file : saveDir.listFiles()) {
            if (file.getName().endsWith(".png"))
                names.add(file.getName().replace(".png", ""));
        }
        return names;
    }

    @Override
    public List<BufferedImage> loadAllCardImages() {
        List<BufferedImage> images = new ArrayList<>();
        if (!saveDir.exists()) return images;

        for (File file : saveDir.listFiles()) {
            if (file.getName().endsWith(".png")) {
                try { images.add(ImageIO.read(file)); }
                catch (Exception ignored) {}
            }
        }
        return images;
    }
}
