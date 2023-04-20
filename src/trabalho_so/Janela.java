
package trabalho_so;

public class Janela extends javax.swing.JFrame {

    
    public Janela() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cb_escalonadores = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        bt_iniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Algoritmo de Escalonamento");

        cb_escalonadores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_escalonadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SJF", "ROUND ROBIN", "RATE MONITONIC", "EDF" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Escolha o escalonador:");

        bt_iniciar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bt_iniciar.setText("Iniciar");
        bt_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_iniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(cb_escalonadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(bt_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cb_escalonadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(bt_iniciar)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMouseClicked
         switch(cb_escalonadores.getSelectedItem().toString()){
            case "FCFS":
                System.out.println("ALGORITMO DE ESCALONAMENTO FCFS \n");
                FcFs sistema1 = new FcFs();
                sistema1.escalona();
                System.out.println("///////////////////////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////\n\n");
                break;
                
            case "SJF":
                System.out.println("ALGORITMO DE ESCALONAMENTO SJF \n");
                Sjf sistema2 = new Sjf();
                sistema2.escalona();
                System.out.println("///////////////////////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////\n\n");
                break;
                
            case "ROUND ROBIN":
                System.out.println("ALGORITMO DE ESCALONAMENTO ROUND ROBIN \n");
                RoundRobin sistema3 = new RoundRobin();
                sistema3.escalona(sistema3.OrdenarTempoComputacional());
                System.out.println("///////////////////////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////\n\n");
                break;
                
            case "RATE MONITONIC":
                System.out.println("ALGORITMO DE ESCALONAMENTO RATE MONITONIC \n");
                RateMonitonic sistema4 = new RateMonitonic();
                sistema4.escalonar();
                System.out.println("///////////////////////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////\n\n");
                break;
                
            case "EDF": 
                System.out.println("ALGORITMO DE ESCALONAMENTO RATE MONITONIC \n");
                EDF sistema5 = new EDF();
                sistema5.escalonar();
                System.out.println("///////////////////////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////\n\n");
                break;
        }
    }//GEN-LAST:event_bt_iniciarMouseClicked

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_iniciar;
    private javax.swing.JComboBox<String> cb_escalonadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
