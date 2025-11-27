package Classes.Settings;
import java.awt.*;

public class TextSettingPresenter implements TextSettingOutputBoundary {
    private final Color fg;
    private final String font;
    private final int size;

    TextSettingPresenter(TextSettingOutput config) {
        this.fg = config.getColor();
        this.font = config.getFont();
        this.size = config.getSize();
    }

    @Override
    public Color getColor(){
        return fg;
    }

    @Override
    public String getFont(){
        return font;
    }

    @Override
    public int getSize() {
        return size;
    }
}
