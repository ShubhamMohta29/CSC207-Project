package Classes.SaveCard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileSystemCardDataAccess implements SaveCardDataAccessInterface {

    private final File saveDir = new File("cards/");

    public FileSystemCardDataAccess() {
        if (!saveDir.exists()) saveDir.mkdirs();
    }

    @Override
    public boolean cardExists(String cardName) {
        return new File(saveDir, cardName + ".png").exists();
    }

    @Override
    public void saveCard(String cardName, BufferedImage image) throws Exception {
        ImageIO.write(image, "png", new File(saveDir, cardName + ".png"));
    }
}
