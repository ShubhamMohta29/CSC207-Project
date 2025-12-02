package classes.filter;

import javax.swing.*;
import java.awt.*;

public class filterConstants {
    // OpenAI/Language Model Constants
    public static final int MAX_TOKENS = 500;
    public static final String MODEL = "openai/gpt-4o";

    // GUI Layout Constants
    public static final int FILTER_WINDOW_WIDTH = 500;
    public static final int FILTER_WINDOW_HEIGHT = 600;
    public static final int RESULTS_WINDOW_WIDTH = 300;
    public static final int RESULTS_WINDOW_HEIGHT = 400;
    public static final int BORDER_LAYOUT_HGAP = 8;
    public static final int BORDER_LAYOUT_VGAP = 8;

    // Component Sizing Constants
    public static final Dimension TAG_SCROLL_PREFERRED_SIZE = new Dimension(6, 45);
    public static final Dimension RESULTS_SCROLL_PREFERRED_SIZE = new Dimension(300, 200);
    public static final Dimension LIST_SCROLL_PREFERRED_SIZE = new Dimension(400, 300);
    public static final int TAG_TEXT_AREA_ROWS = 1;
    public static final int TAG_TEXT_AREA_COLUMNS = 5;

    // Grid Layout Constants
    public static final int CHECKBOX_GRID_COLUMNS = 3;
    public static final int GRID_INSET_SIZE = 3;
    public static final int SLIDERS_GRID_ROWS = 2;
    public static final int SLIDERS_GRID_COLUMNS = 2;
    public static final int SLIDERS_GRID_HGAP = 3;
    public static final int SLIDERS_GRID_VGAP = 3;

    // Font Constants
    public static final String FONT_NAME = "Tahoma";
    public static final int HEADING_FONT_SIZE = 15;
    public static final int HEADING_FONT_STYLE = Font.BOLD;
    public static final int RANGE_LABEL_FONT_SIZE = 9;
    public static final int RANGE_LABEL_FONT_STYLE = Font.BOLD;
    public static final int RESULTS_FONT_SIZE = 12;
    public static final int RESULTS_FONT_STYLE = Font.PLAIN;

    // Card Layout Constants
    public static final String CARD_FILTERS = "FILTERS";
    public static final String CARD_RESULTS = "RESULTS";

    // String Constants
    public static final String FILTER_BY_TITLE = "Filter By...";
    public static final String NO_FILTERS_TEXT = "No filters selected";
    public static final String RANGE_TEXT_FORMAT = "Range: %d - %d years";
    public static final String NO_RESULTS_MESSAGE = "No animals found matching your filters. Try different criteria.";

    // Checkbox Category Titles
    public static final String GROUP_TITLE = "Group";
    public static final String LOCATION_TITLE = "Location";
    public static final String DIET_TITLE = "Diet";

    // Group Options
    public static final String[] GROUP_OPTIONS = {
        "Mammal", "Bird", "Reptile", "Amphibian", "Fish", "Insect",
    };

    // Location Options
    public static final String[] LOCATION_OPTIONS = {
        "Africa", "Asia", "Europe", "North America",
        "South America", "Australia", "Antarctica",
    };

    // Diet Options
    public static final String[] DIET_OPTIONS = {
        "Herbivore", "Carnivore", "Omnivore", "Insectivore",
    };

    // Lifespan Slider Constants
    public static final String LIFESPAN_TITLE = "Lifespan Range (years)";
    public static final String MIN_LABEL = "Minimum:";
    public static final String MAX_LABEL = "Maximum:";
    public static final int MIN_LIFESPAN = 0;
    public static final int MAX_LIFESPAN = 100;
    public static final int DEFAULT_MIN_LIFESPAN = 0;
    public static final int DEFAULT_MAX_LIFESPAN = 100;
    public static final int SLIDER_MAJOR_TICK_SPACING = 15;
    public static final int SLIDER_MINOR_TICK_SPACING = 5;

    // Window Titles
    public static final String FILTER_WINDOW_TITLE = "Filter Animals";
    public static final String RESULTS_WINDOW_TITLE = "Filter Results";

    // FlowLayout Constants
    public static final int FLOW_LAYOUT_HGAP = 10;
    public static final int FLOW_LAYOUT_VGAP = 10;
    public static final int FLOW_LAYOUT_ALIGNMENT = FlowLayout.RIGHT;

    // Text Alignment Constants
    public static final int HEADING_ALIGNMENT = SwingConstants.LEFT;
    public static final int RANGE_LABEL_ALIGNMENT = JLabel.CENTER;
    public static final int NO_RESULTS_ALIGNMENT = SwingConstants.CENTER;

    // Scroll Policies
    public static final int VERTICAL_SCROLLBAR_POLICY = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
    public static final int HORIZONTAL_SCROLLBAR_POLICY = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;

    // List Selection Mode
    public static final int LIST_SELECTION_MODE = ListSelectionModel.SINGLE_SELECTION;

    // Text Wrapping
    public static final boolean LINE_WRAP = true;
    public static final boolean WRAP_STYLE_WORD = true;

    // Empty Border Constants
    public static final int EMPTY_BORDER_TOP = 50;
    public static final int EMPTY_BORDER_LEFT = 10;
    public static final int EMPTY_BORDER_BOTTOM = 50;
    public static final int EMPTY_BORDER_RIGHT = 10;

    // Colors (if needed elsewhere)
    public static final Color NO_RESULTS_COLOR = Color.RED;
}