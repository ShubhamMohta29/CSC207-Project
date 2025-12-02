package Classes.GenerateTradingCard;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import Classes.retrieveInfo.Animal;
import Classes.GenerateTradingCard.CardImageGenerator;

import static Classes.GenerateTradingCard.CardAppearanceConstants.*;

/**
 * Full card generator producing the final image.
 */
public final class Java2dCardGenerator implements CardImageGenerator {

    @Override
    public BufferedImage generate(final Animal animal) {

        BufferedImage img = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Background + border
        g.setClip(new RoundRectangle2D.Float(0,0,CARD_WIDTH,CARD_HEIGHT,CARD_CORNER_RADIUS,CARD_CORNER_RADIUS));
        g.setPaint(new GradientPaint(0,0,
                new Color(COLOR_TOP_R,COLOR_TOP_G,COLOR_TOP_B),
                CARD_WIDTH,CARD_HEIGHT,
                new Color(COLOR_BOTTOM_R,COLOR_BOTTOM_G,COLOR_BOTTOM_B)));
        g.fillRect(0,0,CARD_WIDTH,CARD_HEIGHT);

        g.setClip(null);
        g.setColor(new Color(212,175,55));
        g.setStroke(new BasicStroke(CARD_BORDER_STROKE));
        g.draw(new RoundRectangle2D.Float(CARD_BORDER_MARGIN,CARD_BORDER_MARGIN,
                CARD_WIDTH - CARD_BORDER_MARGIN*2,
                CARD_HEIGHT - CARD_BORDER_MARGIN*2,
                CARD_BORDER_RADIUS,CARD_BORDER_RADIUS));

        int y = drawTitle(g, animal.getName());
        y = drawDivider(g,y);
        y = drawWrappedText(g, animal.getMostDistinctiveFeature(), y);
        y = drawDivider(g,y);

        y = drawStat(g,"Type",animal.getGroup(),y);
        y = drawStat(g,"Habitat",animal.getHabitat(),y);
        y = drawStat(g,"Diet",animal.getDiet(),y);
        drawStat(g,"Distinctive Feature",animal.getMostDistinctiveFeature(),y);

        g.dispose();
        return img;
    }

    private int drawTitle(Graphics2D g,String name){
        name = clean(name);
        g.setFont(new Font("Serif",Font.BOLD,TITLE_SIZE));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(name,(CARD_WIDTH - fm.stringWidth(name))/2,TITLE_Y);
        return TITLE_NEXT_Y;
    }

    private int drawDivider(Graphics2D g,int y){
        g.setColor(new Color(240,220,120));
        g.fillRect(STAT_LEFT_MARGIN,y,CARD_WIDTH - SLOGAN_MAX_WIDTH_OFFSET,DIVIDER_HEIGHT);
        return y + SECTION_PADDING;
    }

    private int drawWrappedText(Graphics2D g,String text,int y){
        text = clean(text);
        g.setFont(new Font("Serif",Font.ITALIC,SLOGAN_FONT_SIZE));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int h = fm.getHeight();

        for(String line: wrap(text,fm,CARD_WIDTH - SLOGAN_MAX_WIDTH_OFFSET)){
            g.drawString(line,(CARD_WIDTH - fm.stringWidth(line)) / 2,y);
            y += h;
        }
        return y;
    }

    private int drawStat(Graphics2D g,String label,String val,int y){
        g.setFont(new Font("Serif",Font.PLAIN,SLOGAN_FONT_SIZE));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int h = fm.getHeight();
        val = clean(val);

        for(String line: wrap(label+": "+val,fm,CARD_WIDTH - SLOGAN_MAX_WIDTH_OFFSET)){
            g.drawString(line,STAT_LEFT_MARGIN,y);
            y += h;
        }
        return y + STAT_PADDING;
    }

    private java.util.List<String> wrap(String text,FontMetrics fm,int max){
        java.util.List<String> out=new java.util.ArrayList<>();
        StringBuilder line=new StringBuilder();
        for(String w:text.split(" ")){
            String test=line.length()==0?w:line+" "+w;
            if(fm.stringWidth(test) > max){
                out.add(line.toString());
                line=new StringBuilder(w);
            }else line=new StringBuilder(test);
        }
        out.add(line.toString());
        return out;
    }

    private String clean(String s){
        return (s==null||s.isBlank()||s.equalsIgnoreCase("N/A"))?"mystery":s.trim();
    }
}
