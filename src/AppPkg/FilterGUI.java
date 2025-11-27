/**
 * FilterGUI : Handles filters, sliders, tags, and results.
 *  REMEMBER: OR within a category, AND across categories
 */

package AppPkg;

import Classes.Filter.FilterController;
import Classes.Filter.FilterViewModel;
import Classes.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        setLayout(new BorderLayout(8, 8));
        setPreferredSize(new Dimension(500, 600));

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
        JLabel lblHeading = new JLabel("Filter By...", SwingConstants.LEFT);
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 15));

        tagsTextArea = new JTextArea(1, 5);
        tagsTextArea.setEditable(false);
        tagsTextArea.setLineWrap(true);
        tagsTextArea.setWrapStyleWord(true);

        JScrollPane tagScroll = new JScrollPane(tagsTextArea);
        tagScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tagScroll.setPreferredSize(new Dimension(6, 45));

        topPanel = new JPanel(new BorderLayout(3,3));
        topPanel.add(lblHeading, BorderLayout.NORTH);
        topPanel.add(tagScroll, BorderLayout.SOUTH);
    }

    private void createFiltersPanel() {
        pnlFilters = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);

        gbc.gridy = 0; pnlFilters.add(createCheckboxPanel("Group", new String[]{"Mammal","Bird","Reptile","Amphibian","Fish","Insect"}), gbc);
        gbc.gridy = 1; pnlFilters.add(createCheckboxPanel("Location", new String[]{"Africa","Asia","Europe","North America","South America","Australia","Antarctica"}), gbc);
        gbc.gridy = 2; pnlFilters.add(createCheckboxPanel("Diet", new String[]{"Herbivore","Carnivore","Omnivore","Insectivore"}), gbc);
        gbc.gridy = 3; pnlFilters.add(createRangeSliderPanel(), gbc);

        filtersScroll = new JScrollPane(pnlFilters);
    }

    private void createResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScroll = new JScrollPane(resultsPanel);
        resultsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resultsScroll.setPreferredSize(new Dimension(300, 200));

        btnLoadMore = new JButton("Load More");
        btnLoadMore.setEnabled(false);

        resultsContainer = new JPanel(new BorderLayout());
        resultsContainer.add(resultsScroll, BorderLayout.CENTER);
        resultsContainer.add(btnLoadMore, BorderLayout.SOUTH);
    }

    private void createMainContentPanel() {
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.add(filtersScroll, "FILTERS");
        mainContentPanel.add(resultsContainer, "RESULTS");
    }

    private void createButtonsPanel() {
        btnApply = new JButton("Apply");
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
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
        JPanel panel = new JPanel(new GridLayout(0,3,1,1));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JCheckBox[] checkboxes = new JCheckBox[options.length];
        for (int i=0; i<options.length; i++) {
            String option = options[i];
            JCheckBox cb = new JCheckBox(option);
            cb.addItemListener(e -> { if(cb.isSelected()) selectedTags.add(option); else selectedTags.remove(option); updateTagLabel(); });
            checkboxes[i] = cb;
            panel.add(cb);
        }

        switch(title){
            case "Group" -> groupCheckboxes = checkboxes;
            case "Location" -> locationCheckboxes = checkboxes;
            case "Diet" -> dietCheckboxes = checkboxes;
        }
        return panel;
    }

    private JPanel createRangeSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lifespan Range (years)"));

        minLifespanSlider = new JSlider(0,100,0);
        maxLifespanSlider = new JSlider(0,100,100);
        configureSlider(minLifespanSlider); configureSlider(maxLifespanSlider);

        lblLifespanRange = new JLabel("Range: 0 - 100 years", JLabel.CENTER);
        lblLifespanRange.setFont(new Font("Tahoma", Font.BOLD, 9));

        minLifespanSlider.addChangeListener(e -> { if(minLifespanSlider.getValue()>maxLifespanSlider.getValue()) minLifespanSlider.setValue(maxLifespanSlider.getValue()); updateRangeDisplay(); });
        maxLifespanSlider.addChangeListener(e -> { if(maxLifespanSlider.getValue()<minLifespanSlider.getValue()) maxLifespanSlider.setValue(minLifespanSlider.getValue()); updateRangeDisplay(); });

        JPanel slidersPanel = new JPanel(new GridLayout(2,2,3,3));
        slidersPanel.add(new JLabel("Minimum:")); slidersPanel.add(minLifespanSlider);
        slidersPanel.add(new JLabel("Maximum:")); slidersPanel.add(maxLifespanSlider);

        panel.add(slidersPanel, BorderLayout.CENTER);
        panel.add(lblLifespanRange, BorderLayout.SOUTH);
        return panel;
    }

    private void configureSlider(JSlider slider){
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(15);
        slider.setMinorTickSpacing(5);
    }

    private void updateRangeDisplay() { lblLifespanRange.setText("Range: " + minLifespanSlider.getValue() + " - " + maxLifespanSlider.getValue() + " years"); }

    private void updateTagLabel() { tagsTextArea.setText(selectedTags.isEmpty() ? "No filters selected" : String.join(", ", selectedTags)); }

    private List<String> getSelectedGroups() { return getSelectedFromArray(groupCheckboxes); }
    private List<String> getSelectedLocations() { return getSelectedFromArray(locationCheckboxes); }
    private List<String> getSelectedDiets() { return getSelectedFromArray(dietCheckboxes); }
    private List<String> getSelectedFromArray(JCheckBox[] arr) { List<String> selected = new ArrayList<>(); for(JCheckBox cb: arr) if(cb.isSelected()) selected.add(cb.getText()); return selected; }

    private void showResultsView() {
        ((CardLayout) mainContentPanel.getLayout()).show(mainContentPanel, "RESULTS");
        updateButtonPanel(btnReset, filterViewModel.hasMore() ? btnLoadMore : null, btnClose);
        setTitle("Filter Results");
        setPreferredSize(new Dimension(300, 400));
        pack();
    }

    private void showFiltersView() {
        ((CardLayout) mainContentPanel.getLayout()).show(mainContentPanel, "FILTERS");
        updateButtonPanel(btnReset, btnApply, btnClose);
        setTitle("Filter Animals");
        setPreferredSize(new Dimension(500, 600));
        pack();
    }

    private void resetFilters() {
        selectedTags.clear(); setSelectedAll(groupCheckboxes,false); setSelectedAll(locationCheckboxes,false); setSelectedAll(dietCheckboxes,false);
        minLifespanSlider.setValue(0); maxLifespanSlider.setValue(100); updateTagLabel(); updateRangeDisplay();

        resultsPanel.removeAll(); resultsPanel.revalidate(); allLoadedAnimals.clear(); resultsPanel.repaint();
        btnLoadMore.setEnabled(false);
    }

    private void setSelectedAll(JCheckBox[] arr, boolean selected){ for(JCheckBox cb: arr) cb.setSelected(selected); }

    // =========================================================
    // RESULTS UPDATE METHODS
    // =========================================================

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
        JLabel noResultsLabel = new JLabel("No animals found matching your filters. Try different criteria.");
        noResultsLabel.setForeground(Color.RED);
        noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noResultsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        noResultsPanel.add(noResultsLabel, BorderLayout.CENTER);
        noResultsPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(noResultsPanel, BorderLayout.CENTER);
    }

    private void showResultsList(List<Animal> animals) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Animal a : animals) model.addElement(a.getName());

        JList<String> resultsList = new JList<>(model);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        resultsList.clearSelection();

        updateClickListener(resultsList, allLoadedAnimals);

        JScrollPane listScrollPane = new JScrollPane(resultsList);
        listScrollPane.setPreferredSize(new Dimension(400,300));

        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.add(listScrollPane, BorderLayout.CENTER);

        btnLoadMore.setEnabled(filterViewModel.hasMore());
        SwingUtilities.invokeLater(() -> resultsScroll.getVerticalScrollBar().setValue(0));
    }

    // =========================================================
    // UNTOUCHED METHODS
    // =========================================================

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

    private void loadMoreResults(){
        filterController.filterAnimals(getSelectedGroups(), getSelectedLocations(), getSelectedDiets(),
                minLifespanSlider.getValue(), maxLifespanSlider.getValue(), filterViewModel.getNextCursor());
        System.out.println("FilterViewModel animals after filter before update call: " + filterViewModel.getAnimals().size());
        if (filterViewModel.getAnimals() == null) filterViewModel.setAnimals(new ArrayList<>());
        appendResultsFromViewModel();
        System.out.println("FilterViewModel animals after filter after call: " + filterViewModel.getAnimals().size());
    }

    private void updateClickListener(JList<String> resultsList, List<Animal> allAnimals) {
        for(java.awt.event.MouseListener listener : resultsList.getMouseListeners()) resultsList.removeMouseListener(listener);

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

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame parent = new JFrame();
            parent.setSize(500,300);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setVisible(true);
        });
    }
}
