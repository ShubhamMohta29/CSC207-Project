package AppPkg;

import Classes.Settings.ReaderEditor;

import java.awt.*;

public class Settings extends javax.swing.JFrame {
    private ReaderEditor config = new ReaderEditor("settings.csv");
    private String color = "black";
    private String style = "Arial";
    private int size = 2;

    public Settings() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setupListeners();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        Color fg = config.getColor();
        Font font = config.getStyle();

        lblFontSize = new javax.swing.JLabel();
        lblFontSize.setForeground(fg);
        lblFontSize.setFont(font);

        cBoxFontSizes = new javax.swing.JComboBox<>();
        cBoxFontSizes.setForeground(fg);
        cBoxFontSizes.setFont(font);

        lblFont = new javax.swing.JLabel();
        lblFont.setForeground(fg);
        lblFont.setFont(font);

        cBoxFonts = new javax.swing.JComboBox<>();
        cBoxFonts.setForeground(fg);
        cBoxFonts.setFont(font);

        lblColor = new javax.swing.JLabel();
        lblColor.setForeground(fg);
        lblColor.setFont(font);

        cBoxColor = new javax.swing.JComboBox<>();
        cBoxColor.setForeground(fg);
        cBoxColor.setFont(font);

        btnSave = new javax.swing.JButton();
        btnSave.setForeground(fg);
        btnSave.setFont(font);

        btnDefault = new javax.swing.JButton();
        btnDefault.setForeground(fg);
        btnDefault.setFont(font);

        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");

        lblFontSize.setText("Font Size");

        cBoxFontSizes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5"}));

        lblFont.setText("Font");

        cBoxFonts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Arial", "Times New Roman", "Droid Sans Georgian", "DejaVu Sans"}));

        lblColor.setText("Color");

        cBoxColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"black", "blue", "green", "purple"}));

        btnSave.setText("Save");

        btnDefault.setText("Restore Defaults");

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFontSize)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cBoxFontSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFont)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cBoxFonts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblColor)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnSave)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnDefault))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnHome)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFontSize)
                                        .addComponent(cBoxFontSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFont)
                                        .addComponent(cBoxFonts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblColor)
                                        .addComponent(cBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSave)
                                        .addComponent(btnDefault))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(btnHome)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHomeActionPerformed
    {//GEN-HEADEREND:event_btnHomeActionPerformed
        // new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void setupListeners() {
        // Font size combo box
        cBoxFontSizes.addActionListener(e -> {
            size = Integer.parseInt((String) cBoxFontSizes.getSelectedItem());
        });

        // Font style combo box
        cBoxFonts.addActionListener(e -> {
            style = (String) cBoxFonts.getSelectedItem();
        });

        // Color combo box
        cBoxColor.addActionListener(e -> {
            color = (String) cBoxColor.getSelectedItem();


        });


        btnDefault.addActionListener(e -> {
            size = 2;
            style = "Aerial";
            color = "black";
            updateLabelStyle();

        });

        btnSave.addActionListener(e -> {
            updateLabelStyle();
        });
    }

    private void updateLabelStyle() {
        config.editSettings(color, size, style);
        Color fg = config.getColor();
        Font font = config.getStyle();

        lblFontSize.setForeground(fg);
        lblFontSize.setFont(font);

        cBoxFontSizes.setForeground(fg);
        cBoxFontSizes.setFont(font);

        lblFont.setForeground(fg);
        lblFont.setFont(font);

        cBoxFonts.setForeground(fg);
        cBoxFonts.setFont(font);

        lblColor.setForeground(fg);
        lblColor.setFont(font);

        cBoxColor.setForeground(fg);
        cBoxColor.setFont(font);

        btnSave.setForeground(fg);
        btnSave.setFont(font);

        btnDefault.setForeground(fg);
        btnDefault.setFont(font);

        // Automatically resize window to fit updated font sizes
        revalidate();
        pack();

    }


    public static void main(String args[]) {
        new Settings().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDefault;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cBoxColor;
    private javax.swing.JComboBox<String> cBoxFontSizes;
    private javax.swing.JComboBox<String> cBoxFonts;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblFont;
    private javax.swing.JLabel lblFontSize;
    // End of variables declaration//GEN-END:variables
}
