package Classes.GenerateTradingCard;

import Classes.Animal;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class GenerateTradingCardInteractor implements GenerateTradingCardInputBoundary {

    private final GenerateTradingCardOutputBoundary presenter;

    public GenerateTradingCardInteractor(GenerateTradingCardOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public TradingCardViewModel generate(GenerateTradingCardRequestModel requestModel) {
        Animal animal = requestModel.getAnimal();

        int width = 320;
        int height = 480;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // High-quality rendering
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Rounded clipping
        int arc = 40;
        RoundRectangle2D rounded = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
        g.setClip(rounded);

        // Background gradient
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(130, 60, 190),
                width, height, new Color(220, 150, 255)
        );
        g.setPaint(gp);
        g.fillRect(0, 0, width, height);

        // Gloss highlight
        GradientPaint gloss = new GradientPaint(
                0, 0, new Color(255, 255, 255, 110),
                width, height / 3f, new Color(255, 255, 255, 0)
        );
        g.setPaint(gloss);
        g.fillRect(0, 0, width, height);

        // Remove clip for gold frame
        g.setClip(null);

        // Gold border
        int frameThickness = 10;
        g.setStroke(new BasicStroke(frameThickness));
        g.setColor(new Color(212, 175, 55));
        int inset = frameThickness / 2;

        RoundRectangle2D frame = new RoundRectangle2D.Float(
                inset, inset,
                width - frameThickness,
                height - frameThickness,
                20, 20
        );
        g.draw(frame);

        // Inner layout
        int margin = 30;
        int innerLeft = margin;
        int innerRight = width - margin;
        int innerWidth = innerRight - innerLeft;

        // Title — wrapped + centered
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 32));
        int y = 90;

        y = drawWrappedCenteredText(
                g,
                defaultValue(animal.getName()),
                width,
                y,
                width - 60
        );

        // Divider
        g.setColor(new Color(240, 220, 120));
        g.fillRect(innerLeft, y + 10, innerWidth, 4);

        // Slogan
        String slogan = sloganOrDefault(animal.getSlogan());
        g.setColor(new Color(255, 240, 255));
        g.setFont(new Font("Serif", Font.ITALIC, 18));

        int sloganY = y + 40;
        FontMetrics fmSlogan = g.getFontMetrics();
        int sloganX = (width - fmSlogan.stringWidth(slogan)) / 2;
        g.drawString(slogan, sloganX, sloganY);

        // Stats
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 18));

        int statsY = sloganY + 35;

        statsY = drawWrappedStat(g, "Type", defaultValue(animal.getGroup()), innerLeft, statsY, innerWidth);
        statsY = drawWrappedStat(g, "Habitat", defaultValue(animal.getHabitat()), innerLeft, statsY, innerWidth);
        statsY = drawWrappedStat(g, "Diet", defaultValue(animal.getDiet()), innerLeft, statsY, innerWidth);
        statsY = drawWrappedStat(g, "Distinctive Feature",
                defaultValue(animal.getMostDistinctiveFeature()), innerLeft, statsY, innerWidth);

        g.dispose();


        TradingCardViewModel vm =
                presenter.prepareSuccessView(new GenerateTradingCardResponseModel(img, animal));

        return vm;
    }

    // HELPERS

    private String defaultValue(String s) {
        if (s == null) return "mystery";
        s = s.trim();
        if (s.isEmpty()) return "mystery";
        if (s.equalsIgnoreCase("n/a") || s.equalsIgnoreCase("none") || s.equalsIgnoreCase("unknown"))
            return "mystery";
        return s;
    }

    private int drawWrappedStat(Graphics2D g, String label, String value,
                                int x, int startY, int maxWidth) {

        String full = label + ": " + value;
        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();

        String[] words = full.split(" ");
        StringBuilder line = new StringBuilder();
        int y = startY;

        for (String word : words) {
            String test = (line.length() == 0) ? word : line + " " + word;

            if (fm.stringWidth(test) > maxWidth) {
                g.drawString(line.toString(), x, y);
                y += lineHeight;
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(test);
            }
        }

        //last line
        if (!line.isEmpty()) {
            g.drawString(line.toString(), x, y);
            y += lineHeight;
        }

        y += 10;
        return y;
    }

    private int drawWrappedCenteredText(Graphics2D g, String text,
                                        int width, int startY, int maxWidth) {

        FontMetrics fm = g.getFontMetrics();
        int lineHeight = fm.getHeight();

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int y = startY;

        for (String word : words) {
            String test = (line.length() == 0) ? word : line + " " + word;

            if (fm.stringWidth(test) > maxWidth) {
                int x = (width - fm.stringWidth(line.toString())) / 2;
                g.drawString(line.toString(), x, y);
                y += lineHeight;
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(test);
            }
        }

        // draw last line
        int x = (width - fm.stringWidth(line.toString())) / 2;
        g.drawString(line.toString(), x, y);
        y += 5;

        return y;
    }
    private String sloganOrDefault(String s) {
        if (s == null ||
                s.trim().isEmpty() ||
                s.equalsIgnoreCase("n/a") ||
                s.equalsIgnoreCase("none") ||
                s.equalsIgnoreCase("") ||
                s.equalsIgnoreCase("unknown")) {

            return "A creature full of wonder.";
        }
        return s.trim();
    }
}
