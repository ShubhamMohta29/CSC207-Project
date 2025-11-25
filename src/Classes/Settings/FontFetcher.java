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


    public String[] getFonts() {
        String[] list = this.availableFont.toArray(new String[availableFont.size()]);
        return list;
    }

}