package expresiones.view;

import expresiones.code.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Vista extends javax.swing.JFrame {
    private Arbol arbol = null;
    private Automata automata = null;
    
    public Vista() {
        initComponents();
    }
    
    // Reinicia los datos del programa para volver a empezar
    private void reset_valores(){
        arbol = null;
        automata = null;
        this.txtExpresion.setText("");
        this.txtVerificar.setText("");
        DefaultTableModel tableModel = (DefaultTableModel)tabla.getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
    }
    
    // Muestra la tabla del automata
    public void asignar_valores() {
        DefaultTableModel tableModel = (DefaultTableModel)tabla.getModel();
        tableModel.addColumn("Estado");
        Set<Character> alfabeto = automata.alfabeto;
        for(Character simbolo : alfabeto) 
            tableModel.addColumn(simbolo);
        Hashtable<Character,Integer> transiciones[] = automata.transiciones;
        for(int i = 1; i <= automata.n_estados; i++){
            ArrayList<Object> row = new ArrayList<>();
            String estadoFinal = "";
            if(automata.es_estado_final(i)) 
                estadoFinal = "*";
            row.add(i + estadoFinal);
            for(Character simbolo : alfabeto) 
                row.add(transiciones[i].get(simbolo));
            tableModel.addRow(row.toArray());
        }
        this.tabla.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtExpresion = new javax.swing.JTextField();
        txtVerificar = new javax.swing.JTextField();
        lblExpresion = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        lblVerificar = new javax.swing.JLabel();
        btnVerificar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Expresiones Regulares");
        setBackground(new java.awt.Color(65, 164, 143));
        setIconImages(null);
        setResizable(false);

        panel.setBackground(new java.awt.Color(65, 164, 143));
        panel.setPreferredSize(new java.awt.Dimension(498, 350));

        lblTitulo.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(25, 30, 52));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Expresiones Regulares");

        txtExpresion.setBackground(java.awt.Color.lightGray);
        txtExpresion.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        txtVerificar.setBackground(java.awt.Color.lightGray);
        txtVerificar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        lblExpresion.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        lblExpresion.setForeground(new java.awt.Color(25, 30, 52));
        lblExpresion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpresion.setText("Ingrese la expresion regular:");

        btnCrear.setBackground(new java.awt.Color(13, 75, 86));
        btnCrear.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnCrear.setForeground(java.awt.Color.lightGray);
        btnCrear.setText("Crear Automata");
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        lblVerificar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        lblVerificar.setForeground(new java.awt.Color(25, 30, 52));
        lblVerificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVerificar.setText("<html><p>Ingrese la expresion que  desea verificar:</p>");

        btnVerificar.setBackground(new java.awt.Color(13, 75, 86));
        btnVerificar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnVerificar.setForeground(java.awt.Color.lightGray);
        btnVerificar.setText("Verificar Hilera");
        btnVerificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnReiniciar.setBackground(new java.awt.Color(13, 75, 86));
        btnReiniciar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        btnReiniciar.setForeground(java.awt.Color.lightGray);
        btnReiniciar.setText("Reiniciar");
        btnReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        tabla.setBackground(java.awt.Color.lightGray);
        tabla.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        tabla.setForeground(new java.awt.Color(0, 0, 0));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setEnabled(false);
        tabla.setSelectionBackground(java.awt.Color.lightGray);
        tabla.setSelectionForeground(java.awt.Color.lightGray);
        tabla.setShowGrid(false);
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblVerificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrear)
                        .addGap(24, 24, 24)
                        .addComponent(lblVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(btnReiniciar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Creación del Automata y Arbol
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String expresion = this.txtExpresion.getText();
        if(!expresion.isEmpty()) {
            reset_valores();
            this.txtExpresion.setText(expresion);
            arbol = new Arbol(expresion);
            arbol.crear_arbol();
            arbol.calculo_posiciones();
            automata = new Automata(arbol, expresion);
            automata.crear_automata();
            asignar_valores();
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    // Verificar hilera entrada
    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        String hilera = txtVerificar.getText();
        if(!hilera.isEmpty() && automata != null){
            if(automata.verificar_cadena(hilera)) JOptionPane.showMessageDialog(null, "Se ha reconocido la cadena.");
            else JOptionPane.showMessageDialog(null, "No se ha reconocido la cadena.");
        }
        this.txtVerificar.setText("");
    }//GEN-LAST:event_btnVerificarActionPerformed

    // Reiniciar la aplicación
    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        reset_valores();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExpresion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVerificar;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtExpresion;
    private javax.swing.JTextField txtVerificar;
    // End of variables declaration//GEN-END:variables
}
