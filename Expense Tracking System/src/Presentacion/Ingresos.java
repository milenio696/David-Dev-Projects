/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import Entidades.Gasto;
import LogicaNegocio.LogicaGasto;
import javax.swing.JOptionPane;

public class Ingresos extends javax.swing.JFrame {

    Gasto gasto = new Gasto();
    SeleccionUtilidad sU = new SeleccionUtilidad();
    LogicaGasto logic = new LogicaGasto();
    
    public Ingresos() {
        initComponents();
        
        mensualLBL.setVisible(false);
        IngresoMensualTXT.setVisible(false);
        AnualLBL.setVisible(false);
        IngresoAnualTXT.setVisible(false);        
        AgregarAnaulBTN.setVisible(false);
        AgregarMensualBTN.setVisible(false);
        añoLBL.setVisible(false);
        añoTXT.setVisible(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImagenLBL = new javax.swing.JLabel();
        TituloLBL = new javax.swing.JLabel();
        MensualBTN = new javax.swing.JButton();
        AnualBTN = new javax.swing.JButton();
        mensualLBL = new javax.swing.JLabel();
        AnualLBL = new javax.swing.JLabel();
        IngresoMensualTXT = new javax.swing.JTextField();
        IngresoAnualTXT = new javax.swing.JTextField();
        AgregarMensualBTN = new javax.swing.JButton();
        CancelBTN = new javax.swing.JButton();
        AgregarAnaulBTN = new javax.swing.JButton();
        añoLBL = new javax.swing.JLabel();
        añoTXT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImagenLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/360_F_514951224_2dxMLbIw5qNRdPGD003chpbVcxWtcp7K.jpg"))); // NOI18N
        getContentPane().add(ImagenLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TituloLBL.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        TituloLBL.setText("Deseas agregar los ingresos de manera mensual o anual?");
        getContentPane().add(TituloLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        MensualBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MensualBTN.setText("Mensual");
        MensualBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MensualBTNActionPerformed(evt);
            }
        });
        getContentPane().add(MensualBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 100, 30));

        AnualBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AnualBTN.setText("Anual");
        AnualBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnualBTNActionPerformed(evt);
            }
        });
        getContentPane().add(AnualBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 94, 30));

        mensualLBL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        mensualLBL.setText("Ingreso Mensual:");
        getContentPane().add(mensualLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, -1, 30));

        AnualLBL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AnualLBL.setText("Ingreso Anual:");
        getContentPane().add(AnualLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, -1, 30));

        IngresoMensualTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresoMensualTXTActionPerformed(evt);
            }
        });
        getContentPane().add(IngresoMensualTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 319, 30));

        IngresoAnualTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresoAnualTXTActionPerformed(evt);
            }
        });
        getContentPane().add(IngresoAnualTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 300, 30));

        AgregarMensualBTN.setText("Agregar");
        AgregarMensualBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarMensualBTNActionPerformed(evt);
            }
        });
        getContentPane().add(AgregarMensualBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 103, 30));

        CancelBTN.setText("Cancelar");
        CancelBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBTNActionPerformed(evt);
            }
        });
        getContentPane().add(CancelBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 516, 100, 31));

        AgregarAnaulBTN.setText("Agregar");
        AgregarAnaulBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAnaulBTNActionPerformed(evt);
            }
        });
        getContentPane().add(AgregarAnaulBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 490, 104, 30));

        añoLBL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        añoLBL.setText("Año referido:");
        getContentPane().add(añoLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, -1, 30));

        añoTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añoTXTActionPerformed(evt);
            }
        });
        getContentPane().add(añoTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, 190, 30));

        setSize(new java.awt.Dimension(800, 569));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IngresoAnualTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresoAnualTXTActionPerformed

    }//GEN-LAST:event_IngresoAnualTXTActionPerformed

    
    
    
    
    //Habilito cuadros de ingresos mensuales
    private void MensualBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MensualBTNActionPerformed
        
        AgregarAnaulBTN.setVisible(false);
        IngresoAnualTXT.setVisible(false);
        AnualLBL.setVisible(false);
        
        mensualLBL.setVisible(true);
        AgregarMensualBTN.setVisible(true);
        IngresoMensualTXT.setVisible(true);
        gasto.setIngresoMen(IngresoMensualTXT.getText());
   
        añoLBL.setVisible(true);
        añoTXT.setVisible(true);

        

    }//GEN-LAST:event_MensualBTNActionPerformed

    
    //CANCEL BTN
    private void CancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBTNActionPerformed
        
        this.dispose();
        sU.setVisible(true);
        

    }//GEN-LAST:event_CancelBTNActionPerformed

    
    
    //Habilito cuadros de ingresos anuales
    private void AnualBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnualBTNActionPerformed
        
        AgregarMensualBTN.setVisible(false);
        IngresoMensualTXT.setVisible(false);        
        mensualLBL.setVisible(false);
        
        AnualLBL.setVisible(true);
        AgregarAnaulBTN.setVisible(true);
        IngresoAnualTXT.setVisible(true);
        gasto.setIngresoAnu(IngresoAnualTXT.getText());

        añoLBL.setVisible(true);
        añoTXT.setVisible(true);

    }//GEN-LAST:event_AnualBTNActionPerformed

    
    
    
    //BOTON DE AGREGAR INGRESO ANUAL
    private void AgregarAnaulBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAnaulBTNActionPerformed
        
        gasto.setAño(añoTXT.getText());
        
        if("".equals(gasto.getAño())){
        JOptionPane.showMessageDialog(null,"Por favor ingresa el año en el que recibes estos ingresos");
        }else{
        
        
        //Elimino los espacion en el String en caso que el usuario los use para ver mejor el numero
        String conEspacio = IngresoAnualTXT.getText();
        String sinEspacio = conEspacio.replace(" ", "");
        gasto.setIngresoAnu(sinEspacio);
        
        logic.IngresoAnual(gasto);
        JOptionPane.showMessageDialog(null, "Se ha agregado el Ingreso Anual!");
        sU.setVisible(true);
        this.dispose();
        }

    }//GEN-LAST:event_AgregarAnaulBTNActionPerformed

    
    
    
    //BOTON DE AGREGAR INGRESO MENSUAL
    private void AgregarMensualBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarMensualBTNActionPerformed

       gasto.setAño(añoTXT.getText());
       
        if("".equals(gasto.getAño())){
        JOptionPane.showMessageDialog(null,"Por favor ingresa el año en el que recibes estos ingresos");
        }else{


        //Elimino los espacios en el String en caso que el usuario los use para ver mejor el numero
        String conEspacio = IngresoMensualTXT.getText();
        String sinEspacio = conEspacio.replace(" ", "");
        gasto.setIngresoMen(sinEspacio);
        
        logic.IngresosMensuales(gasto);
        
        JOptionPane.showMessageDialog(null, "Se ha agregado el Ingreso Mensual!");
        sU.setVisible(true);
        this.dispose();
        }

    }//GEN-LAST:event_AgregarMensualBTNActionPerformed

    private void añoTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añoTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_añoTXTActionPerformed

    private void IngresoMensualTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresoMensualTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IngresoMensualTXTActionPerformed

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
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAnaulBTN;
    private javax.swing.JButton AgregarMensualBTN;
    private javax.swing.JButton AnualBTN;
    private javax.swing.JLabel AnualLBL;
    private javax.swing.JButton CancelBTN;
    private javax.swing.JLabel ImagenLBL;
    private javax.swing.JTextField IngresoAnualTXT;
    private javax.swing.JTextField IngresoMensualTXT;
    private javax.swing.JButton MensualBTN;
    private javax.swing.JLabel TituloLBL;
    private javax.swing.JLabel añoLBL;
    private javax.swing.JTextField añoTXT;
    private javax.swing.JLabel mensualLBL;
    // End of variables declaration//GEN-END:variables
}
