package AppPkg.UIStrategy;

import javax.swing.*;
import java.util.*;

public class StrategyMap {
    private final Map<Class<?>, UIStyleStrategy> strategies = new HashMap<>();
    private final UIStyleStrategy defaultStrategy = new DefaultUIStrategy();

    //maps the strategy for different UI component with strategy interface
    public StrategyMap() {
        strategies.put(JScrollPane.class, new ScrollPaneStrategy());
        strategies.put(JPanel.class, new JPanelStrategy());
        //add more if needed
    }

    public UIStyleStrategy getStrategy(Class<?> compClass) {
        UIStyleStrategy s = strategies.get(compClass);
        if (s != null) {
            return s;
        } else {
            return defaultStrategy;
        }

    }
}
