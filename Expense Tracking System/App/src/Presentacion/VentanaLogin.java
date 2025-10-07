package Presentacion;

import Entidades.Login;
import LogicaNegocio.LogicaLogin;
import javax.swing.JOptionPane;


public class VentanaLogin extends javax.swing.JFrame {


    public VentanaLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accessLoginBtn_1 = new javax.swing.JButton();
        loginLbl_1 = new javax.swing.JLabel();
        usrLbl_1 = new javax.swing.JLabel();
        passLbl_1 = new javax.swing.JLabel();
        pass_1 = new javax.swing.JPasswordField();
        usr_1 = new javax.swing.JTextField();
        LoginImageLBL = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BienvenidoLBL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accessLoginBtn_1.setBackground(new java.awt.Color(0, 102, 102));
        accessLoginBtn_1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        accessLoginBtn_1.setForeground(new java.awt.Color(255, 255, 255));
        accessLoginBtn_1.setText("Iniciar Sesion");
        accessLoginBtn_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accessLoginBtn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessLoginBtn_1ActionPerformed(evt);
            }
        });
        getContentPane().add(accessLoginBtn_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, 360, 40));

        loginLbl_1.setFont(new java.awt.Font("Segoe UI Variable", 3, 42)); // NOI18N
        loginLbl_1.setForeground(new java.awt.Color(0, 102, 102));
        loginLbl_1.setText("Login");
        getContentPane().add(loginLbl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 120, 60));

        usrLbl_1.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        usrLbl_1.setText("Nombre de usuario o username:");
        getContentPane().add(usrLbl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, -1, 20));

        passLbl_1.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        passLbl_1.setText("Contraseña:");
        getContentPane().add(passLbl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, -1, -1));

        pass_1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pass_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_1ActionPerformed(evt);
            }
        });
        getContentPane().add(pass_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, 360, 40));

        usr_1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usr_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usr_1ActionPerformed(evt);
            }
        });
        getContentPane().add(usr_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 360, 40));

        LoginImageLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoLogin.png"))); // NOI18N
        LoginImageLBL.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(LoginImageLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 740));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrarse");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 600, 350, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        jLabel2.setText("Aun no tienes cuenta? Registrate con nosotros!");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 570, -1, 30));

        BienvenidoLBL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        BienvenidoLBL.setText("Bienvenido al control total de tu economia!");
        getContentPane().add(BienvenidoLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 300, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Por favor inicia sesion para poder empezar con la administracion de tus gastos.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, -1, -1));

        setSize(new java.awt.Dimension(1302, 740));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void accessLoginBtn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accessLoginBtn_1ActionPerformed

        //objetos
        Login objLogin = new Login();
        LogicaLogin objLogicaLogin = new LogicaLogin();
        

        //asigno variables de reconocimiento de usuario
        objLogin.setUsername(this.usr_1.getText());

        char[] password = this.pass_1.getPassword();
        String passwordString = new String(password);
        objLogin.setPass(passwordString);

        //valido la informacion del login
        objLogicaLogin.Login(objLogin);
        if (objLogin.isAcceso()) {

            this.setVisible(false);
            SeleccionUtilidad sU = new SeleccionUtilidad();
            
             JOptionPane.showMessageDialog(null,"Por favor asegura de agregar los Ingresos antes que nada!");
            sU.setVisible(objLogin.isAcceso());
           

        } else {
            JOptionPane.showMessageDialog(null, "El usuario o la contrasena ingresada son incorrectas, intentalo de nuevo por favor");
        }
        
    }//GEN-LAST:event_accessLoginBtn_1ActionPerformed

    private void pass_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_1ActionPerformed

    private void usr_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usr_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usr_1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        Registro rr = new Registro();
        rr.setVisible(true);
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     *
     * @param args
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
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VentanaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BienvenidoLBL;
    private javax.swing.JLabel LoginImageLBL;
    private javax.swing.JButton accessLoginBtn_1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel loginLbl_1;
    private javax.swing.JLabel passLbl_1;
    private javax.swing.JPasswordField pass_1;
    private javax.swing.JLabel usrLbl_1;
    private javax.swing.JTextField usr_1;
    // End of variables declaration//GEN-END:variables
}
