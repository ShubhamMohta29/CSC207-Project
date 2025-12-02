package AppPkg;

import classes.settings.textSettingController;
import AppPkg.UIStrategy.StrategyMap;
import AppPkg.UIStrategy.UIStyleStrategy;
import classes.settings.viewModel;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    private final StrategyMap strategies;
    private final String filePath;
    private Color fg;
    private Font font;

    /**
     * UIManager builds the controller + presenter + view model using the filePath.
     */
    public UIManager(String filePath) {

        // Build controller to load stored settings
        textSettingController controller = new textSettingController(filePath);
        viewModel viewModel = controller.getViewModel();

        // Store UI-ready settings
        this.filePath = filePath;
        this.fg = viewModel.getColor();
        this.font = viewModel.getFont();

        this.strategies = new StrategyMap();
    }

    public Font getFont() {
        return font;
    }

    /**
     * Updates ALL components in this JFrame, applying new UI settings.
     * Automatically wraps the content in a scroll panel if fonts or layout exceed space.
     */
    public void updateALL(JFrame frame) {

        // Rebuild controller + view model so we get newest settings
        textSettingController controller = new textSettingController(filePath);
        viewModel viewModel = controller.getViewModel();

        this.fg = viewModel.getColor();
        this.font = viewModel.getFont();

        // -------------------------------
        // NEW: install scroll pane if not installed yet
        // -------------------------------
        if (!(frame.getContentPane() instanceof JScrollPane)) {

            Container original = frame.getContentPane();
            JScrollPane scroll = new JScrollPane(original);

            // cleaner UI
            scroll.setBorder(null);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            frame.setContentPane(scroll);
        }

        // Get actual content to update (inside the scroll viewport)
        JScrollPane scrollPane = (JScrollPane) frame.getContentPane();
        Container content = (Container) scrollPane.getViewport().getView();

        // Recursively apply the new UI settings
        updateComponentsRecursively(content, fg, font);

        // Refresh UI
        content.revalidate();
        frame.pack();
    }

    /**
     * Recursively applies fonts + colors using strategy map.
     */
    private void updateComponentsRecursively(Container container, Color c, Font font) {
        for (Component comp : container.getComponents()) {

            UIStyleStrategy strategy = this.strategies.getStrategy(comp.getClass());
            strategy.apply(comp, c, font);

            if (comp instanceof Container) {
                updateComponentsRecursively((Container) comp, c, font);
            }
        }
    }
}
