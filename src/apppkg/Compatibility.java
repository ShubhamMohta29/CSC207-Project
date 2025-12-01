package apppkg;

import classes.Compatibility.usecases.CompareAnimalsInputBoundary;
import classes.Compatibility.usecases.CompareAnimalsOutputBoundary;
import classes.Compatibility.usecases.CompareAnimalsInteractor;
import classes.Compatibility.usecases.AnimalDataAccessInterface;
import classes.Compatibility.data.AnimalDataAccess;

import static classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;

public class Compatibility extends javax.swing.JFrame
{
    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);

    // Controller and Presenter are now part of this class for simplicity
    private final CompareAnimalsInputBoundary interactor;

    public Compatibility()
    {
        // Build the CA Engine in constructor
        AnimalDataAccessInterface dataAccess = new AnimalDataAccess();
        CompareAnimalsOutputBoundary presenter = new CompareAnimalsPresenter();
        this.interactor = new CompareAnimalsInteractor(dataAccess, presenter);

        initComponents();
        updateLabelStyle();
    }

    // Inner class: Presenter (Interface Adapter Layer)
    private class CompareAnimalsPresenter implements CompareAnimalsOutputBoundary {
        @Override
        public void presentSuccess(String animal1Name, String animal2Name,
                                   String matching, String conflicting, String rating) {
            lblSearchedAnimal1.setText(animal1Name);
            lblSearchedAnimal2.setText(animal2Name);
            txaMatching.setText(matching);
            txaConflicting.setText(conflicting);
            lblRating.setText(rating);
        }

        @Override
        public void presentAnimal1Error() {
            lblSearchedAnimal1.setText("Invalid Animal 1");
            lblSearchedAnimal2.setText("Searched Animal 2");
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

    @SuppressWarnings("unchecked")
    private void initComponents() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compatibility");

        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png")));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        lblHeading.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Pet Compatibility");

        lblAnimal1.setText("Animal 1");
        lblAnimal2.setText("Animal 2");

        btnCompare.setText("Compare");
        btnCompare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompareActionPerformed(evt);
            }
        });

        lblMatching.setText("Matching");

        txaMatching.setColumns(20);
        txaMatching.setRows(5);
        jScrollPane1.setViewportView(txaMatching);

        lblCategories1.setText("Categories");

        lblConflicting.setText("Conflicting");

        txaConflicting.setColumns(20);
        txaConflicting.setRows(5);
        jScrollPane2.setViewportView(txaConflicting);

        lblCategories.setText("Categories");

        lblSearchedAnimal1.setText("Searched Animal 1");
        lblSearchedAnimal2.setText("Searched Animal 2");
        lblRating.setText("Rating");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblAnimal1)
                                                        .addComponent(lblAnimal2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txfAnimal2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                        .addComponent(txfAnimal1)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblConflicting)
                                                        .addComponent(lblMatching)
                                                        .addComponent(lblCategories1)
                                                        .addComponent(lblCategories))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane2))))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCompare)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(140)
                                .addComponent(lblSearchedAnimal1)
                                .addGap(40)
                                .addComponent(lblSearchedAnimal2)
                                .addContainerGap(140, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblRating)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(btnReturn)
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHeading)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAnimal1)
                                        .addComponent(txfAnimal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAnimal2)
                                        .addComponent(txfAnimal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCompare)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSearchedAnimal1)
                                        .addComponent(lblSearchedAnimal2))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblMatching)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCategories1))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblConflicting)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCategories)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRating)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(btnReturn)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Controller logic (Interface Adapter Layer)
    private void btnCompareActionPerformed(java.awt.event.ActionEvent evt)
    {
        // Reset labels
        lblSearchedAnimal1.setText("Searched Animal 1");
        lblSearchedAnimal2.setText("Searched Animal 2");

        // Get user input and pass to interactor
        String animal1Name = txfAnimal1.getText();
        String animal2Name = txfAnimal2.getText();

        // Call the use case
        interactor.execute(animal1Name, animal2Name);
    }

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt)
    {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(() -> {
            new Compatibility().setVisible(true);
        });
    }

    private void updateLabelStyle(){
        config.updateALL(this);
    }

    // Variables declaration
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
}