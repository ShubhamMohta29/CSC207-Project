package AppPkg;

import javax.swing.*;
import java.util.List;

public class MultiSuccesfulSearch extends javax.swing.JFrame
{

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

        DefaultListModel<String> listModel = new DefaultListModel<>();
        jListOptions.setModel(listModel);
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

    // updates the list
    public void updateAnimalList(List<String> animals){
        DefaultListModel<String> model = (DefaultListModel<String>) jListOptions.getModel();
        model.clear();
        for (String animal:animals){
            model.addElement(animal);
        }
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
