package Classes.Settings;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Collections;

public class FontFetcher {
    private final ArrayList<String> availableFont;

    public FontFetcher() {
        this.availableFont = new ArrayList<>();
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = g.getAvailableFontFamilyNames();

        Collections.addAll(availableFont, fontFamilies);
    }

    /**
     * Returns a list of font retrieved from local environment.
     * @return str[]
     */
    public String[] getFonts() {

        return this.availableFont.toArray(new String[availableFont.size()]);
    }

}
