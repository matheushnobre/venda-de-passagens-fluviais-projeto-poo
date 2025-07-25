/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.vendalancha.view;

import com.vendalancha.control.LanchaController;
import com.vendalancha.dao.LanchaDAO;
import com.vendalancha.model.Lancha;
import com.vendalancha.util.IconeUtil;
import com.vendalancha.util.validacao.ValidacaoEntrada;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class TelaExibicaoLanchas extends javax.swing.JFrame {

    /**
     * Creates new form TelaExibicaoLanchas
     */
    public TelaExibicaoLanchas() {
        initComponents();
        jt_tabelaLanchas.setRowHeight(30);
        estilizarHeader();
        carregarDados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tabelaLanchas = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        btn_deletar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Lanchas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vendalancha/imagens/lancha32.png"))); // NOI18N

        jt_tabelaLanchas.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jt_tabelaLanchas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Nº de Poltronas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tabelaLanchas.setMaximumSize(null);
        jScrollPane1.setViewportView(jt_tabelaLanchas);

        jScrollPane2.setViewportView(jScrollPane1);

        btnVoltar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vendalancha/imagens/voltar32.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btn_deletar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn_deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vendalancha/imagens/not-ok32.png"))); // NOI18N
        btn_deletar.setText("Deletar");
        btn_deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletarActionPerformed(evt);
            }
        });

        btn_editar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vendalancha/imagens/edit32.png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(45, 45, 45)
                        .addComponent(btn_deletar)
                        .addGap(44, 44, 44)
                        .addComponent(btn_editar)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addGap(261, 261, 261))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btn_deletar)
                    .addComponent(btn_editar))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btn_deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletarActionPerformed
        // TODO add your handling code here:
        int linha = jt_tabelaLanchas.getSelectedRow();
        if(linha >= 0){
            String nomeBarco = jt_tabelaLanchas.getValueAt(linha, 0).toString();
            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar a lancha " + nomeBarco + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                if(LanchaController.deletar(nomeBarco)){ 
                    carregarDados(); // 
                    JOptionPane.showMessageDialog(rootPane, "Deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, IconeUtil.getIconeSucesso());
                    return;
                }
                
                JOptionPane.showMessageDialog(rootPane, "Um erro inesperado aconteceu.", "Erro", JOptionPane.INFORMATION_MESSAGE, IconeUtil.getIconeErro());
            }
            return;
        }
        
        JOptionPane.showMessageDialog(rootPane, "Selecione uma lancha", "Erro: nenhuma lancha selecionada", JOptionPane.ERROR_MESSAGE, IconeUtil.getIconeErro());
        
    }//GEN-LAST:event_btn_deletarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        int linha = jt_tabelaLanchas.getSelectedRow();
        
        if(linha >= 0){
            String nome = jt_tabelaLanchas.getValueAt(linha, 0).toString();
            String str_novo = JOptionPane.showInputDialog("Insira o novo número de poltronas: ");
            if(str_novo == null) return; // desistiu de editar lancha
            
            int mensagem = LanchaController.editarLancha(nome, str_novo);
            switch(mensagem){
                case 1 -> {
                    JOptionPane.showMessageDialog(rootPane, "Valor alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, IconeUtil.getIconeSucesso());
                    jt_tabelaLanchas.setValueAt(str_novo, linha, 1); 
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(rootPane, "Nº de poltronas inválido.", "Erro", JOptionPane.ERROR_MESSAGE, IconeUtil.getIconeErro());            
                }
                case 3 -> {
                    JOptionPane.showMessageDialog(rootPane, "Um erro inesperado aconteceu.", "Erro", JOptionPane.ERROR_MESSAGE, IconeUtil.getIconeErro());            
                }
            }
            
            return;
        }
        
        JOptionPane.showMessageDialog(rootPane, "Selecione uma lancha", "Erro: nenhuma lancha selecionada", JOptionPane.ERROR_MESSAGE, IconeUtil.getIconeErro());            
    }//GEN-LAST:event_btn_editarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaExibicaoLanchas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoLanchas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoLanchas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaExibicaoLanchas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaExibicaoLanchas().setVisible(true);
            }
        });
    }
    
    private void estilizarHeader(){
        JTableHeader header = jt_tabelaLanchas.getTableHeader();
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        header.setBackground(new Color(173,216,230));
        header.setForeground(Color.BLACK);
    }
    
    private void carregarDados(){
        ArrayList<Lancha> embarcacoes = LanchaDAO.carregarEmbarcacoes();
        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula será editável
            }
        };
        modelo.addColumn("Nome da Embarcação");
        modelo.addColumn("Nº Poltronas");

        for (Lancha e : embarcacoes) {
            modelo.addRow(new Object[]{
                e.getNome(),
                e.getnPoltronas(),
            });
        }
        jt_tabelaLanchas.setModel(modelo);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btn_deletar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jt_tabelaLanchas;
    // End of variables declaration//GEN-END:variables
}
