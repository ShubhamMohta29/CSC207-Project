/**
 * FilterGUI : Handles filters, sliders, tags, and results.
 *  REMEMBER: OR within a category, AND across categories
 */

package AppPkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import classes.filter.filterController;
import classes.filter.FilterViewModel;
import classes.filter.filterConstants;
import classes.retrieveInfo.animal;

import static classes.settings.settingConstants.DEFAULT_SETTINGS_FILE;

public class FilterGUI extends JFrame {

    private JLabel lblLifespanRange;
    private JPanel pnlFilters;
    private JTextArea tagsTextArea;

    private JCheckBox[] groupCheckboxes;
    private JCheckBox[] locationCheckboxes;
    private JCheckBox[] dietCheckboxes;

    private JSlider minLifespanSlider;
    private JSlider maxLifespanSlider;

    private JButton btnLoadMore;
    private JButton btnApply;
    private JButton btnReset;
    private JButton btnClose;

    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);

    private List<String> selectedTags;
    private final filterController filterController;
    private final FilterViewModel filterViewModel;

    private JPanel resultsPanel;
    private JScrollPane resultsScroll;
    private JPanel topPanel;
    private JScrollPane filtersScroll;
    private JPanel resultsContainer;
    private JPanel buttonPanel;
    private JPanel mainContentPanel;
    private List<animal> allLoadedAnimals = new ArrayList<>();

    public FilterGUI(JFrame parent, filterController filterController, FilterViewModel filterViewModel) {
        super();
        this.filterController = filterController;
        this.filterViewModel = filterViewModel;
        selectedTags = new ArrayList<>();
        initComponents();
        setupListeners();
        setLocationRelativeTo(parent);
        config.updateALL(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(
                filterConstants.BORDER_LAYOUT_HGAP,
                filterConstants.BORDER_LAYOUT_VGAP
        ));
        setPreferredSize(new Dimension(
                filterConstants.FILTER_WINDOW_WIDTH,
                filterConstants.FILTER_WINDOW_HEIGHT
        ));

        createTopPanel();
        createFiltersPanel();
        createResultsPanel();
        createMainContentPanel();
        createButtonsPanel();

        add(topPanel, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        showFiltersView();
        revalidate();
        pack();
    }

    private void createTopPanel() {
        JLabel lblHeading = new JLabel(
                filterConstants.FILTER_BY_TITLE,
                filterConstants.HEADING_ALIGNMENT
        );
        lblHeading.setFont(new Font(
                filterConstants.FONT_NAME,
                filterConstants.HEADING_FONT_STYLE,
                filterConstants.HEADING_FONT_SIZE
        ));

        tagsTextArea = new JTextArea(
                filterConstants.TAG_TEXT_AREA_ROWS,
                filterConstants.TAG_TEXT_AREA_COLUMNS
        );
        tagsTextArea.setEditable(false);
        tagsTextArea.setLineWrap(filterConstants.LINE_WRAP);
        tagsTextArea.setWrapStyleWord(filterConstants.WRAP_STYLE_WORD);

        JScrollPane tagScroll = new JScrollPane(tagsTextArea);
        tagScroll.setVerticalScrollBarPolicy(filterConstants.VERTICAL_SCROLLBAR_POLICY);
        tagScroll.setPreferredSize(filterConstants.TAG_SCROLL_PREFERRED_SIZE);

        topPanel = new JPanel(new BorderLayout(
                filterConstants.GRID_INSET_SIZE,
                filterConstants.GRID_INSET_SIZE
        ));
        topPanel.add(lblHeading, BorderLayout.NORTH);
        topPanel.add(tagScroll, BorderLayout.SOUTH);
    }

    private void createFiltersPanel() {
        pnlFilters = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(
                filterConstants.GRID_INSET_SIZE,
                filterConstants.GRID_INSET_SIZE,
                filterConstants.GRID_INSET_SIZE,
                filterConstants.GRID_INSET_SIZE
        );

        gbc.gridy = 0; pnlFilters.add(createCheckboxPanel(
                filterConstants.GROUP_TITLE,
                filterConstants.GROUP_OPTIONS
        ), gbc);
        gbc.gridy = 1; pnlFilters.add(createCheckboxPanel(
                filterConstants.LOCATION_TITLE,
                filterConstants.LOCATION_OPTIONS
        ), gbc);
        gbc.gridy = 2; pnlFilters.add(createCheckboxPanel(
                filterConstants.DIET_TITLE,
                filterConstants.DIET_OPTIONS
        ), gbc);
        gbc.gridy = 3; pnlFilters.add(createRangeSliderPanel(), gbc);

        filtersScroll = new JScrollPane(pnlFilters);
    }

    private void createResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScroll = new JScrollPane(resultsPanel);
        resultsScroll.setVerticalScrollBarPolicy(filterConstants.VERTICAL_SCROLLBAR_POLICY);
        resultsScroll.setHorizontalScrollBarPolicy(filterConstants.HORIZONTAL_SCROLLBAR_POLICY);
        resultsScroll.setPreferredSize(filterConstants.RESULTS_SCROLL_PREFERRED_SIZE);

        btnLoadMore = new JButton("Load More");
        btnLoadMore.setEnabled(false);

        resultsContainer = new JPanel(new BorderLayout());
        resultsContainer.add(resultsScroll, BorderLayout.CENTER);
        resultsContainer.add(btnLoadMore, BorderLayout.SOUTH);
    }

    private void createMainContentPanel() {
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.add(filtersScroll, filterConstants.CARD_FILTERS);
        mainContentPanel.add(resultsContainer, filterConstants.CARD_RESULTS);
    }

    private void createButtonsPanel() {
        btnApply = new JButton("Apply");
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        buttonPanel = new JPanel(new FlowLayout(
                filterConstants.FLOW_LAYOUT_ALIGNMENT,
                filterConstants.FLOW_LAYOUT_HGAP,
                filterConstants.FLOW_LAYOUT_VGAP
        ));
        updateButtonPanel(btnReset, btnApply, btnClose);
    }

    private void updateButtonPanel(JButton... buttons) {
        buttonPanel.removeAll();
        for (JButton b : buttons) if(b != null) buttonPanel.add(b);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void setupListeners() {
        btnApply.addActionListener(e -> { applyFilters(); showResultsView(); });
        btnLoadMore.addActionListener(e -> loadMoreResults());
        btnReset.addActionListener(e -> { resetFilters(); showFiltersView(); });
        btnClose.addActionListener(e -> dispose());
    }

    private JPanel createCheckboxPanel(String title, String[] options) {
        JPanel panel = new JPanel(new GridLayout(
                0,
                filterConstants.CHECKBOX_GRID_COLUMNS,
                1,
                1
        ));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JCheckBox[] checkboxes = new JCheckBox[options.length];
        for (int i=0; i<options.length; i++) {
            String option = options[i];
            JCheckBox cb = new JCheckBox(option);
            cb.addItemListener(e -> {
                if(cb.isSelected()) selectedTags.add(option);
                else selectedTags.remove(option);
                updateTagLabel();
            });
            checkboxes[i] = cb;
            panel.add(cb);
        }

        switch(title){
            case filterConstants.GROUP_TITLE -> groupCheckboxes = checkboxes;
            case filterConstants.LOCATION_TITLE -> locationCheckboxes = checkboxes;
            case filterConstants.DIET_TITLE -> dietCheckboxes = checkboxes;
        }
        return panel;
    }

    private JPanel createRangeSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(filterConstants.LIFESPAN_TITLE));

        minLifespanSlider = new JSlider(
                filterConstants.MIN_LIFESPAN,
                filterConstants.MAX_LIFESPAN,
                filterConstants.DEFAULT_MIN_LIFESPAN
        );
        maxLifespanSlider = new JSlider(
                filterConstants.MIN_LIFESPAN,
                filterConstants.MAX_LIFESPAN,
                filterConstants.DEFAULT_MAX_LIFESPAN
        );
        configureSlider(minLifespanSlider);
        configureSlider(maxLifespanSlider);

        lblLifespanRange = new JLabel(
                String.format(
                        filterConstants.RANGE_TEXT_FORMAT,
                        filterConstants.DEFAULT_MIN_LIFESPAN,
                        filterConstants.DEFAULT_MAX_LIFESPAN
                ),
                filterConstants.RANGE_LABEL_ALIGNMENT
        );
        lblLifespanRange.setFont(new Font(
                filterConstants.FONT_NAME,
                filterConstants.RANGE_LABEL_FONT_STYLE,
                filterConstants.RANGE_LABEL_FONT_SIZE
        ));

        minLifespanSlider.addChangeListener(e -> {
            if(minLifespanSlider.getValue() > maxLifespanSlider.getValue())
                minLifespanSlider.setValue(maxLifespanSlider.getValue());
            updateRangeDisplay();
        });
        maxLifespanSlider.addChangeListener(e -> {
            if(maxLifespanSlider.getValue() < minLifespanSlider.getValue())
                maxLifespanSlider.setValue(minLifespanSlider.getValue());
            updateRangeDisplay();
        });

        JPanel slidersPanel = new JPanel(new GridLayout(
                filterConstants.SLIDERS_GRID_ROWS,
                filterConstants.SLIDERS_GRID_COLUMNS,
                filterConstants.SLIDERS_GRID_HGAP,
                filterConstants.SLIDERS_GRID_VGAP
        ));
        slidersPanel.add(new JLabel(filterConstants.MIN_LABEL));
        slidersPanel.add(minLifespanSlider);
        slidersPanel.add(new JLabel(filterConstants.MAX_LABEL));
        slidersPanel.add(maxLifespanSlider);

        panel.add(slidersPanel, BorderLayout.CENTER);
        panel.add(lblLifespanRange, BorderLayout.SOUTH);
        return panel;
    }

    private void configureSlider(JSlider slider){
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(filterConstants.SLIDER_MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(filterConstants.SLIDER_MINOR_TICK_SPACING);
    }

    private void updateRangeDisplay() {
        lblLifespanRange.setText(
                String.format(
                        filterConstants.RANGE_TEXT_FORMAT,
                        minLifespanSlider.getValue(),
                        maxLifespanSlider.getValue()
                )
        );
    }

    private void updateTagLabel() {
        tagsTextArea.setText(
                selectedTags.isEmpty() ?
                        filterConstants.NO_FILTERS_TEXT :
                        String.join(", ", selectedTags)
        );
    }

    private List<String> getSelectedGroups() { return getSelectedFromArray(groupCheckboxes); }
    private List<String> getSelectedLocations() { return getSelectedFromArray(locationCheckboxes); }
    private List<String> getSelectedDiets() { return getSelectedFromArray(dietCheckboxes); }
    private List<String> getSelectedFromArray(JCheckBox[] arr) {
        List<String> selected = new ArrayList<>();
        for(JCheckBox cb: arr) if(cb.isSelected()) selected.add(cb.getText());
        return selected;
    }

    private void showResultsView() {
        ((CardLayout) mainContentPanel.getLayout()).show(
                mainContentPanel,
                filterConstants.CARD_RESULTS
        );
        updateButtonPanel(btnReset, filterViewModel.hasMore() ? btnLoadMore : null, btnClose);
        setTitle(filterConstants.RESULTS_WINDOW_TITLE);
        setMinimumSize(new Dimension(
                filterConstants.RESULTS_WINDOW_WIDTH,
                filterConstants.RESULTS_WINDOW_HEIGHT
        ));
        pack();
    }

    private void showFiltersView() {
        ((CardLayout) mainContentPanel.getLayout()).show(
                mainContentPanel,
                filterConstants.CARD_FILTERS
        );
        updateButtonPanel(btnReset, btnApply, btnClose);
        setTitle(filterConstants.FILTER_WINDOW_TITLE);
        setPreferredSize(new Dimension(
                filterConstants.FILTER_WINDOW_WIDTH,
                filterConstants.FILTER_WINDOW_HEIGHT
        ));
        pack();
    }

    private void resetFilters() {
        selectedTags.clear();
        setSelectedAll(groupCheckboxes,false);
        setSelectedAll(locationCheckboxes,false);
        setSelectedAll(dietCheckboxes,false);
        minLifespanSlider.setValue(filterConstants.DEFAULT_MIN_LIFESPAN);
        maxLifespanSlider.setValue(filterConstants.DEFAULT_MAX_LIFESPAN);
        updateTagLabel();
        updateRangeDisplay();

        resultsPanel.removeAll();
        resultsPanel.revalidate();
        allLoadedAnimals.clear();
        resultsPanel.repaint();
        btnLoadMore.setEnabled(false);
    }

    private void setSelectedAll(JCheckBox[] arr, boolean selected){
        for(JCheckBox cb: arr) cb.setSelected(selected);
    }

    private void updateResultsFromViewModel() {
        System.out.println("Updating results from view model");

        resultsPanel.removeAll();
        allLoadedAnimals.clear();

        List<animal> animals = filterViewModel.getAnimals();
        allLoadedAnimals.addAll(animals);

        if (animals.isEmpty()) {
            showNoResultsMessage();
        } else {
            showResultsList(animals);
        }

        config.updateALL(this);
    }

    private void appendResultsFromViewModel() {
        System.out.println("Appending results from view model");

        List<animal> newAnimals = filterViewModel.getAnimals();
        if(newAnimals.isEmpty()){
            System.out.println("No more animals found");
            btnLoadMore.setEnabled(false);
            return;
        }

        allLoadedAnimals.addAll(newAnimals);

        JScrollPane scrollPane = (JScrollPane) resultsPanel.getComponent(0);
        JList<String> resultsList = (JList<String>) scrollPane.getViewport().getView();
        DefaultListModel<String> model = (DefaultListModel<String>) resultsList.getModel();
        resultsList.clearSelection();

        for(animal animal : newAnimals) model.addElement(animal.getName());

        updateClickListener(resultsList, allLoadedAnimals);
        btnLoadMore.setEnabled(filterViewModel.hasMore());

        System.out.println("Total animals now: " + model.getSize());
        config.updateALL(this);
    }

    private void showNoResultsMessage() {
        JPanel noResultsPanel = new JPanel(new BorderLayout());
        JLabel noResultsLabel = new JLabel(filterConstants.NO_RESULTS_MESSAGE);
        noResultsLabel.setForeground(filterConstants.NO_RESULTS_COLOR);
        noResultsLabel.setHorizontalAlignment(filterConstants.NO_RESULTS_ALIGNMENT);
        noResultsLabel.setFont(new Font(
                filterConstants.FONT_NAME,
                filterConstants.HEADING_FONT_STYLE,
                filterConstants.RESULTS_FONT_SIZE
        ));
        noResultsPanel.add(noResultsLabel, BorderLayout.CENTER);
        noResultsPanel.setBorder(BorderFactory.createEmptyBorder(
                filterConstants.EMPTY_BORDER_TOP,
                filterConstants.EMPTY_BORDER_LEFT,
                filterConstants.EMPTY_BORDER_BOTTOM,
                filterConstants.EMPTY_BORDER_RIGHT
        ));
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(noResultsPanel, BorderLayout.CENTER);
    }

    private void showResultsList(List<animal> animals) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (animal a : animals) model.addElement(a.getName());

        JList<String> resultsList = new JList<>(model);
        resultsList.setSelectionMode(filterConstants.LIST_SELECTION_MODE);
        resultsList.setFont(new Font(
                filterConstants.FONT_NAME,
                filterConstants.RESULTS_FONT_STYLE,
                filterConstants.RESULTS_FONT_SIZE
        ));
        resultsList.clearSelection();

        updateClickListener(resultsList, allLoadedAnimals);

        JScrollPane listScrollPane = new JScrollPane(resultsList);
        listScrollPane.setPreferredSize(filterConstants.LIST_SCROLL_PREFERRED_SIZE);

        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(listScrollPane, BorderLayout.CENTER);

        btnLoadMore.setEnabled(filterViewModel.hasMore());
        SwingUtilities.invokeLater(() -> resultsScroll.getVerticalScrollBar().setValue(0));
    }

    private void applyFilters(){
        int min = minLifespanSlider.getValue();
        int max = maxLifespanSlider.getValue();

        List<String> groups = getSelectedGroups();
        List<String> locations = getSelectedLocations();
        List<String> diets = getSelectedDiets();

        System.out.println("Filters - Groups: " + groups + ", Locations: " + locations + ", Diets: " + diets + ", Lifespan: " + min + "-" + max);

        filterController.filterAnimals(groups, locations, diets, min, max, null);
        updateResultsFromViewModel();

        System.out.println("Controller called, animals in filterViewModel: " + filterViewModel.getAnimals().size());
    }

    private void loadMoreResults() {
        filterController.filterAnimals(getSelectedGroups(), getSelectedLocations(), getSelectedDiets(),
                minLifespanSlider.getValue(), maxLifespanSlider.getValue(), filterViewModel.getNextCursor());
        System.out.println("FilterViewModel animals after filter before update call: " + filterViewModel.getAnimals().size());
        if (filterViewModel.getAnimals() == null) {
            filterViewModel.setAnimals(new ArrayList<>());
        }
        appendResultsFromViewModel();
        System.out.println("FilterViewModel animals after filter after call: " + filterViewModel.getAnimals().size());
    }

    private void updateClickListener(JList<String> resultsList, List<animal> allAnimals) {
        for(java.awt.event.MouseListener listener : resultsList.getMouseListeners())
            resultsList.removeMouseListener(listener);

        final List<animal> currentAnimals = new ArrayList<>(allAnimals);
        resultsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedIndex = resultsList.locationToIndex(evt.getPoint());
                    if (selectedIndex >= 0 && selectedIndex < currentAnimals.size()) {
                        resultsList.clearSelection();
                        animal selectedAnimal = currentAnimals.get(selectedIndex);
                        new SuccesfulSearch(selectedAnimal).setVisible(true);
                    }
                }
            }
        });
    }
}