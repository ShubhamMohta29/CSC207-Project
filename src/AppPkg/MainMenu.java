package AppPkg;

import Classes.APIClass;
import Classes.Animal;
import Classes.Settings.ReaderEditor;

import javax.swing.JOptionPane;
import java.awt.*;

public class  MainMenu extends javax.swing.JFrame
{
    private ReaderEditor config = new ReaderEditor("settings.csv");

    public MainMenu()
    {
        initComponents();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblGreeting1 = new javax.swing.JLabel();
        lblGreeting2 = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        txfAnimal = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnCompatibility = new javax.swing.JButton();
        btnFavorites = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");

        lblGreeting1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        lblGreeting1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGreeting1.setText("Hello User");

        lblGreeting2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGreeting2.setText("Welcome to SKALBS'S Animal Encyclopdia");

        lblQuestion.setText("What animal are you searching for?");

        txfAnimal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/filter.png"))); // NOI18N
        btnFilter.setPreferredSize(new java.awt.Dimension(393, 369));
        btnFilter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnFilterActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSearchActionPerformed(evt);
            }
        });

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/settings.png"))); // NOI18N
        btnSettings.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSettingsActionPerformed(evt);
            }
        });

        btnCompatibility.setText("Match Compatibility");
        btnCompatibility.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCompatibilityActionPerformed(evt);
            }
        });

        btnFavorites.setText("Favorites");
        btnFavorites.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnFavoritesActionPerformed(evt);
            }
        });

        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblError.setText(" ");
        updateLabelStyle( );



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblGreeting1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(btnSettings))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(txfAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnCompatibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFavorites)
                .addGap(56, 56, 56))
            .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblGreeting2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
            .addGroup(layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSettings)
                    .addComponent(lblGreeting1))
                .addGap(18, 18, 18)
                .addComponent(lblGreeting2)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txfAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompatibility)
                    .addComponent(btnFavorites))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSettingsActionPerformed
    {//GEN-HEADEREND:event_btnSettingsActionPerformed
        new Settings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSearchActionPerformed
    {//GEN-HEADEREND:event_btnSearchActionPerformed
        String animalName = txfAnimal.getText().toLowerCase();  // gets the animal name, and makes it lowercase

        if (animalName.isEmpty()){  // ensures the user has given an input. if not, terminates teh call
            lblError.setText("Please select an animal name.");
        } else {
            APIClass aClass = new Classes.APIClass();               // instantiates APIClass
            String result = aClass.getAnimalData(animalName);       // calls getAnimalData to get the JSON data of the animal
            int numResults = aClass.numResults();                   // gets the number of animals' data that was returned
            Animal searched = new Animal(result);

            System.out.println(result);


            if (numResults == 0)   // asks the user to ensure they entered the correct animal because an animal with the user inputted spelling doesn't exist in the API
            {
                lblError.setText("Please double check animal name.");
            }
            if (numResults == 1)    // if there is only 1 outputting result, open SuccesfulSearch because the animal's data will be output there
            {
                new SuccesfulSearch(searched).setVisible(true);
                this.dispose();
            }
            if (numResults >= 2)
            {
                new MultiSuccesfulSearch().setVisible(true);
                this.dispose();
            }
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnFilterActionPerformed
    {//GEN-HEADEREND:event_btnFilterActionPerformed
        FilterGUI filterFrame = new FilterGUI(this);  // create the frame

        //get MainMenu's co-ords
        int x = this.getX() + this.getWidth();
        int y = this.getY();

        filterFrame.setLocation(x, y);  // set the location
        filterFrame.setVisible(true);   // make it visible
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnCompatibilityActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCompatibilityActionPerformed
    {//GEN-HEADEREND:event_btnCompatibilityActionPerformed
        new Compatibility().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCompatibilityActionPerformed

    private void btnFavoritesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnFavoritesActionPerformed
    {//GEN-HEADEREND:event_btnFavoritesActionPerformed
        new Favorites().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFavoritesActionPerformed

    private void updateLabelStyle(){
        Color fg = config.getColor();
        Font font = config.getStyle();
        lblGreeting1.setForeground(fg);
        lblGreeting2.setForeground(fg);
        lblQuestion.setForeground(fg);
        lblQuestion.setFont(font);
        txfAnimal.setForeground(fg);
        txfAnimal.setFont(font);
        btnFilter.setForeground(fg);
        btnFilter.setFont(font);
        btnSearch.setForeground(fg);
        btnSearch.setFont(font);
        btnCompatibility.setForeground(fg);
        btnCompatibility.setFont(font);
        btnFavorites.setForeground(fg);
        btnFavorites.setFont(font);
        lblError.setForeground(fg);
        lblError.setFont(font);

    }

    public static void main(String args[])
    {
        new MainMenu().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompatibility;
    private javax.swing.JButton btnFavorites;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSettings;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblGreeting1;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txfAnimal;
    // End of variables declaration//GEN-END:variables
}
