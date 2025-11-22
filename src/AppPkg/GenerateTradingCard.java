
package AppPkg;

import Classes.Animal;
import Classes.GenerateTradingCard.GenerateTradingCardInputBoundary;
import Classes.GenerateTradingCard.GenerateTradingCardInteractor;
import Classes.GenerateTradingCard.GenerateTradingCardRequestModel;
import Classes.GenerateTradingCard.TradingCardPresenter;
import Classes.GenerateTradingCard.TradingCardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class GenerateTradingCard extends JFrame {

    private JPanel imagePanel;
    private JPanel bottomPanel;
    private JButton btnCancel;
    private JButton btnDownload;

    private TradingCardViewModel viewModel;

    private JFrame previousScreen;   // ✅ NEW

    // 2-argument constructor → supports going back previous screen
    public GenerateTradingCard(Animal animal, JFrame previousScreen) {
        this.previousScreen = previousScreen;
        runCleanArchitectureFlow(animal);
        initComponents();
    }

    // 1-argument constructor → default to MainMenu on Cancel
    public GenerateTradingCard(Animal animal) {
        this(animal, null);
    }

    private void runCleanArchitectureFlow(Animal animal) {
        TradingCardPresenter presenter = new TradingCardPresenter();
        GenerateTradingCardInputBoundary interactor =
                new GenerateTradingCardInteractor(presenter);

        GenerateTradingCardRequestModel requestModel =
                new GenerateTradingCardRequestModel(animal);

        this.viewModel = interactor.generate(requestModel);
    }

    private void initComponents() {
        setTitle("Trading Card");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel that displays the generated card image
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (viewModel != null && viewModel.getImage() != null) {
                    int imgW = viewModel.getImage().getWidth();
                    int imgH = viewModel.getImage().getHeight();
                    int x = (getWidth() - imgW) / 2;
                    int y = (getHeight() - imgH) / 2;
                    g.drawImage(viewModel.getImage(), x, y, null);
                }
            }
        };
        add(imagePanel, BorderLayout.CENTER);

        // Bottom panel with Cancel and Download buttons
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnCancel = new JButton("Cancel");
        btnDownload = new JButton("Download");

        btnCancel.addActionListener(this::btnCancelActionPerformed);
        btnDownload.addActionListener(this::btnDownloadActionPerformed);

        bottomPanel.add(btnCancel);
        bottomPanel.add(btnDownload);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Go back to previous screen if provided, otherwise go to MainMenu
    private void btnCancelActionPerformed(ActionEvent evt) {
        if (previousScreen != null) {
            previousScreen.setVisible(true);
        } else {
            new MainMenu().setVisible(true);
        }
        this.dispose();
    }

    // Save the generated trading card image
    private void btnDownloadActionPerformed(ActionEvent evt) {
        if (viewModel == null || viewModel.getImage() == null) {
            JOptionPane.showMessageDialog(this, "No image to save.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("trading_card.jpg"));

        try {
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                javax.imageio.ImageIO.write(
                        viewModel.getImage(),
                        "jpg",
                        chooser.getSelectedFile()
                );
                JOptionPane.showMessageDialog(this, "Card saved!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving card.");
        }
    }
}