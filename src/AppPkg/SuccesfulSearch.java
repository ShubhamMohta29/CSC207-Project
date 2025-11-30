package AppPkg;

import Classes.retrieveInfo.Animal;
import Classes.Settings.*;
import Classes.Settings.TextSettingInteractor;
import Classes.Settings.TextSettingOutput;
import Classes.add_favorite.AddFavoriteController;
import Classes.add_favorite.AddFavoriteInputBoundary;
import Classes.add_favorite.AddFavoriteInteractor;
import Classes.add_favorite.FileFavoritesDataAccessObject;

import java.awt.*;

import static Classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;

public class SuccesfulSearch extends javax.swing.JFrame
{
    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);
    private Animal animal;

    public SuccesfulSearch(Animal animal)
    {
        initComponents();
        animalName = animal.getName();
        this.animal = animal;
        lblHeading.setText("Searched: " + Character.toUpperCase(animalName.charAt(0)) + animalName.substring(1));
        jTextArea1.setText(animal.toString());
        updateLabelStyle();
    }

    public SuccesfulSearch()
    {
        initComponents();
        lblHeading.setText("Searched: ");
        updateLabelStyle();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblHeading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnAddFavorite = new javax.swing.JButton();
        btnGenerateTradingCard = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MainMenu().setVisible(true); // Open MainMenu
                dispose(); // Close current frame
            }
        });

        lblHeading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Searched: ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnAddFavorite.setText("Favorite");
        btnAddFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {btnAddFavoriteActionPerformed(evt);}
        });

        btnGenerateTradingCard.setText("Generate Trading Card");
        btnGenerateTradingCard.addActionListener(evt -> {
            new GenerateTradingCard(animal, this).setVisible(true);
            this.setVisible(false);
        });

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btnAddFavorite, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerateTradingCard)
                                .addGap(38, 38, 38))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHome)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHeading)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAddFavorite)
                                        .addComponent(btnGenerateTradingCard))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(btnHome)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHomeActionPerformed
    {//GEN-HEADEREND:event_btnHomeActionPerformed
        new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnAddFavoriteActionPerformed(java.awt.event.ActionEvent evt) {
        final FileFavoritesDataAccessObject favoritesDataAccessObject
                = new FileFavoritesDataAccessObject("favorites.csv");

        final AddFavoriteInputBoundary addFavoriteInteractor = new AddFavoriteInteractor(favoritesDataAccessObject);
        AddFavoriteController addFavoriteController = new AddFavoriteController(addFavoriteInteractor);

        btnAddFavorite.setBackground(Color.RED);
        btnAddFavorite.setOpaque(true);
        // Set timer so the button goes back to normal after 300 ms
        javax.swing.Timer t = new javax.swing.Timer(300, e -> {
            btnAddFavorite.setBackground(null);
            ((javax.swing.Timer) e.getSource()).stop();
        });
        t.start();

        addFavoriteController.execute(animalName);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        new SuccesfulSearch().setVisible(true);
    }

    private void updateLabelStyle(){
        config.updateALL(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    String animalName;
    private javax.swing.JButton btnAddFavorite;
    private javax.swing.JButton btnGenerateTradingCard;
    private javax.swing.JButton btnHome;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblHeading;
    // End of variables declaration//GEN-END:variables
}
