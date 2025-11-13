package AppPkg;

import Classes.APIClass;
import Classes.Animal;
import java.util.HashSet;

public class Compatibility extends javax.swing.JFrame
{

    public Compatibility()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents()
    {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compatibility");

        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPkg/home.png"))); // NOI18N
        btnReturn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReturnActionPerformed(evt);
            }
        });

        lblHeading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeading.setText("Pet Compatibility");

        lblAnimal1.setText("Animal 1");

        lblAnimal2.setText("Animal 2");

        btnCompare.setText("Compare");
        btnCompare.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnReturn))
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
                        .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(btnCompare)
                                .addContainerGap(173, Short.MAX_VALUE))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(btnReturn)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt)
    {
        new MainMenu().setVisible(true);
        this.dispose();
    }

    private void btnCompareActionPerformed(java.awt.event.ActionEvent evt)
    {
        String choiceOne = txfAnimal1.getText();
        APIClass api = new APIClass();
        String animal1Data = api.getAnimalData(choiceOne);
        //Animal animalOne = new Animal();

        String choiceTwo = txfAnimal2.getText();
        String animal2Data = api.getAnimalData(choiceTwo);
        //Animal animalTwo = new Animal();

        //ArrayList<String> similar = getSimilar(animalOne, animalTwo);

        //This below line is just a placeholder for now
        String similar = "";

        String similarString = String.join(", ", similar);
        txaMatching.setText(similarString);
        txaConflicting.setText("Goodbye");
        //Making random changes (remove this)
    }

    public static HashSet<String> getSimilar(Animal animal1, Animal animal2){
        //Create Similar Set
        HashSet<String> similar = new HashSet<>();

        if (animal1.getGroup().equals(animal2.getGroup())){
            similar.add("Group");
        }
        if (animal1.getDiet().equals(animal2.getDiet())){
            similar.add("Diet");
        }
        if (animal1.getLifestyle().equals(animal2.getLifestyle())){
            similar.add("Lifestyle");
        }
        for(String location1: animal1.getLocation()){
            for (String location2: animal2.getLocation()){
                if (location1.equals(location2)){
                    similar.add("Location");
                }
            }

            boolean foundPrey = false;
            for(String prey1: animal1.getPrey()) {
                for (String prey2 : animal2.getPrey()) {
                    if (prey1.equals(prey2)) {
                        similar.add("Prey");
                    }
                }
            }
            if (animal1.getHabitat().equals(animal2.getHabitat())) {
                similar.add("Habitat");
            }
        }


        return similar;
    }

    public static void main(String args[])
    {
        new Compatibility().setVisible(true);
    }

    // Variables declaration - do not modify
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
    // End of variables declaration
}
