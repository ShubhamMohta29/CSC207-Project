package Classes.GenerateTradingCard;

import Classes.Animal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GenerateTradingCardInteractor implements GenerateTradingCardInputBoundary {

    private final GenerateTradingCardOutputBoundary presenter;

    public GenerateTradingCardInteractor(GenerateTradingCardOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public TradingCardViewModel generate(GenerateTradingCardRequestModel requestModel) {
        Animal animal = requestModel.getAnimal();

        int width = 480;
        int height = 640;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Background purple gradient
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(120, 40, 160),
                0, height, new Color(200, 120, 255)
        );
        g.setPaint(gp);
        g.fillRect(0, 0, width, height);

        // Gold frame
        g.setColor(new Color(212, 175, 55)); // gold
        int frameThickness = 10;
        for (int i = 0; i < frameThickness; i++) {
            g.drawRect(i, i, width - 2 * i - 1, height - 2 * i - 1);
        }

        // Inner content area
        int margin = 30;
        int innerLeft = margin;
        int innerRight = width - margin;
        int innerWidth = innerRight - innerLeft;

        // Title (animal name)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        int y = 90;
        drawCentered(g, animal.getName(), width, y);

        // Divider line
        g.setColor(new Color(240, 220, 120));
        g.fillRect(innerLeft, y + 15, innerWidth, 4);

        // Stats text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        y = y + 60; // move below divider

        y = drawWrappedStat(g, "Type", animal.getGroup(), innerLeft, y, innerWidth);
        y = drawWrappedStat(g, "Habitat", animal.getHabitat(), innerLeft, y, innerWidth);
        y = drawWrappedStat(g, "Diet", animal.getDiet(), innerLeft, y, innerWidth);
        y = drawWrappedStat(g, "Distinctive Feature",
                animal.getMostDistinctiveFeature(), innerLeft, y, innerWidth);

        g.dispose();

        // Interactor -> Response -> Presenter -> ViewModel
        GenerateTradingCardResponseModel responseModel = new GenerateTradingCardResponseModel(img);
        return presenter.prepareSuccessView(responseModel);
    }

    private void drawCentered(Graphics2D g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    // Display info with word wrapping.
    private int drawWrappedStat(Graphics2D g, String label, String value,
                                int x, int startY, int maxWidth) {
        String full = label + ": " + value;
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();

        String[] words = full.split(" ");
        StringBuilder line = new StringBuilder();
        int y = startY;

        for (String word : words) {
            String testLine = line.length() == 0 ? word : line + " " + word;
            int testWidth = fm.stringWidth(testLine);

            if (testWidth > maxWidth) {
                // draw current line
                g.drawString(line.toString(), x, y);
                y += lineHeight;
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(testLine);
            }
        }

        // draw last line
        if (line.length() > 0) {
            g.drawString(line.toString(), x, y);
            y += lineHeight;
        }

        // extra spacing between stats
        y += 10;
        return y;
    }
}