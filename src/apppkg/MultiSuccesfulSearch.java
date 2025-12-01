package apppkg;

import classes.retrieveInfo.Animal;

import javax.swing.*;

import static classes.Settings.SettingConstants.DEFAULT_SETTINGS_FILE;

public class MultiSuccesfulSearch extends javax.swing.JFrame
{
    private final UIManager config = new UIManager(DEFAULT_SETTINGS_FILE);

    public MultiSuccesfulSearch(Animal[] animals)
    {
        initComponents();

        // Populates the JList
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Animal a : animals) {
            model.addElement(a.getName());
        }
        jListOptions.setModel(model);

        // Allows the user to get the information of their desired animal from the list by them double clicking on any of the animals in teh list
        jListOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int selectedIndex = jListOptions.locationToIndex(evt.getPoint());
                    if (selectedIndex >= 0) {
                        Animal selectedAnimal = animals[selectedIndex];

                        new SuccesfulSearch(selectedAnimal).setVisible(true);
                        MultiSuccesfulSearch.this.dispose();
                    }
                }
            }
        });
        config.updateALL(this);
    }

    public MultiSuccesfulSearch()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents()
    {

        lblHeading = new javax.swing.JLabel();
        lblHeading2 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListOptions = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblHeading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Current Matches");

        lblHeading2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading2.setText("Choose one");

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHomeActionPerformed(evt);
            }
        });

        jListOptions.setModel(new javax.swing.AbstractListModel<String>()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListOptions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnHome))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblHeading2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblHeading)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblHeading2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(btnHome)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt)
    {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    public static void main(String args[])
    {
        new MultiSuccesfulSearch().setVisible(true);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnHome;
    private javax.swing.JList<String> jListOptions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblHeading2;
    // End of variables declaration
}
