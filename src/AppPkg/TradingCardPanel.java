package AppPkg;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Classes.GenerateTradingCard.TradingCardViewModel;

/**
 * Displays a generated card image and buttons
 */
public class TradingCardPanel extends JPanel {

    public TradingCardPanel(
            TradingCardViewModel viewModel,
            Runnable onBackClick,
            Runnable onDownloadClick,
            Runnable onSaveCardClick) {

        setLayout(new BorderLayout());

        final JLabel display = new JLabel(new ImageIcon(viewModel.getImage()));
        display.setHorizontalAlignment(SwingConstants.CENTER);

        final JButton back = new JButton("Back");
        final JButton download = new JButton("Download");
        final JButton save = new JButton("Save Card");

        back.addActionListener(event -> onBackClick.run());
        download.addActionListener(event -> onDownloadClick.run());
        save.addActionListener(event -> onSaveCardClick.run());

        final JPanel bottom = new JPanel();
        bottom.add(back);
        bottom.add(download);
        bottom.add(save);

        add(display, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
