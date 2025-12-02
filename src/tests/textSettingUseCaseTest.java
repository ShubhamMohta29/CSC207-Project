package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AppPkg.UIManager;
import Classes.Settings.FontFetcher;
import Classes.Settings.SettingConstants;
import Classes.Settings.TextSetting;
import Classes.Settings.TextSettingController;
import Classes.Settings.TextSettingDataAccess;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * Integration tests for the Text Setting use case.
 * Verifies the full flow:
 * Controller → Interactor → DataAccess → Presenter → UIManager → UI Frame.
 */
public class TextSettingUseCaseTest {

    /**
     * Resets the test data file before each run.
     */
    @BeforeEach
    void setup() {
        final TextSettingDataAccess data =
                new TextSettingDataAccess(SettingConstants.TEST_SETTINGS_FILE);

        data.save(new TextSetting(
                SettingConstants.DEFAULT_FONT_SIZE,
                SettingConstants.DEFAULT_COLOR,
                SettingConstants.DEFAULT_FONT_NAME
        ));
    }

    /**
     * Tests the main flow:
     * Controller.updateSettings → values saved →
     * UIManager.updateALL → UI components reflect new settings.
     */
    @Test
    void testMainFlowTextSettingsAppliedToFrame() {

        final TextSettingController controller =
                new TextSettingController(SettingConstants.TEST_SETTINGS_FILE);

        final UIManager uiManager =
                new UIManager(SettingConstants.TEST_SETTINGS_FILE);

        final String[] availableFonts = new FontFetcher().getFonts();

        // Build UI
        final JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());

        final JLabel label1 = new JLabel("Hello");
        final JLabel label2 = new JLabel("Humans");

        frame.add(label1);
        frame.add(label2);

        // Initial update (defaults)
        uiManager.updateALL(frame);

        assertEquals(
                SettingConstants.DEFAULT_FONT_NAME,
                label1.getFont().getName()
        );
        assertEquals(
                SettingConstants.DEFAULT_COLOR,
                label1.getForeground()
        );

        // Apply new settings: purple, size TWO, first font name
        controller.updateSettings(
                SettingConstants.NAME_PURPLE,
                SettingConstants.TWO,
                availableFonts[0]
        );

        uiManager.updateALL(frame);

        // UI should update
        final Font updatedFont = label1.getFont();

        assertEquals(availableFonts[0], updatedFont.getName());
        assertEquals(SettingConstants.FONT_SIZE_TWO, updatedFont.getSize());
        assertEquals(SettingConstants.PURPLE, label1.getForeground());
    }

    /**
     * Tests alternative/exception flow:
     * Invalid input should cause fallback to default settings.
     */
    @Test
    void testAlternativeFlow() {

        final TextSettingController controller =
                new TextSettingController(SettingConstants.TEST_SETTINGS_FILE);

        final UIManager uiManager =
                new UIManager(SettingConstants.TEST_SETTINGS_FILE);

        final JFrame frame = new JFrame();
        final JLabel label = new JLabel("Test");

        frame.add(label);

        // Invalid color + invalid size → fallback to defaults
        controller.updateSettings(
                "NOT_A_COLOR",
                1,
                SettingConstants.DEFAULT_FONT_NAME
        );

        uiManager.updateALL(frame);

        final Font font = label.getFont();
        final Color color = label.getForeground();

        assertEquals(
                SettingConstants.DEFAULT_FONT_NAME,
                font.getName()
        );
        assertEquals(
                SettingConstants.FONT_SIZE_ONE,
                font.getSize()
        );
        assertEquals(
                SettingConstants.DEFAULT_COLOR,
                color
        );
    }
}
