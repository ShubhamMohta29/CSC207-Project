package AppPkg;

import classes.viewSavedCards.loadSavedCardsResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SavedCardScreen extends JFrame {

    public SavedCardScreen(loadSavedCardsResponseModel response) {
        setTitle("My Saved Trading Cards");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));

        java.util.List<String> names = response.getCardNames();
        java.util.List<BufferedImage> images = response.getCardImages();

        for (int i = 0; i < names.size(); i++) {

            BufferedImage img = images.get(i);
            String cardName = names.get(i);

            Image scaled = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(scaled));
            JLabel label = new JLabel(cardName, SwingConstants.CENTER);

            JPanel tile = new JPanel(new BorderLayout());
            tile.add(picture, BorderLayout.CENTER);
            tile.add(label, BorderLayout.SOUTH);
            tile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            tile.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    new ViewSavedCardScreen(cardName, img).setVisible(true);
                    dispose();
                }
            });

            grid.add(tile);
        }

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
