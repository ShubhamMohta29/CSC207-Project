/**
 * FilterGUI : Handles filters, sliders, tags, and results.
 *  REMEMBER: OR within a category, AND across categories
 */

package AppPkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Classes.Filter.FilterController;
import Classes.Filter.FilterViewModel;
import Classes.Filter.FilterConstants;
import Classes.retrieveInfo.Animal;

import static Classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;

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
    private final FilterController filterController;
    private final FilterViewModel filterViewModel;

    private JPanel resultsPanel;
    private JScrollPane resultsScroll;
    private JPanel topPanel;
    private JScrollPane filtersScroll;
    private JPanel resultsContainer;
    private JPanel buttonPanel;
    private JPanel mainContentPanel;
    private List<Animal> allLoadedAnimals = new ArrayList<>();

    public FilterGUI(JFrame parent, FilterController filterController, FilterViewModel filterViewModel) {
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
                FilterConstants.BORDER_LAYOUT_HGAP,
                FilterConstants.BORDER_LAYOUT_VGAP
        ));
        setPreferredSize(new Dimension(
                FilterConstants.FILTER_WINDOW_WIDTH,
                FilterConstants.FILTER_WINDOW_HEIGHT
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
        pack();
    }

    private void createTopPanel() {
        JLabel lblHeading = new JLabel(
                FilterConstants.FILTER_BY_TITLE,
                FilterConstants.HEADING_ALIGNMENT
        );
        lblHeading.setFont(new Font(
                FilterConstants.FONT_NAME,
                FilterConstants.HEADING_FONT_STYLE,
                FilterConstants.HEADING_FONT_SIZE
        ));

        tagsTextArea = new JTextArea(
                FilterConstants.TAG_TEXT_AREA_ROWS,
                FilterConstants.TAG_TEXT_AREA_COLUMNS
        );
        tagsTextArea.setEditable(false);
        tagsTextArea.setLineWrap(FilterConstants.LINE_WRAP);
        tagsTextArea.setWrapStyleWord(FilterConstants.WRAP_STYLE_WORD);

        JScrollPane tagScroll = new JScrollPane(tagsTextArea);
        tagScroll.setVerticalScrollBarPolicy(FilterConstants.VERTICAL_SCROLLBAR_POLICY);
        tagScroll.setPreferredSize(FilterConstants.TAG_SCROLL_PREFERRED_SIZE);

        topPanel = new JPanel(new BorderLayout(
                FilterConstants.GRID_INSET_SIZE,
                FilterConstants.GRID_INSET_SIZE
        ));
        topPanel.add(lblHeading, BorderLayout.NORTH);
        topPanel.add(tagScroll, BorderLayout.SOUTH);
    }

    private void createFiltersPanel() {
        pnlFilters = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(
                FilterConstants.GRID_INSET_SIZE,
                FilterConstants.GRID_INSET_SIZE,
                FilterConstants.GRID_INSET_SIZE,
                FilterConstants.GRID_INSET_SIZE
        );

        gbc.gridy = 0; pnlFilters.add(createCheckboxPanel(
                FilterConstants.GROUP_TITLE,
                FilterConstants.GROUP_OPTIONS
        ), gbc);
        gbc.gridy = 1; pnlFilters.add(createCheckboxPanel(
                FilterConstants.LOCATION_TITLE,
                FilterConstants.LOCATION_OPTIONS
        ), gbc);
        gbc.gridy = 2; pnlFilters.add(createCheckboxPanel(
                FilterConstants.DIET_TITLE,
                FilterConstants.DIET_OPTIONS
        ), gbc);
        gbc.gridy = 3; pnlFilters.add(createRangeSliderPanel(), gbc);

        filtersScroll = new JScrollPane(pnlFilters);
    }

    private void createResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScroll = new JScrollPane(resultsPanel);
        resultsScroll.setVerticalScrollBarPolicy(FilterConstants.VERTICAL_SCROLLBAR_POLICY);
        resultsScroll.setHorizontalScrollBarPolicy(FilterConstants.HORIZONTAL_SCROLLBAR_POLICY);
        resultsScroll.setPreferredSize(FilterConstants.RESULTS_SCROLL_PREFERRED_SIZE);

        btnLoadMore = new JButton("Load More");
        btnLoadMore.setEnabled(false);

        resultsContainer = new JPanel(new BorderLayout());
        resultsContainer.add(resultsScroll, BorderLayout.CENTER);
        resultsContainer.add(btnLoadMore, BorderLayout.SOUTH);
    }

    private void createMainContentPanel() {
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.add(filtersScroll, FilterConstants.CARD_FILTERS);
        mainContentPanel.add(resultsContainer, FilterConstants.CARD_RESULTS);
    }

    private void createButtonsPanel() {
        btnApply = new JButton("Apply");
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        buttonPanel = new JPanel(new FlowLayout(
                FilterConstants.FLOW_LAYOUT_ALIGNMENT,
                FilterConstants.FLOW_LAYOUT_HGAP,
                FilterConstants.FLOW_LAYOUT_VGAP
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
                FilterConstants.CHECKBOX_GRID_COLUMNS,
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
            case FilterConstants.GROUP_TITLE -> groupCheckboxes = checkboxes;
            case FilterConstants.LOCATION_TITLE -> locationCheckboxes = checkboxes;
            case FilterConstants.DIET_TITLE -> dietCheckboxes = checkboxes;
        }
        return panel;
    }

    private JPanel createRangeSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(FilterConstants.LIFESPAN_TITLE));

        minLifespanSlider = new JSlider(
                FilterConstants.MIN_LIFESPAN,
                FilterConstants.MAX_LIFESPAN,
                FilterConstants.DEFAULT_MIN_LIFESPAN
        );
        maxLifespanSlider = new JSlider(
                FilterConstants.MIN_LIFESPAN,
                FilterConstants.MAX_LIFESPAN,
                FilterConstants.DEFAULT_MAX_LIFESPAN
        );
        configureSlider(minLifespanSlider);
        configureSlider(maxLifespanSlider);

        lblLifespanRange = new JLabel(
                String.format(
                        FilterConstants.RANGE_TEXT_FORMAT,
                        FilterConstants.DEFAULT_MIN_LIFESPAN,
                        FilterConstants.DEFAULT_MAX_LIFESPAN
                ),
                FilterConstants.RANGE_LABEL_ALIGNMENT
        );
        lblLifespanRange.setFont(new Font(
                FilterConstants.FONT_NAME,
                FilterConstants.RANGE_LABEL_FONT_STYLE,
                FilterConstants.RANGE_LABEL_FONT_SIZE
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
                FilterConstants.SLIDERS_GRID_ROWS,
                FilterConstants.SLIDERS_GRID_COLUMNS,
                FilterConstants.SLIDERS_GRID_HGAP,
                FilterConstants.SLIDERS_GRID_VGAP
        ));
        slidersPanel.add(new JLabel(FilterConstants.MIN_LABEL));
        slidersPanel.add(minLifespanSlider);
        slidersPanel.add(new JLabel(FilterConstants.MAX_LABEL));
        slidersPanel.add(maxLifespanSlider);

        panel.add(slidersPanel, BorderLayout.CENTER);
        panel.add(lblLifespanRange, BorderLayout.SOUTH);
        return panel;
    }

    private void configureSlider(JSlider slider){
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(FilterConstants.SLIDER_MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(FilterConstants.SLIDER_MINOR_TICK_SPACING);
    }

    private void updateRangeDisplay() {
        lblLifespanRange.setText(
                String.format(
                        FilterConstants.RANGE_TEXT_FORMAT,
                        minLifespanSlider.getValue(),
                        maxLifespanSlider.getValue()
                )
        );
    }

    private void updateTagLabel() {
        tagsTextArea.setText(
                selectedTags.isEmpty() ?
                        FilterConstants.NO_FILTERS_TEXT :
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
                FilterConstants.CARD_RESULTS
        );
        updateButtonPanel(btnReset, filterViewModel.hasMore() ? btnLoadMore : null, btnClose);
        setTitle(FilterConstants.RESULTS_WINDOW_TITLE);
        setPreferredSize(new Dimension(
                FilterConstants.RESULTS_WINDOW_WIDTH,
                FilterConstants.RESULTS_WINDOW_HEIGHT
        ));
        pack();
    }

    private void showFiltersView() {
        ((CardLayout) mainContentPanel.getLayout()).show(
                mainContentPanel,
                FilterConstants.CARD_FILTERS
        );
        updateButtonPanel(btnReset, btnApply, btnClose);
        setTitle(FilterConstants.FILTER_WINDOW_TITLE);
        setPreferredSize(new Dimension(
                FilterConstants.FILTER_WINDOW_WIDTH,
                FilterConstants.FILTER_WINDOW_HEIGHT
        ));
        pack();
    }

    private void resetFilters() {
        selectedTags.clear();
        setSelectedAll(groupCheckboxes,false);
        setSelectedAll(locationCheckboxes,false);
        setSelectedAll(dietCheckboxes,false);
        minLifespanSlider.setValue(FilterConstants.DEFAULT_MIN_LIFESPAN);
        maxLifespanSlider.setValue(FilterConstants.DEFAULT_MAX_LIFESPAN);
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

        List<Animal> animals = filterViewModel.getAnimals();
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

        List<Animal> newAnimals = filterViewModel.getAnimals();
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

        for(Animal animal : newAnimals) model.addElement(animal.getName());

        updateClickListener(resultsList, allLoadedAnimals);
        btnLoadMore.setEnabled(filterViewModel.hasMore());

        System.out.println("Total animals now: " + model.getSize());
        config.updateALL(this);
    }

    private void showNoResultsMessage() {
        JPanel noResultsPanel = new JPanel(new BorderLayout());
        JLabel noResultsLabel = new JLabel(FilterConstants.NO_RESULTS_MESSAGE);
        noResultsLabel.setForeground(FilterConstants.NO_RESULTS_COLOR);
        noResultsLabel.setHorizontalAlignment(FilterConstants.NO_RESULTS_ALIGNMENT);
        noResultsLabel.setFont(new Font(
                FilterConstants.FONT_NAME,
                FilterConstants.HEADING_FONT_STYLE,
                FilterConstants.RESULTS_FONT_SIZE
        ));
        noResultsPanel.add(noResultsLabel, BorderLayout.CENTER);
        noResultsPanel.setBorder(BorderFactory.createEmptyBorder(
                FilterConstants.EMPTY_BORDER_TOP,
                FilterConstants.EMPTY_BORDER_LEFT,
                FilterConstants.EMPTY_BORDER_BOTTOM,
                FilterConstants.EMPTY_BORDER_RIGHT
        ));
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(noResultsPanel, BorderLayout.CENTER);
    }

    private void showResultsList(List<Animal> animals) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Animal a : animals) model.addElement(a.getName());

        JList<String> resultsList = new JList<>(model);
        resultsList.setSelectionMode(FilterConstants.LIST_SELECTION_MODE);
        resultsList.setFont(new Font(
                FilterConstants.FONT_NAME,
                FilterConstants.RESULTS_FONT_STYLE,
                FilterConstants.RESULTS_FONT_SIZE
        ));
        resultsList.clearSelection();

        updateClickListener(resultsList, allLoadedAnimals);

        JScrollPane listScrollPane = new JScrollPane(resultsList);
        listScrollPane.setPreferredSize(FilterConstants.LIST_SCROLL_PREFERRED_SIZE);

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

    private void updateClickListener(JList<String> resultsList, List<Animal> allAnimals) {
        for(java.awt.event.MouseListener listener : resultsList.getMouseListeners())
            resultsList.removeMouseListener(listener);

        final List<Animal> currentAnimals = new ArrayList<>(allAnimals);
        resultsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedIndex = resultsList.locationToIndex(evt.getPoint());
                    if (selectedIndex >= 0 && selectedIndex < currentAnimals.size()) {
                        resultsList.clearSelection();
                        Animal selectedAnimal = currentAnimals.get(selectedIndex);
                        new SuccesfulSearch(selectedAnimal).setVisible(true);
                    }
                }
            }
        });
    }
}