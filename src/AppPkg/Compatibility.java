package AppPkg;

import Classes.Compatibility.data.AnimalDataAccess;
import Classes.Compatibility.usecases.AnimalDataAccessInterface;
import Classes.Compatibility.usecases.CompareAnimalsInputBoundary;
import Classes.Compatibility.usecases.CompareAnimalsInteractor;
import Classes.Compatibility.usecases.CompareAnimalsOutputBoundary;
import Classes.Settings.TextSettingInteractor;
import Classes.Settings.TextSettingOutput;

/**
 * Compatibility window for comparing two animals.
 * Implements the UI for the animal compatibility feature.
 */
public final class Compatibility extends javax.swing.JFrame {
    private static final String SEARCHED_ANIMAL_2 = "Searched Animal 2";
    private static final int FONT_SIZE = 18;
    private static final int TEXT_AREA_COLUMNS = 20;
    private static final int TEXT_AREA_ROWS = 5;
    private static final int TEXT_FIELD_WIDTH = 80;
    private static final int SCROLL_PANE_WIDTH = 293;
    private static final int LEFT_MARGIN = 140;
    private static final int CENTER_GAP = 40;
    private static final int RIGHT_MARGIN = 140;
    private static final int BUTTON_MARGIN = 20;
    private static final int GAP_SMALL = 10;
    private static final int GAP_MEDIUM = 18;
    private static final int GAP_LARGE = 25;
    private static final int SCROLL_HEIGHT = 70;
    private static final int BOTTOM_GAP = 23;

    private final TextSettingInteractor config =
            new TextSettingInteractor("settings.csv");
    private final TextSettingOutput textSettingOutput =
            new TextSettingOutput(config);
    private final CompareAnimalsInputBoundary interactor;

    private javax.swing.JButton btnCompare;
    private javax.swing.JButton btnReturn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnimal1;
    private javax.swing.JLabel lblAnimal2;
    private javax.swing.JLabel lblCategories;
    private javax.swing.JLabel lblCategories1;
    private javax.swing.JLabel lblConflicting;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblMatching;
    private javax.swing.JTextArea txaConflicting;
    private javax.swing.JTextArea txaMatching;
    private javax.swing.JTextField txfAnimal1;
    private javax.swing.JTextField txfAnimal2;
    private javax.swing.JLabel lblSearchedAnimal1;
    private javax.swing.JLabel lblSearchedAnimal2;
    private javax.swing.JLabel lblRating;

    /**
     * Constructs the Compatibility window.
     * Initializes the interactor and UI components.
     */
    public Compatibility() {
        final AnimalDataAccessInterface dataAccess =
                new AnimalDataAccess();
        final CompareAnimalsOutputBoundary presenter =
                new CompareAnimalsPresenter();
        this.interactor = new CompareAnimalsInteractor(
                dataAccess, presenter);

        initComponents();
        updateLabelStyle();
    }

    /**
     * Initializes the UI components.
     * Sets up buttons, labels, text fields, and layout.
     */
    private void initComponents() {
        initializeComponents();
        configureWindow();
        configureButtons();
        configureLabels();
        configureTextAreas();
        setupLayout();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes all component instances.
     */
    private void initializeComponents() {
        btnReturn = new javax.swing.JButton();
        lblHeading = new javax.swing.JLabel();
        lblAnimal1 = new javax.swing.JLabel();
        lblAnimal2 = new javax.swing.JLabel();
        txfAnimal1 = new javax.swing.JTextField();
        txfAnimal2 = new javax.swing.JTextField();
        btnCompare = new javax.swing.JButton();
        lblMatching = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaMatching = new javax.swing.JTextArea();
        lblCategories1 = new javax.swing.JLabel();
        lblConflicting = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaConflicting = new javax.swing.JTextArea();
        lblCategories = new javax.swing.JLabel();
        lblSearchedAnimal1 = new javax.swing.JLabel();
        lblSearchedAnimal2 = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
    }

    /**
     * Configures window properties.
     */
    private void configureWindow() {
        setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compatibility");
    }

    /**
     * Configures button properties and action listeners.
     */
    private void configureButtons() {
        btnReturn.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/imagesPkg/home.png")));
        btnReturn.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            final java.awt.event.ActionEvent evt) {
                        btnReturnActionPerformed(evt);
                    }
                });

        btnCompare.setText("Compare");
        btnCompare.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(
                            final java.awt.event.ActionEvent evt) {
                        btnCompareActionPerformed(evt);
                    }
                });
    }

    /**
     * Configures label properties.
     */
    private void configureLabels() {
        lblHeading.setFont(
                new java.awt.Font("Tahoma", 0, FONT_SIZE));
        lblHeading.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Pet Compatibility");

        lblAnimal1.setText("Animal 1");
        lblAnimal2.setText("Animal 2");
        lblMatching.setText("Matching");
        lblCategories1.setText("Categories");
        lblConflicting.setText("Conflicting");
        lblCategories.setText("Categories");
        lblSearchedAnimal1.setText("Searched Animal 1");
        lblSearchedAnimal2.setText(SEARCHED_ANIMAL_2);
        lblRating.setText("Rating");
    }

    /**
     * Configures text area properties.
     */
    private void configureTextAreas() {
        txaMatching.setColumns(TEXT_AREA_COLUMNS);
        txaMatching.setRows(TEXT_AREA_ROWS);
        jScrollPane1.setViewportView(txaMatching);

        txaConflicting.setColumns(TEXT_AREA_COLUMNS);
        txaConflicting.setRows(TEXT_AREA_ROWS);
        jScrollPane2.setViewportView(txaConflicting);
    }

    /**
     * Sets up the layout manager and component positioning.
     */
    private void setupLayout() {
        final javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        setupHorizontalLayout(layout);
        setupVerticalLayout(layout);
    }

    /**
     * Sets up horizontal layout groups.
     * @param layout the GroupLayout to configure
     */
    private void setupHorizontalLayout(
            final javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblHeading,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                createAnimalInputGroup(layout))
                                        .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                createResultsGroup(layout)))
                                .addContainerGap())
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                createCenteredButtonGroup(layout))
                        .addGroup(createAnimalLabelsGroup(layout))
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                createCenteredRatingGroup(layout))
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                createReturnButtonGroup(layout))
        );
    }

    /**
     * Creates animal input group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createAnimalInputGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblAnimal1)
                        .addComponent(lblAnimal2))
                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING,
                                false)
                        .addComponent(txfAnimal2,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                TEXT_FIELD_WIDTH, Short.MAX_VALUE)
                        .addComponent(txfAnimal1));
    }

    /**
     * Creates results group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createResultsGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblConflicting)
                        .addComponent(lblMatching)
                        .addComponent(lblCategories1)
                        .addComponent(lblCategories))
                .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING,
                                false)
                        .addComponent(jScrollPane1,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                SCROLL_PANE_WIDTH, Short.MAX_VALUE)
                        .addComponent(jScrollPane2));
    }

    /**
     * Creates centered button group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createCenteredButtonGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCompare)
                .addGap(0, 0, Short.MAX_VALUE);
    }

    /**
     * Creates animal labels group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createAnimalLabelsGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGap(LEFT_MARGIN)
                .addComponent(lblSearchedAnimal1)
                .addGap(CENTER_GAP)
                .addComponent(lblSearchedAnimal2)
                .addContainerGap(RIGHT_MARGIN, Short.MAX_VALUE);
    }

    /**
     * Creates centered rating group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createCenteredRatingGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRating)
                .addGap(0, 0, Short.MAX_VALUE);
    }

    /**
     * Creates return button group layout.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createReturnButtonGroup(
            final javax.swing.GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGap(BUTTON_MARGIN)
                .addComponent(btnReturn)
                .addContainerGap();
    }

    /**
     * Sets up vertical layout groups.
     * @param layout the GroupLayout to configure
     */
    private void setupVerticalLayout(
            final javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblHeading)
                                        .addGap(GAP_MEDIUM, GAP_MEDIUM, GAP_MEDIUM)
                                        .addGroup(createAnimal1InputRow(layout))
                                        .addPreferredGap(javax.swing.LayoutStyle
                                                .ComponentPlacement.UNRELATED)
                                        .addGroup(createAnimal2InputRow(layout))
                                        .addPreferredGap(javax.swing.LayoutStyle
                                                .ComponentPlacement.RELATED)
                                        .addComponent(btnCompare)
                                        .addGap(GAP_SMALL, GAP_SMALL, GAP_SMALL)
                                        .addGroup(createSearchedAnimalsRow(layout))
                                        .addGap(GAP_LARGE, GAP_LARGE, GAP_LARGE)
                                        .addGroup(createMatchingSection(layout))
                                        .addGap(GAP_MEDIUM, GAP_MEDIUM, GAP_MEDIUM)
                                        .addGroup(createConflictingSection(layout))
                                        .addPreferredGap(javax.swing.LayoutStyle
                                                .ComponentPlacement.RELATED)
                                        .addComponent(lblRating)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle
                                                        .ComponentPlacement.RELATED,
                                                BOTTOM_GAP, Short.MAX_VALUE)
                                        .addComponent(btnReturn)
                                        .addContainerGap())
        );
    }

    /**
     * Creates animal 1 input row.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createAnimal1InputRow(
            final javax.swing.GroupLayout layout) {
        return layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblAnimal1)
                .addComponent(txfAnimal1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE);
    }

    /**
     * Creates animal 2 input row.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createAnimal2InputRow(
            final javax.swing.GroupLayout layout) {
        return layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblAnimal2)
                .addComponent(txfAnimal2,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE);
    }

    /**
     * Creates searched animals row.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createSearchedAnimalsRow(
            final javax.swing.GroupLayout layout) {
        return layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSearchedAnimal1)
                .addComponent(lblSearchedAnimal2);
    }

    /**
     * Creates matching section.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createMatchingSection(
            final javax.swing.GroupLayout layout) {
        return layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMatching)
                        .addPreferredGap(
                                javax.swing.LayoutStyle
                                        .ComponentPlacement.RELATED)
                        .addComponent(lblCategories1))
                .addComponent(jScrollPane1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        SCROLL_HEIGHT,
                        javax.swing.GroupLayout.PREFERRED_SIZE);
    }

    /**
     * Creates conflicting section.
     * @param layout the GroupLayout to configure
     * @return the configured group
     */
    private javax.swing.GroupLayout.Group createConflictingSection(
            final javax.swing.GroupLayout layout) {
        return layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        SCROLL_HEIGHT,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblConflicting)
                        .addPreferredGap(
                                javax.swing.LayoutStyle
                                        .ComponentPlacement.RELATED)
                        .addComponent(lblCategories));
    }

    /**
     * Handles compare button action.
     * @param evt the action event
     */
    private void btnCompareActionPerformed(
            final java.awt.event.ActionEvent evt) {
        lblSearchedAnimal1.setText("Searched Animal 1");
        lblSearchedAnimal2.setText(SEARCHED_ANIMAL_2);

        final String animal1Name = txfAnimal1.getText();
        final String animal2Name = txfAnimal2.getText();

        interactor.execute(animal1Name, animal2Name);
    }

    /**
     * Handles return button action.
     * @param evt the action event
     */
    private void btnReturnActionPerformed(
            final java.awt.event.ActionEvent evt) {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    /**
     * Updates label styles based on settings.
     */
    private void updateLabelStyle() {
        textSettingOutput.updateAll(this);
    }

    /**
     * Main method to launch the Compatibility window.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Compatibility().setVisible(true);
        });
    }

    /**
     * Presenter for the Compare Animals use case.
     * Implements the output boundary interface.
     */
    private final class CompareAnimalsPresenter
            implements CompareAnimalsOutputBoundary {
        @Override
        public void presentSuccess(final String animal1Name,
                                   final String animal2Name,
                                   final String matching,
                                   final String conflicting,
                                   final String rating) {
            lblSearchedAnimal1.setText(animal1Name);
            lblSearchedAnimal2.setText(animal2Name);
            txaMatching.setText(matching);
            txaConflicting.setText(conflicting);
            lblRating.setText(rating);
        }

        @Override
        public void presentAnimal1Error() {
            lblSearchedAnimal1.setText("Invalid Animal 1");
            lblSearchedAnimal2.setText(SEARCHED_ANIMAL_2);
            txaMatching.setText("");
            txaConflicting.setText("");
            lblRating.setText("");
        }

        @Override
        public void presentAnimal2Error() {
            lblSearchedAnimal2.setText("Invalid Animal 2");
            txaMatching.setText("");
            txaConflicting.setText("");
            lblRating.setText("");
        }

        @Override
        public void presentAnimal1Empty() {
            lblSearchedAnimal1.setText("Empty Animal 1");
            txaMatching.setText("");
            txaConflicting.setText("");
            lblRating.setText("");
        }

        @Override
        public void presentAnimal2Empty() {
            lblSearchedAnimal2.setText("Empty Animal 2");
            txaMatching.setText("");
            txaConflicting.setText("");
            lblRating.setText("");
        }
    }
}
