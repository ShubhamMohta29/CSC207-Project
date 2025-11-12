/**
 * FilterGUI: contains logic for the category selectors and range sliders
 *  also remember you OR within a category and AND across them
 *  (any group) AND (any location) AND (.....) ₍^ >⩊< ^₎Ⳋ
 /)/)
 ( . .)
 ( づ♡
 */
package AppPkg;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List; //cuz both awt and util contain List --> want to avoid ambiguity

public class FilterGUI extends JDialog {
    //extending it as a JDialog instead of JFrame cause it felt more natural ₍^ >⩊< ^₎Ⳋ

    private JLabel lblLifespanRange;
    private JPanel pnlFilters; //the panel for displaying all filter options
    private JTextArea tagsTextArea; //want to display all the filters applied as "tags"

    private JCheckBox[] groupCheckboxes;
    private JCheckBox[] locationCheckboxes;
    private JCheckBox[] dietCheckboxes;

    //slider for filtering based on life span
    private JSlider minLifespanSlider;
    private JSlider maxLifespanSlider;

    //slider for filtering based on weight?
    private JSlider minWeightSlider;
    private JSlider maxLWeightSlider;


    private List<String> selectedTags;

    public FilterGUI(JFrame parent) {
        super(parent, "Refined Search", true); //modal == true ensures the user cannot interact with the
        // main frame until the filter dialogue is open
        selectedTags = new ArrayList<>();
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8)); //specifies padding between components in the FilterDialog
        setPreferredSize(new Dimension(420, 400)); //specifies the ideal size of the FilterFrame

        // Top Panel: Heading + Selected "Tags"
        JLabel lblHeading = new JLabel("Filter By...", SwingConstants.LEFT); //left align
        lblHeading.setFont(new Font("Tahoma", Font.BOLD, 15));

        tagsTextArea = new JTextArea(1, 5);
        tagsTextArea.setEditable(false); //read only! the user cannot alter the tags here YET -->
        // maybe later if I can implement something that allows to toggle the tags themselves but for now it's just
        // toggling the checkboxes to "edit" the tags section
        tagsTextArea.setLineWrap(true); //wraps onto next line --> don't want to extend horizontally (⸝⸝⸝>﹏<⸝⸝⸝)
        tagsTextArea.setWrapStyleWord(true); //don't chop the words if they don't fit on the line (⸝⸝⸝>﹏<⸝⸝⸝)
        //tagsTextArea.setBackground(getBackground());

        // Scrollable tags area
        JScrollPane scrollPane = new JScrollPane(tagsTextArea); //too many filter tags but now you can scroll and view
        // them all:)
        scrollPane.setPreferredSize(new Dimension(6, 45)); //dimensions of the panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //scroll vertically

        //panel to contain the above elements
        JPanel topPanel = new JPanel(new BorderLayout(3, 3));
        topPanel.add(lblHeading, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.SOUTH);


        // Filters Panel
        pnlFilters = new JPanel(new GridBagLayout()); //want it to look like a grid layout so everything is
        // aligned ⦮ ⦯
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        //gbc.weightx = 1.0;

        String[] groups = {"Mammal", "Bird", "Reptile", "Amphibian", "Fish", "Insect"};
        String[] locations = {"Africa", "Asia", "Europe", "North America", "South America", "Australia", "Antarctica"};
        String[] diets = {"Herbivore", "Carnivore", "Omnivore", "Insectivore"};

        gbc.gridy = 0;
        pnlFilters.add(createCheckboxPanel("Group", groups), gbc);
        gbc.gridy = 1;
        pnlFilters.add(createCheckboxPanel("Location", locations), gbc);
        gbc.gridy = 2;
        pnlFilters.add(createCheckboxPanel("Diet", diets), gbc);
        gbc.gridy = 3;
        pnlFilters.add(createRangeSliderPanel(), gbc);


        // Buttons Panel
        JButton btnApply = new JButton("Apply");
        JButton btnReset = new JButton("Reset");
        //basic action buttons
        JButton btnReturn = new JButton("Close");

        btnApply.addActionListener(e -> applyFilters());
        btnReset.addActionListener(e -> resetFilters());
        btnReturn.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(btnReset);
        buttonPanel.add(btnApply);
        buttonPanel.add(btnReturn);

        // Add to Dialog
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(pnlFilters), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    private JPanel createCheckboxPanel(String title, String[] options) {
        JPanel panel = new JPanel(new GridLayout(0, 3, 1, 1));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JCheckBox[] checkboxes = new JCheckBox[options.length];
        for (String option : options) {
            JCheckBox cb = new JCheckBox(option);
            cb.addItemListener(e -> {
                if (cb.isSelected()) selectedTags.add(option);
                else selectedTags.remove(option);
                updateTagLabel();
            });
            panel.add(cb);

            // save the array to the right field
            switch (title) {
                case "Group" -> groupCheckboxes = checkboxes;
                case "Location" -> locationCheckboxes = checkboxes;
                case "Diet" -> dietCheckboxes = checkboxes;
            }
        }
        return panel;
    }

    private JPanel createRangeSliderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lifespan Range (years)"));

        minLifespanSlider = new JSlider(0, 100, 0);
        maxLifespanSlider = new JSlider(0, 100, 100);

        //slider for filtering based on speed?
        JSlider minSpeedSlider = new JSlider(0, 100, 0);
        JSlider maxSpeedSlider = new JSlider(0, 100, 100);

        configureSlider(minLifespanSlider);
        configureSlider(maxLifespanSlider);

        configureSlider(minSpeedSlider);
        configureSlider(maxSpeedSlider);


        lblLifespanRange = new JLabel("Range: 0 - 100 years", JLabel.CENTER);
        lblLifespanRange.setFont(new Font("Tahoma", Font.BOLD, 9));

//        lblSpeedRange = new JLabel("Range: 0 - 100 years", JLabel.CENTER);
//        lblSpeedRange.setFont(new Font("Tahoma", Font.BOLD, 9));

        minLifespanSlider.addChangeListener(e -> {
            int min = minLifespanSlider.getValue();
            int max = maxLifespanSlider.getValue();
            if (min > max) minLifespanSlider.setValue(max);
            updateRangeDisplay();
        });

        maxLifespanSlider.addChangeListener(e -> {
            int min = minLifespanSlider.getValue();
            int max = maxLifespanSlider.getValue();
            if (max < min) maxLifespanSlider.setValue(min);
            updateRangeDisplay();
        });

        JPanel slidersPanel = new JPanel(new GridLayout(2, 2, 3, 3));
        slidersPanel.add(new JLabel("Minimum:"));
        slidersPanel.add(minLifespanSlider);
        slidersPanel.add(new JLabel("Maximum:"));
        slidersPanel.add(maxLifespanSlider);

        panel.add(slidersPanel, BorderLayout.CENTER);
        panel.add(lblLifespanRange, BorderLayout.SOUTH);

        return panel;
    }

    private void configureSlider(JSlider slider) {
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(15);
        slider.setMinorTickSpacing(5);
    }

    private void updateRangeDisplay() {
        lblLifespanRange.setText("Range: " + minLifespanSlider.getValue() + " - " + maxLifespanSlider.getValue() + " years");
    }

    private void updateTagLabel() {
        if (selectedTags.isEmpty()) tagsTextArea.setText("No filters selected");
        else tagsTextArea.setText(String.join(", ", selectedTags));
    }

    //Returns a list of the currently selected groups
    public List<String> getSelectedGroups() {
        List<String> selected = new ArrayList<>();
        for (JCheckBox cb : groupCheckboxes) {
            if (cb.isSelected()) selected.add(cb.getText());
        }
        return selected;
    }


    //Returns a list of the currently selected locations
    public List<String> getSelectedLocations() {
        List<String> selected = new ArrayList<>();
        for (JCheckBox cb : locationCheckboxes) {
            if (cb.isSelected()) selected.add(cb.getText());
        }
        return selected;
    }

    // Returns a list of the currently selected diets
    public List<String> getSelectedDiets() {
        List<String> selected = new ArrayList<>();
        for (JCheckBox cb : dietCheckboxes) {
            if (cb.isSelected()) selected.add(cb.getText());
        }
        return selected;
    }


    private void resetFilters() {
        selectedTags.clear();
        Component[] components = pnlFilters.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                for (Component inner : ((JPanel) comp).getComponents()) {
                    if (inner instanceof JCheckBox) ((JCheckBox) inner).setSelected(false);
                }
            }
        }
        minLifespanSlider.setValue(0);
        maxLifespanSlider.setValue(100);
        updateTagLabel();
        updateRangeDisplay();
    }

    private void applyFilters() {
        int min = minLifespanSlider.getValue();
        int max = maxLifespanSlider.getValue();

        System.out.println("Selected Tags: " + selectedTags);
        System.out.println("Lifespan Range: " + min + " - " + max + " years");

        List<String> groups = getSelectedGroups();
        List<String> locations = getSelectedLocations();
        List<String> diets = getSelectedDiets();

        // todo call the controller here ₍^ >⩊< ^₎Ⳋ
        //FilterController control = new FilterController();
        //control.applyFilter(groups,diets,locations,min, max);
        dispose();
    }

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHomeActionPerformed
    {//GEN-HEADEREND:event_btnHomeActionPerformed
        // new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parent = new JFrame();
            parent.setSize(400, 300);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setVisible(true);

            new FilterGUI(parent).setVisible(true);
        });
    }
}
