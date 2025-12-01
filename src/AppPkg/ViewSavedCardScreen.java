package AppPkg;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewSavedCardScreen extends JFrame {

    public ViewSavedCardScreen(String cardName, BufferedImage image) {
        setTitle(cardName);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 550);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout());

        JLabel full = new JLabel(new ImageIcon(
                image.getScaledInstance(350, 500, Image.SCALE_SMOOTH)
        ));
        full.setHorizontalAlignment(SwingConstants.CENTER);

        main.add(full, BorderLayout.CENTER);

        JButton back = new JButton("Back to Library");
        back.addActionListener(e -> {
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(back);

        main.add(bottom, BorderLayout.SOUTH);

        add(main);
    }
}
