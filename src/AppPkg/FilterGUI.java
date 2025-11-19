package AppPkg;

import Classes.Filter.Controller;
import Classes.Filter.ViewModel;
import Classes.Animal;
import Classes.Settings.ReaderEditor;
import Classes.Settings.StyleUpdater;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FilterGUI: Handles filters, sliders, tags, and results.
 * OR within a category, AND across categories ₍^ >⩊< ^₎Ⳋ
 */
public class FilterGUI extends JDialog {

    private JLabel lblLifespanRange;
    private JPanel pnlFilters;
    private JTextArea tagsTextArea;

    private JCheckBox[] groupCheckboxes;
    private JCheckBox[] locationCheckboxes;
    private JCheckBox[] dietCheckboxes;

    private JSlider minLifespanSlider;
    private JSlider maxLifespanSlider;

    private JButton btnLoadMore;

    private final ReaderEditor config = new ReaderEditor("settings.csv");
    private final StyleUpdater styleUpdater = new StyleUpdater(config);

    private List<String> selectedTags;
    private final Controller controller;
    private final ViewModel viewModel;

    private JPanel resultsPanel;
    private JScrollPane resultsScroll;

    public FilterGUI(JFrame parent, Controller controller, ViewModel viewModel) {
        super(parent, "Refined Search", true);
        this.controller = controller;
        this.viewModel = viewModel;

        selectedTags = new ArrayList<>();
        initComponents();
        setLocationRelativeTo(parent);
        styleUpdater.updateALL(this);
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

        JPanel topPanel = new JPanel(new BorderLayout(3,3));
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

        // Results panel
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsScroll = new JScrollPane(resultsPanel);
        resultsScroll.setPreferredSize(new Dimension(480, 200));

        btnLoadMore = new JButton("Load More");
        btnLoadMore.addActionListener(e -> loadMoreResults());
        btnLoadMore.setEnabled(false); // initially disabled

        JPanel resultsContainer = new JPanel(new BorderLayout());
        resultsContainer.add(resultsScroll, BorderLayout.CENTER);
        resultsContainer.add(btnLoadMore, BorderLayout.SOUTH);

        // Buttons panel
        JButton btnApply = new JButton("Apply");
        JButton btnReset = new JButton("Reset");
        JButton btnClose = new JButton("Close");

        btnApply.addActionListener(e -> applyFilters());
        btnReset.addActionListener(e -> resetFilters());
        btnClose.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(btnReset);
        buttonPanel.add(btnApply);
        buttonPanel.add(btnClose);

        // Add panels to dialog
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(pnlFilters), BorderLayout.CENTER);
        add(resultsContainer, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.PAGE_END);

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
    }

    private void applyFilters(){
        int min = minLifespanSlider.getValue();
        int max = maxLifespanSlider.getValue();

        List<String> groups = getSelectedGroups();
        List<String> locations = getSelectedLocations();
        List<String> diets = getSelectedDiets();

        controller.filterAnimals(groups, locations, diets, min, max, null); // first page
        updateResultsFromViewModel();
    }

    private void loadMoreResults(){
        controller.filterAnimals(getSelectedGroups(), getSelectedLocations(), getSelectedDiets(),
                minLifespanSlider.getValue(), maxLifespanSlider.getValue(), viewModel.getNextCursor());
        updateResultsFromViewModel();
    }

    private void updateResultsFromViewModel(){
        resultsPanel.removeAll();
        for(Animal a: viewModel.getAnimals()){
            resultsPanel.add(new JLabel(a.getName()));
        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
        btnLoadMore.setEnabled(viewModel.hasMore());
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame parent = new JFrame();
            parent.setSize(400,300);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setVisible(true);



           // new FilterGUI(parent, controller, vm).setVisible(true);
        });
    }
}
