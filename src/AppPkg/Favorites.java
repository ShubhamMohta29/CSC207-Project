package AppPkg;

import Classes.retrieveInfo.Animal;
import Classes.retrieveInfo.APIClass;
import Classes.Settings.*;
import Classes.add_favorite.*;
import Classes.retrieveInfo.AnimalFactory;

import javax.swing.*;

import static Classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;

public class Favorites extends javax.swing.JFrame
{
    private final AnimalFactory factory;
    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);

    public Favorites()
    {
        this.factory = new AnimalFactory();
        initComponents();
        config.updateALL(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        btnReturn = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lblHeading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFavs = new javax.swing.JList<>();
        final FavoriteList favorites = new FileFavoritesDataAccessObject("favorites.csv").getFavoriteList();
        final AddFavoriteOutputData addFavoriteOutputData =
                new AddFavoriteOutputData(favorites.getFavorites().toArray(new String[0]));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Favorites");

        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png"))); // NOI18N
        btnReturn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReturnActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {btnRemoveActionPerformed(evt);}
        });

        lblHeading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Favorites");

        jListFavs.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = addFavoriteOutputData.getFavList();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListFavs);

        jListFavs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {btnMouseClickActionPerformed(evt);}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnReturn))
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove)
                        .addGap(18, 18, 18)
                    .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnReturn)
                        .addComponent(btnRemove))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReturnActionPerformed
    {//GEN-HEADEREND:event_btnReturnActionPerformed
        new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnMouseClickActionPerformed(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            String animalName = jListFavs.getSelectedValue().toLowerCase().trim();
            if (animalName.contains(" ")) {
                animalName = animalName.replace(" ", "%20");
            }
            APIClass apiClass = new APIClass();
            String jsonResponse = apiClass.getAnimalData(animalName);

            Animal animal = factory.fromJsonArrayString(jsonResponse);
            new SuccesfulSearch(animal).setVisible(true);
            this.dispose();
        }
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        final AddFavoriteInputBoundary addFavoriteInteractor = new AddFavoriteInteractor(favoritesDataAccessObject);
        AddFavoriteController addFavoriteController = new AddFavoriteController(addFavoriteInteractor);
        String animalName = jListFavs.getSelectedValue();
        if (animalName == null) {
            JOptionPane.showMessageDialog(null, "Select a name to remove");
            return;
        }
        addFavoriteController.execute1(animalName);
        new Favorites().setVisible(true);
        this.dispose();
    }

    public static void main(String args[])
    {
        new Favorites().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturn;
    private javax.swing.JList<String> jListFavs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JButton btnRemove;
    final FileFavoritesDataAccessObject favoritesDataAccessObject
            = new FileFavoritesDataAccessObject("favorites.csv");
    // End of variables declaration//GEN-END:variables
}
