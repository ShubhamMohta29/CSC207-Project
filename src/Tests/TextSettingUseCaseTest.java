package Tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import Classes.Settings.TextSetting;
import java.awt.*;


public class TextSettingTest {

    @Test
    void testConstructorAndGetters() {
        TextSetting ts = new TextSetting(16, Color.RED, "Arial");

        assertEquals(16, ts.getTextSize());
        assertEquals(Color.RED, ts.getTextColor());
        assertEquals("Arial", ts.getFontName());
    }

    @Test
    void testSetters() {
        TextSetting ts = new TextSetting();

        ts.setTextSize(20);
        ts.setTextColor(Color.BLUE);
        ts.setTextStyle("Times New Roman");

        assertEquals(20, ts.getTextSize());
        assertEquals(Color.BLUE, ts.getTextColor());
        assertEquals("Times New Roman", ts.getFontName());
    }

    @Test
    void testGetFont() {
        TextSetting ts = new TextSetting(14, Color.BLACK, "Helvetica");

        Font f = ts.getFont();

        assertEquals("Helvetica", f.getName());
        assertEquals(Font.PLAIN, f.getStyle());
        assertEquals(14, f.getSize());
    }

    @Test
    void testDefaultConstructor() {
        TextSetting ts = new TextSetting();

        // Default fields are null/0 because they are not initialized
        assertEquals(0, ts.getTextSize());
        assertNull(ts.getTextColor());
        assertNull(ts.getFontName());
    }
}
