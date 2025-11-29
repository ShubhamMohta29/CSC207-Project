package AppPkg;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Classes.GenerateTradingCard.TradingCardViewModel;

/**
 * Displays a generated card image + buttons.
 */
public class TradingCardPanel extends JPanel {

    public TradingCardPanel(
            TradingCardViewModel viewModel,
            Runnable onBackClick,
            Runnable onDownloadClick) {

        setLayout(new BorderLayout());

        final JLabel display = new JLabel(new ImageIcon(viewModel.getImage()));
        display.setHorizontalAlignment(SwingConstants.CENTER);

        final JButton back = new JButton("Back");
        final JButton download = new JButton("Download");

        back.addActionListener(event -> onBackClick.run());
        download.addActionListener(event -> onDownloadClick.run());

        final JPanel bottom = new JPanel();
        bottom.add(back);
        bottom.add(download);

        add(display, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
