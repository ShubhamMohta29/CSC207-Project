package Tests;

import Classes.Settings.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;


public class TextSettingUseCaseTest {

    @Test
    void testMainFlow_TextSettingsToFrame() {

        TextSettingDataAccess saves = new TextSettingDataAccess("TestSettings.csv");
        TextSettingInteractor config = new TextSettingInteractor("TestSettings.csv");
        String[] fonts = (new FontFetcher()).getFonts();


        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Hello");
        JLabel label2 = new JLabel("Humans");
        panel.add(label1);
        panel.add(label2);
        frame.add(panel);

        config.updateALL(frame);

        assertEquals("Arial", label1.getFont().getName());
        assertEquals(new Color(100, 50, 200), label1.getForeground());

        TextSettingRequest request = new TextSettingRequest("purple", 3, fonts[0]);
        config.editSettings(request);
        config.updateALL(frame);

        assertEquals(fonts[0], label2.getFont().getName());
        assertEquals(new Color(70, 20, 124), label1.getForeground());

    }

}
