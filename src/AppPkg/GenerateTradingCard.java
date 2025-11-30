package AppPkg;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Classes.retrieveInfo.Animal;
import Classes.GenerateTradingCard.CardAppearanceConstants;
import Classes.GenerateTradingCard.TradingCardService;
import Classes.GenerateTradingCard.TradingCardViewModel;

/**
 * UI window for viewing and exporting a generated trading card.
 */
public class GenerateTradingCard extends JFrame {

    private final TradingCardViewModel viewModel;
    private final JFrame previousScreen;

    /**
     * Opens trading card window with return navigation enabled.
     * @param animal whose card is generated
     * @param previousScreen screen to return to when clicking back
     */
    public GenerateTradingCard(final Animal animal, final JFrame previousScreen) {
        this.previousScreen = previousScreen;
        this.viewModel = new TradingCardService().create(animal);
        buildUi();
    }

    /**
     * Opens trading card window without previous screen link.
     * @param animal whose card is generated
     */
    public GenerateTradingCard(final Animal animal) {
        this(animal, null);
    }

    /**
     * Builds UI with TradingCardPanel callback-wired buttons.
     */
    private void buildUi() {
        setTitle("Trading Card");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(CardAppearanceConstants.CARD_WIDTH, CardAppearanceConstants.CARD_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final TradingCardPanel panel = new TradingCardPanel(
                viewModel,
                this::goBack,
                this::saveImage
        );

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Navigate back to previous window or Home.
     */
    private void goBack() {
        if (previousScreen != null) {
            previousScreen.setVisible(true);
        }
        else {
            new MainMenu().setVisible(true);
        }
        dispose();
    }

    /**
     * Writes generated PNG to user-selected path.
     */
    private void saveImage() {
        final JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(viewModel.getAnimalName().replace(" ", "_") + "_card.png"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(viewModel.getImage(), "png", chooser.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Saved!");
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}
