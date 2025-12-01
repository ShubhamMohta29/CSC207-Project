package AppPkg;

import Classes.ViewSavedCards.LoadSavedCardsResponseModel;
import Classes.retrieveInfo.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class SavedCardScreen extends JFrame {

    private final List<Animal> animals;
    private final List<BufferedImage> images;

    public SavedCardScreen(LoadSavedCardsResponseModel response) {

        this.animals = response.getAnimals();
        this.images  = response.getImages();

        setTitle("My Saved Trading Cards Library");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));

        for (int i = 0; i < animals.size(); i++) {

            Animal animal = animals.get(i);
            BufferedImage img = images.get(i);

            Image scaled = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            JLabel picture = new JLabel(new ImageIcon(scaled));
            JLabel label = new JLabel(animal.getName(), SwingConstants.CENTER);

            JPanel tile = new JPanel(new BorderLayout());
            tile.add(picture, BorderLayout.CENTER);
            tile.add(label, BorderLayout.SOUTH);
            tile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            int index = i;
            tile.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    new GenerateTradingCard(animals.get(index)).setVisible(true);
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