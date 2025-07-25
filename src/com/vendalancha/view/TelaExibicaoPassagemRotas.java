/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vendalancha.view;

import com.vendalancha.model.Passagem;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class TelaExibicaoPassagemRotas extends javax.swing.JFrame {

    private ArrayList<Passagem> passagens;
    /**
     * Creates new form TelaExibicaoPassagemRotas
     */
    public TelaExibicaoPassagemRotas(String origem, String destino, String embarcacao, String data, String horario, ArrayList<Passagem> passagens) {
        initComponents();
        this.passagens = passagens;
        jt_tabelaPassagens.setRowHeight(30);
        estilizarHeader();
        carregarDados();
        tf_infos.setText(origem + " -> " + destino + " (" + embarcacao + ") - " + data + ", " + horario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabelaPassagens = new javax.swing.JTable();
        tf_infos = new javax.swing.JLabel();
        tf_data = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jt_tabelaPassagens.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jt_tabelaPassagens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tipo", "P1", "P2", "P3"
            }
        ));
        jScrollPane1.setViewportView(jt_tabelaPassagens);

        tf_infos.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        tf_infos.setText("Infos:");

        tf_data.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(tf_data))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(tf_infos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(tf_infos)
                .addGap(26, 26, 26)
                .addComponent(tf_data)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaExibicaoPassagemRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoPassagemRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoPassagemRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoPassagemRotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaExibicaoPassagemRotas("", "", "", "", "", new ArrayList<Passagem>()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tabelaPassagens;
    private javax.swing.JLabel tf_data;
    private javax.swing.JLabel tf_infos;
    // End of variables declaration//GEN-END:variables

     private void estilizarHeader(){
        JTableHeader header = jt_tabelaPassagens.getTableHeader();
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        header.setBackground(new Color(173,216,230));
        header.setForeground(Color.BLACK);
    }
    
    private void carregarDados(){        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula será editável
            }
        };
        
        modelo.addColumn("Tipo");
        modelo.addColumn("P1");
        modelo.addColumn("P2");
        modelo.addColumn("P3");

        for (Passagem p : passagens) {
            String tipo = p.getTipoPassagem().toString();
            String p1 = p.getPassegeiroN1() != null ? p.getPassegeiroN1().getNome() : "";
            String p2 = p.getPassageiroN2() != null ? p.getPassageiroN2().getNome() : "";
            String p3 = p.getPassageiroN3() != null ? p.getPassageiroN3().getNome() : "";
            modelo.addRow(new Object[]{
                tipo, p1, p2, p3
            });
        }
        jt_tabelaPassagens.setModel(modelo);
        
    }
}
