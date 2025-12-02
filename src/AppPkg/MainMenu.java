package AppPkg;

// Standard Java packages
import java.awt.Cursor;
import java.awt.Font;

// Project imports - Classes package
import Classes.Filter.FuzzySearch.AnimalFuzzySearch;
import Classes.Filter.FuzzySearch.FuzzySearchProvider;
import Classes.ViewSavedCards.FileSystemLoadSavedCardsDataAccess;
import Classes.ViewSavedCards.LoadSavedCardsController;
import Classes.ViewSavedCards.LoadSavedCardsInteractor;
import Classes.ViewSavedCards.LoadSavedCardsPresenter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

import static Classes.Settings.SettingConstants.*;
import Classes.retrieveInfo.APIClass;
import Classes.retrieveInfo.AnimalFactory;
import AppPkg.Controllers.SearchController;
import AppPkg.Controllers.SearchResult;
import static Classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;
import static Classes.Settings.SettingConstants.DEFAULT_STYLE;
import static Classes.Settings.SettingConstants.HEADING_FONT_SIZE;

public class  MainMenu extends javax.swing.JFrame
{
    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);
    private APIClass api = new APIClass();
    private FuzzySearchProvider fuzzyProvider = new AnimalFuzzySearch();

    private final SearchController searchController;

    public MainMenu()
    {
        this.searchController = new SearchController(this.api, this.fuzzyProvider, new AnimalFactory());
        initComponents();
        updateLabelStyle();// apply setting changes
    }

    public MainMenu(FuzzySearchProvider fuzzyProvider, APIClass api){
        this.fuzzyProvider = fuzzyProvider;
        this.api = api;
        this.searchController = new SearchController(this.api, this.fuzzyProvider, new AnimalFactory());
        initComponents();
        updateLabelStyle();
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
        btnMyCards = new javax.swing.JButton();

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
        btnMyCards.setText("View My Saved Cards");
        btnMyCards.addActionListener(evt -> openSavedCardsLibrary());


        btnFavorites.setText("View Favourites");
        btnFavorites.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnFavoritesActionPerformed(evt);
            }
        });

        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblError.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblGreeting1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnSettings))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblQuestion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                            //.addComponent(btnMyCards)
                            .addComponent(txfAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                    .addComponent(btnMyCards)
                .addComponent(btnCompatibility)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFavorites, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblGreeting2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(btnMyCards)
                    .addComponent(btnFavorites))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openSavedCardsLibrary() {
        var controller = new LoadSavedCardsController(
                new LoadSavedCardsInteractor(
                        new FileSystemLoadSavedCardsDataAccess(),
                        new LoadSavedCardsPresenter()
                )
        );

        var response = controller.load();
        new SavedCardScreen(response).setVisible(true);
        this.dispose();
    }

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSettingsActionPerformed
    {//GEN-HEADEREND:event_btnSettingsActionPerformed
        new Settings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSearchActionPerformed
    {//GEN-HEADEREND:event_btnSearchActionPerformed
        String query = txfAnimal.getText();
        SearchResult res = searchController.search(query);

        if (!res.isSuccess()) {
            if (res.getSuggestion() != null && !res.getSuggestion().isEmpty()) {
                lblError.setText(res.getMessage());
                lblError.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblError.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txfAnimal.setText(res.getSuggestion());
                        btnSearchActionPerformed(null);
                    }
                });
            } else {
                lblError.setText(res.getMessage());
            }
            return;
        }

        int n = res.getAnimals().length;
        if (n == 1) {
            new SuccesfulSearch(res.getAnimals()[0]).setVisible(true);
            this.dispose();
        } else if (n >= 2) {
            new MultiSuccesfulSearch(res.getAnimals()).setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnFilterActionPerformed
    {//GEN-HEADEREND:event_btnFilterActionPerformed
        FilterGUI filterFrame = FilterGUIFactory.create(this);
        filterFrame.setLocation(this.getX() + this.getWidth(), this.getY());
        filterFrame.setVisible(true);
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
        config.updateALL(this);
        lblGreeting1.setFont(
                new Font(config.getFont().getName(), DEFAULT_STYLE, HEADING_FONT_SIZE));
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
    private javax.swing.JButton btnMyCards;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblGreeting1;
    private javax.swing.JLabel lblGreeting2;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txfAnimal;
    // End of variables declaration//GEN-END:variables
}
