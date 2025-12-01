package AppPkg;

import Classes.ViewSavedCards.LoadSavedCardsResponseModel;
import Classes.GenerateTradingCard.TradingCardService;
import Classes.GenerateTradingCard.TradingCardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SavedCardScreen extends JFrame {

    public SavedCardScreen(LoadSavedCardsResponseModel response) {
        setTitle("My Saved Trading Cards");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));

        // Load all cards
        java.util.List<String> names = response.getCardNames();
        java.util.List<BufferedImage> images = response.getCardImages();

        for (int i = 0; i < names.size(); i++) {

            BufferedImage img = images.get(i);
            String cardName = names.get(i);

            Image scaled = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(scaled));
            JLabel label = new JLabel(cardName, SwingConstants.CENTER);

            // tile panel
            JPanel tile = new JPanel(new BorderLayout());
            tile.add(picture, BorderLayout.CENTER);
            tile.add(label, BorderLayout.SOUTH);
            tile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            // open full card view
            tile.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TradingCardViewModel generated = new TradingCardService()
                            .create(new JSONWrapperforAnimal(cardName));
                    new GenerateTradingCard(new JSONWrapperforAnimal(cardName)).setVisible(true);
                    dispose();
                }
            });

            grid.add(tile);
        }

        // Scroll panel if there are many cards
        JScrollPane scroll = new JScrollPane(grid);
        mainPanel.add(scroll, BorderLayout.CENTER);

        JButton back = new JButton("Back to Home");
        back.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(back);

        mainPanel.add(bottom, BorderLayout.SOUTH);
        add(mainPanel);
    }
}
