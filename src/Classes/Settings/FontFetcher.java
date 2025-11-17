package Classes.Settings;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

public class FontFetcher {
    private ArrayList<String> availableFont;

    public FontFetcher() {
        this.availableFont = new ArrayList<>();
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = g.getAvailableFontFamilyNames();

        for (String font : fontFamilies) {
            availableFont.add(font);
        }
    }


    public String[] getFonts() {
        String[] list = this.availableFont.toArray(new String[availableFont.size()]);
        return list;
    }

}