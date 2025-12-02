package AppPkg;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.retrieveInfo.animal;
import classes.generateTradingCard.cardAppearanceConstants;
import classes.generateTradingCard.tradingCardService;
import classes.generateTradingCard.tradingCardViewModel;
import classes.saveCard.*;

/**
 * UI window for viewing, saving, and downloading a generated trading card.
 */
public class GenerateTradingCard extends JFrame {

    private final tradingCardViewModel viewModel;
    private final JFrame previousScreen;

    /**
     * Opens trading card window with action buttons at the bottom.
     */
    public GenerateTradingCard(final animal animal, final JFrame previousScreen) {
        this.previousScreen = previousScreen;
        this.viewModel = new tradingCardService().create(animal);
        buildUi();
    }

    /** Opens card window without return screen link. */
    public GenerateTradingCard(final animal animal) {
        this(animal, null);
    }

    /** UI: image + buttons (Back, Download, Save Card). */
    private void buildUi() {
        setTitle("Trading Card");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(cardAppearanceConstants.CARD_WIDTH, cardAppearanceConstants.CARD_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new TradingCardPanel(
                viewModel,
                this::goBack,
                this::saveImage,
                this::saveCard
        ), BorderLayout.CENTER);

        setVisible(true);
    }

    /** Return to previous screen. */
    private void goBack() {
        if (previousScreen != null) previousScreen.setVisible(true);
        else new MainMenu().setVisible(true);
        dispose();
    }

    /** Write PNG to a location of user's choice. */
    private void saveImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(viewModel.getAnimalName().replace(" ", "_") + "_card.png"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(viewModel.getImage(), "png", chooser.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Downloaded!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /** Saves card internally using SaveCard use case. */
    private void saveCard() {
        saveCardController controller = new saveCardController(
                new saveCardInteractor(
                        new fileSystemCardDataAccess(),
                        new saveCardPresenter()
                )
        );

        controller.save(viewModel.getAnimalName(), viewModel.getImage());
        JOptionPane.showMessageDialog(this, "Card saved to library!");
    }
}
