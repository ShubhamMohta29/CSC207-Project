package Tests;

import apppkg.UIManager;
import classes.Settings.*;
import org.junit.jupiter.api.*;

import static classes.Settings.SettingConstants.DEFAULT_FONT_SIZE;
import static classes.Settings.SettingConstants.FONT_SIZE_TWO;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

/**
 * High-level integration tests for the Text Setting use case.
 * Ensures: Controller → Interactor → DataAccess → Presenter → UIManager → UI Frame
 */
public class TextSettingUseCaseTest {

    @BeforeEach
    void setup() {
        // Make sure test settings file is reset
        TextSettingDataAccess data = new TextSettingDataAccess("TestSettings.csv");
        data.save(new TextSetting(12, Color.BLACK,"Arial" ));
    }

    /**
     * Tests full main flow:
     * Controller.updateSettings(...) → saved → UIManager.updateALL(...)
     */
    @Test
    void testMainFlow_TextSettingsAppliedToFrame() {

        TextSettingController controller = new TextSettingController("TestSettings.csv");
        UIManager uiManager = new UIManager("TestSettings.csv");

        String[] availableFonts = new FontFetcher().getFonts();

        // Create UI container
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("Hello");
        JLabel l2 = new JLabel("Humans");

        frame.add(l1);
        frame.add(l2);

        // Initial update (should be default Arial, black)
        uiManager.updateALL(frame);

        assertEquals("Arial", l1.getFont().getName());
        assertEquals(Color.BLACK, l1.getForeground());

        // Apply new settings
        controller.updateSettings("purple", 2, availableFonts[0]);
        uiManager.updateALL(frame);

        // Now labels should reflect new style
        assertEquals(availableFonts[0], l1.getFont().getName());
        assertEquals(FONT_SIZE_TWO , l1.getFont().getSize());

        // Purple mapped by your UIManager
        assertEquals(new Color(70, 20, 124), l1.getForeground());
    }


    @Test
    void testAlternativeFlow_InvalidColorGracefullyHandled() {
        TextSettingController controller = new TextSettingController("TestSettings.csv");
        UIManager uiManager = new UIManager("TestSettings.csv");

        JFrame frame = new JFrame();
        JLabel label = new JLabel("Test");
        frame.add(label);

        // Try using invalid color
        controller.updateSettings("NOT_A_COLOR", 10, "Arial");

        // Should fall back to black or default
        uiManager.updateALL(frame);

        assertEquals("Arial", label.getFont().getName());
        assertEquals(DEFAULT_FONT_SIZE, label.getFont().getSize());

        // Should revert to default color
        assertEquals(Color.BLACK, label.getForeground());
    }
}