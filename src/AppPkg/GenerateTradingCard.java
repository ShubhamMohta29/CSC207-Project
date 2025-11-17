package AppPkg;

import Classes.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GenerateTradingCard extends JFrame {

    private Animal animal;
    private GradientPanel cardPanel;
    private JButton btnReturn;
    private JButton btnDownload;
    private JFrame previousScreen;

    public GenerateTradingCard(Animal animal, JFrame previousScreen){
        this.animal = animal;
        this.previousScreen = previousScreen;
        initComponents();
        displayTradingCard();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trading Card - REVEAL");
        setSize(480,640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main card panel
        cardPanel = new GradientPanel();
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 8),  // GOLD BORDER
                BorderFactory.createEmptyBorder(20, 20, 20, 20)            // INTERNAL MARGIN
        ));
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        add(cardPanel, BorderLayout.CENTER);

        // Bottom button panel
        JPanel bottomPanel = new JPanel(new FlowLayout());

        btnReturn = new JButton("Cancel");
        btnReturn.addActionListener(evt -> {
            if (previousScreen != null) {
                previousScreen.setVisible(true);
            }
            this.dispose();
        });

        btnDownload = new JButton("Download");
        btnDownload.addActionListener(evt -> savetoJPG());

        bottomPanel.add(btnReturn);
        bottomPanel.add(btnDownload);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void displayTradingCard() {
        if (animal == null) {
            JLabel lblError = new JLabel("No animal data available");
            lblError.setHorizontalAlignment(SwingConstants.CENTER);
            lblError.setFont(new Font("Arial", Font.BOLD, 20));
            cardPanel.add(lblError);
            return;
        }

        // Animal name
        JLabel nameLabel = new JLabel("<html><center><b>" + animal.getName() + "</b></center></html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(Box.createVerticalStrut(50));
        cardPanel.add(nameLabel);

        // Divider
        cardPanel.add(Box.createVerticalStrut(12));
        cardPanel.add(makeGoldDivider());
        cardPanel.add(Box.createVerticalStrut(18));

        // --- Type ---
        cardPanel.add(makeStat("Type", animal.getGroup()));

        // --- Habitat ---
        cardPanel.add(makeStat("Habitat", animal.getHabitat()));

        // --- Diet ---
        cardPanel.add(makeStat("Diet", animal.getDiet()));

        // --- Distinctive Feature ---
        cardPanel.add(makeStat("Distinctive Feature", animal.getMostDistinctiveFeature()));

        cardPanel.add(Box.createVerticalStrut(20));

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JSeparator makeGoldDivider() {
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5)); // thickness
        sep.setForeground(new Color(212,175,55)); // GOLD
        return sep;
    }

    // Formatting for each stat
    private JLabel makeStat(String label, String value) {
        JLabel stat = new JLabel("<html><b>" + label + ":</b> " + value + "</html>");
        stat.setFont(new Font("Arial", Font.PLAIN, 18));
        stat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        stat.setAlignmentX(Component.LEFT_ALIGNMENT);
        return stat;
    }

    private void savetoJPG() {
        try {
            BufferedImage img = new BufferedImage(cardPanel.getWidth(), cardPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            cardPanel.paintAll(g2);
            g2.dispose();

            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(animal.getName() + "_card.jpg"));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                ImageIO.write(img, "jpg", chooser.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Trading card saved!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving the image.");
        }
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            Color top = new Color(255, 245, 180);    // soft yellow
            Color bottom = new Color(255, 255, 255); // white

            int w = getWidth();
            int h = getHeight();

            GradientPaint gp = new GradientPaint(0, 0, top, 0, h, bottom);
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);
        }
    }

    public static void main(String args[]) {
    }
}