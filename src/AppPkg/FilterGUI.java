/**
 * FilterGUI : Handles filters, sliders, tags, and results.
 *  REMEMBER: OR within a category, AND across categories ₍^ >⩊< ^₎Ⳋ
  /)/)
 ( . .)
 ( づ♡
 */

package AppPkg;

import Classes.Filter.FilterController;
import Classes.Filter.ViewModel;
import Classes.Animal;
import Classes.Settings.ReaderEditor;
import Classes.Settings.StyleUpdater;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    private final ReaderEditor config = new ReaderEditor("settings.csv");
    private final StyleUpdater styleUpdater = new StyleUpdater(config);

    private List<String> selectedTags;
    private final FilterController filterController;
    private final ViewModel viewModel;

    private JPanel resultsPanel;
    private JScrollPane resultsScroll;
    private JPanel topPanel;
    private JScrollPane filtersScroll;
    private JPanel resultsContainer;
    private JPanel buttonPanel;
    private JPanel mainContentPanel; // Added to manage view switching

    public FilterGUI(JFrame parent, FilterController filterController, ViewModel viewModel) {
        super();
        this.filterController = filterController;
        this.viewModel = viewModel;

        selectedTags = new ArrayList<>();
        initComponents();
        setupListeners();
        setLocationRelativeTo(parent);
        styleUpdater.updateAll(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        setPreferredSize(new Dimension(500, 600));

        // Top panel: filters + tags
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

        // Filters panel
        pnlFilters = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);

        String[] groups = {"Mammal", "Bird", "Reptile", "Amphibian", "Fish", "Insect"};
        String[] locations = {"Africa", "Asia", "Europe", "North America", "South America", "Australia", "Antarctica"};
        String[] diets = {"Herbivore", "Carnivore", "Omnivore", "Insectivore"};

        gbc.gridy = 0; pnlFilters.add(createCheckboxPanel("Group", groups), gbc);
        gbc.gridy = 1; pnlFilters.add(createCheckboxPanel("Location", locations), gbc);
        gbc.gridy = 2; pnlFilters.add(createCheckboxPanel("Diet", diets), gbc);
        gbc.gridy = 3; pnlFilters.add(createRangeSliderPanel(), gbc);

        filtersScroll = new JScrollPane(pnlFilters);

        // Results panel
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

        // Main content panel to switch between views
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.add(filtersScroll, "FILTERS");
        mainContentPanel.add(resultsContainer, "RESULTS");

        // Buttons panel
        btnApply = new JButton("Apply");
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(btnReset);
        buttonPanel.add(btnApply);
        buttonPanel.add(btnClose);

        // Add all panels
        add(topPanel, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        // Initially I want to show the filters view
        showFiltersView();

        pack();
    }

    private void setupListeners() {
        // Apply button listener
        btnApply.addActionListener(e -> {
            applyFilters();
            // Now we want to switch to results view
            showResultsView();
        });

        // Load More button listener
        btnLoadMore.addActionListener(e -> {
            loadMoreResults();
        });

        // Reset button listener
        btnReset.addActionListener(e -> {
            resetFilters();
            // Now we want to show filters view
            showFiltersView();
        });

        // Close button listener
        btnClose.addActionListener(e -> {
            dispose();
        });
    }

    private void showResultsView() {
        // Switch to results view using CardLayout
        CardLayout cl = (CardLayout) mainContentPanel.getLayout();
        cl.show(mainContentPanel, "RESULTS");

        // Update buttons - remove Apply button (makes no sense), show Load More if needed
        buttonPanel.removeAll();
        buttonPanel.add(btnReset);
        if (viewModel.hasMore()) {
            buttonPanel.add(btnLoadMore);
        }
        buttonPanel.add(btnClose);
        buttonPanel.revalidate();
        buttonPanel.repaint();

        // Update window title to indicate results view
        setTitle("Filter Results");

        // Resize dialog to fit results better
        setPreferredSize(new Dimension(300, 400));
        pack();
    }

    private void showFiltersView() {
        // Switch to filters view using CardLayout
        CardLayout cl = (CardLayout) mainContentPanel.getLayout();
        cl.show(mainContentPanel, "FILTERS");

        // Update buttons - show Apply again
        buttonPanel.removeAll();
        buttonPanel.add(btnReset);
        buttonPanel.add(btnApply);
        buttonPanel.add(btnClose);
        buttonPanel.revalidate();
        buttonPanel.repaint();

        // Reset window title
        setTitle("Filter Animals");

        // Reset dialog size
        setPreferredSize(new Dimension(500, 600));
        pack();
    }

    private JPanel createCheckboxPanel(String title, String[] options) {
        JPanel panel = new JPanel(new GridLayout(0,3,1,1));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JCheckBox[] checkboxes = new JCheckBox[options.length];
        for (int i=0;i<options.length;i++) {
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

        configureSlider(minLifespanSlider);
        configureSlider(maxLifespanSlider);

        lblLifespanRange = new JLabel("Range: 0 - 100 years", JLabel.CENTER);
        lblLifespanRange.setFont(new Font("Tahoma", Font.BOLD, 9));

        minLifespanSlider.addChangeListener(e -> {
            int min = minLifespanSlider.getValue();
            int max = maxLifespanSlider.getValue();
            if(min>max) minLifespanSlider.setValue(max);
            updateRangeDisplay();
        });
        maxLifespanSlider.addChangeListener(e -> {
            int min = minLifespanSlider.getValue();
            int max = maxLifespanSlider.getValue();
            if(max<min) maxLifespanSlider.setValue(min);
            updateRangeDisplay();
        });

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

    private void updateRangeDisplay(){
        lblLifespanRange.setText("Range: " + minLifespanSlider.getValue() + " - " + maxLifespanSlider.getValue() + " years");
    }

    private void updateTagLabel(){
        if(selectedTags.isEmpty()) tagsTextArea.setText("No filters selected");
        else tagsTextArea.setText(String.join(", ", selectedTags));
    }

    private List<String> getSelectedGroups(){
        List<String> selected = new ArrayList<>();
        for(JCheckBox cb: groupCheckboxes) if(cb.isSelected()) selected.add(cb.getText());
        return selected;
    }

    private List<String> getSelectedLocations(){
        List<String> selected = new ArrayList<>();
        for(JCheckBox cb: locationCheckboxes) if(cb.isSelected()) selected.add(cb.getText());
        return selected;
    }

    private List<String> getSelectedDiets(){
        List<String> selected = new ArrayList<>();
        for(JCheckBox cb: dietCheckboxes) if(cb.isSelected()) selected.add(cb.getText());
        return selected;
    }

    private void resetFilters(){
        selectedTags.clear();
        for(JCheckBox cb: groupCheckboxes) cb.setSelected(false);
        for(JCheckBox cb: locationCheckboxes) cb.setSelected(false);
        for(JCheckBox cb: dietCheckboxes) cb.setSelected(false);
        minLifespanSlider.setValue(0);
        maxLifespanSlider.setValue(100);
        updateTagLabel();
        updateRangeDisplay();

        // Clear resultss
        resultsPanel.removeAll();
        resultsPanel.revalidate();
        resultsPanel.repaint();
        btnLoadMore.setEnabled(false);
    }

    private void applyFilters(){
        int min = minLifespanSlider.getValue();
        int max = maxLifespanSlider.getValue();

        List<String> groups = getSelectedGroups();
        List<String> locations = getSelectedLocations();
        List<String> diets = getSelectedDiets();

        System.out.println("Filters - Groups: " + groups + ", Locations: " + locations + ", Diets: " + diets + ", Lifespan: " + min + "-" + max);

        // Call controller to filter animals
        filterController.filterAnimals(groups, locations, diets, min, max, null); // first page
        updateResultsFromViewModel();

        System.out.println("Controller called, animals in viewModel: " + viewModel.getAnimals().size());
    }

    private void loadMoreResults(){
        // Call controller to load more results using the next cursor
        filterController.filterAnimals(getSelectedGroups(), getSelectedLocations(), getSelectedDiets(),
                minLifespanSlider.getValue(), maxLifespanSlider.getValue(), viewModel.getNextCursor());
        System.out.println("ViewModel animals after filter before update call: " + viewModel.getAnimals().size());
        if (this.viewModel.getAnimals() == null) {
            this.viewModel.setAnimals(new ArrayList<>());
        }
        updateResultsFromViewModel();
        System.out.println("ViewModel animals after filter after call: " + viewModel.getAnimals().size());
    }

    private void updateResultsFromViewModel() {
        System.out.println("Updating results from view model");

        // Clear existing results
        resultsPanel.removeAll();

        List<Animal> animals = viewModel.getAnimals();
        System.out.println("Number of animals to display: " + animals.size());

        if (animals.isEmpty()) {
            // Show a message when no results are found
            System.out.println("No animals found matching your filters");
            JPanel noResultsPanel = new JPanel(new BorderLayout());
            JLabel noResultsLabel = new JLabel("No animals found matching your filters. Try different criteria.");
            noResultsLabel.setForeground(Color.RED);
            noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noResultsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
            noResultsPanel.add(noResultsLabel, BorderLayout.CENTER);

            noResultsPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
            resultsPanel.add(noResultsPanel);
        } else {
            //Now add results from view model
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Animal animal : animals) {
                model.addElement(animal.getName());
            }

            JList<String> resultsList = new JList<>(model);
            resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            resultsList.setFont(new Font("Tahoma", Font.PLAIN, 12));

           // Add double-click listener - do we want to switch to single click?
            resultsList.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int selectedIndex = resultsList.locationToIndex(evt.getPoint());
                        if (selectedIndex >= 0) {
                            Animal selectedAnimal = animals.get(selectedIndex);
                            new SuccesfulSearch(selectedAnimal).setVisible(true);

                        }
                    }
                }
            });

          // Wrap it all in scroll pane
            JScrollPane listScrollPane = new JScrollPane(resultsList);
            listScrollPane.setPreferredSize(new Dimension(400, 300));

           // Clear existing results and add the list
            resultsPanel.removeAll();
            resultsPanel.setLayout(new BorderLayout());
            resultsPanel.add(listScrollPane, BorderLayout.CENTER);


            resultsPanel.revalidate();
            resultsPanel.repaint();
            btnLoadMore.setEnabled(viewModel.hasMore());

            SwingUtilities.invokeLater(() -> {
                resultsScroll.getVerticalScrollBar().setValue(0);
            });
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame parent = new JFrame();
            parent.setSize(400,300);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setVisible(true);
        });
    }
}