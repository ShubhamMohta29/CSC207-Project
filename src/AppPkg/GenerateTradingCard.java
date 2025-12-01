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
import Classes.SaveCard.*;

/**
 * UI window for viewing, saving, and downloading a generated trading card.
 */
public class GenerateTradingCard extends JFrame {

    private final TradingCardViewModel viewModel;
    private final JFrame previousScreen;

    /**
     * Opens trading card window with action buttons at the bottom.
     */
    public GenerateTradingCard(final Animal animal, final JFrame previousScreen) {
        this.previousScreen = previousScreen;
        this.viewModel = new TradingCardService().create(animal);
        buildUi();
    }

    /** Opens card window without return screen link. */
    public GenerateTradingCard(final Animal animal) {
        this(animal, null);
    }

    /** UI: image + buttons (Back, Download, Save Card). */
    private void buildUi() {
        setTitle("Trading Card");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(CardAppearanceConstants.CARD_WIDTH, CardAppearanceConstants.CARD_HEIGHT);
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
        SaveCardController controller = new SaveCardController(
                new SaveCardInteractor(
                        new FileSystemCardDataAccess(),
                        new SaveCardPresenter()
                )
        );

        controller.save(viewModel.getAnimalName(), viewModel.getImage());
        JOptionPane.showMessageDialog(this, "Card saved to library!");
    }
}
