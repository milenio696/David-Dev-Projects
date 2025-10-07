package Presentacion;

import Entidades.Gasto;
import LogicaNegocio.LogicaGasto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestionGastos extends javax.swing.JFrame {

    public Gasto gasto = new Gasto();
    public LogicaGasto logic = new LogicaGasto();
   
    private DefaultTableModel tblModel;
    private final String[] titulos = {"#", "Categoria", "Monto", "Fecha", "Descripcion"};

    public GestionGastos() {
        initComponents();
        initTable();
        RegisterTBL.setModel(tblModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AgregarGastoBTN = new javax.swing.JButton();
        EditarGastoBTN = new javax.swing.JButton();
        EliminarGastoBTN = new javax.swing.JButton();
        ResumenGastoBTN = new javax.swing.JButton();
        InformeMensualBTN = new javax.swing.JButton();
        TotalBTN = new javax.swing.JButton();
        InformeAnualBTN = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CategoriaLBL = new javax.swing.JLabel();
        CategoriaBOX = new javax.swing.JComboBox<>();
        MontoLBL = new javax.swing.JLabel();
        MontoTXT = new javax.swing.JTextField();
        FechaLBL = new javax.swing.JLabel();
        FechaTXT = new javax.swing.JTextField();
        DescripcionLBL = new javax.swing.JLabel();
        DescripcionTXT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RegisterTBL = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setPreferredSize(new java.awt.Dimension(1057, 265));
        jPanel1.setLayout(new java.awt.BorderLayout());

        AgregarGastoBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AgregarGastoBTN.setText("Agregar");
        AgregarGastoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastoBTNActionPerformed(evt);
            }
        });
        jPanel3.add(AgregarGastoBTN);

        EditarGastoBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EditarGastoBTN.setText("Editar");
        EditarGastoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarGastoBTNActionPerformed(evt);
            }
        });
        jPanel3.add(EditarGastoBTN);

        EliminarGastoBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EliminarGastoBTN.setText("Eliminar");
        EliminarGastoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarGastoBTNActionPerformed(evt);
            }
        });
        jPanel3.add(EliminarGastoBTN);

        ResumenGastoBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResumenGastoBTN.setText("Mostrar");
        ResumenGastoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResumenGastoBTNActionPerformed(evt);
            }
        });
        jPanel3.add(ResumenGastoBTN);

        InformeMensualBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InformeMensualBTN.setText("Informe Completo");
        InformeMensualBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformeMensualBTNActionPerformed(evt);
            }
        });
        jPanel3.add(InformeMensualBTN);

        TotalBTN.setText("Total Anual");
        TotalBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalBTNActionPerformed(evt);
            }
        });
        jPanel3.add(TotalBTN);

        InformeAnualBTN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        InformeAnualBTN.setText("Cerrar Sesion");
        InformeAnualBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformeAnualBTNActionPerformed(evt);
            }
        });
        jPanel3.add(InformeAnualBTN);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        CategoriaLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CategoriaLBL.setText("Categoria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(9, 10, 1, 10);
        jPanel4.add(CategoriaLBL, gridBagConstraints);

        CategoriaBOX.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CategoriaBOX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccion Pendiente...", "Gastos de vivienda", "Gastos alimenticios", "Transporte", "Entretenimiento", "Educacion", "Gastos personales", "Ahorro e inversiones", "Deudas" }));
        CategoriaBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaBOXActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(13, 12, 5, 12);
        jPanel4.add(CategoriaBOX, gridBagConstraints);

        MontoLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MontoLBL.setText("Monto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 9);
        jPanel4.add(MontoLBL, gridBagConstraints);

        MontoTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MontoTXT.setText("$");
        MontoTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MontoTXTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel4.add(MontoTXT, gridBagConstraints);

        FechaLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FechaLBL.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 9);
        jPanel4.add(FechaLBL, gridBagConstraints);

        FechaTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FechaTXT.setText("dd/mm/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel4.add(FechaTXT, gridBagConstraints);

        DescripcionLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DescripcionLBL.setText("Descripcion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 9);
        jPanel4.add(DescripcionLBL, gridBagConstraints);

        DescripcionTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DescripcionTXT.setText("?");
        DescripcionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescripcionTXTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        jPanel4.add(DescripcionTXT, gridBagConstraints);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion De Gastos"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        RegisterTBL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RegisterTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterTBLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(RegisterTBL);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1051, 765));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MontoTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MontoTXTActionPerformed

    }//GEN-LAST:event_MontoTXTActionPerformed

    private void CategoriaBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaBOXActionPerformed

    }//GEN-LAST:event_CategoriaBOXActionPerformed

    //RESETEO
    public void Reseteo() {

        CategoriaBOX.setSelectedIndex(0);
        MontoTXT.setText("$");
        FechaTXT.setText("dd/mm/yyyy");
        DescripcionTXT.setText("?");
    }

    //BOTONES:
    //EDITAR
    private void EditarGastoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarGastoBTNActionPerformed

        if (RegisterTBL.getSelectedRowCount() != 1) {
            return;
        }

        if (gasto.getMensajeError() == null) {

            gasto.setRow(RegisterTBL.getSelectedRow());

            //Aca almaceno los datos de los box en blanco
            String numTemp = ((String) tblModel.getValueAt(gasto.getRow(), 0));
            gasto.setNumeracion(Integer.parseInt(numTemp));

            gasto.setCategoria(CategoriaBOX.getSelectedItem().toString());
            gasto.setMonto(MontoTXT.getText());
            gasto.setFecha(FechaTXT.getText());
            gasto.setDescripcion(DescripcionTXT.getText());

            logic.Edicion(gasto);


            //con el modelo a ejecutar, agarro los datos obtenidos y seteo cada fila y columna 
            for (Object[] line : gasto.getListaGastos()) {
                tblModel.removeRow(gasto.getRow());             
                tblModel.addRow(line);
            }

            //reseteo informacion por introducir
            Reseteo();

        } else {
            JOptionPane.showMessageDialog(null, gasto.getMensajeError());
        }
    }//GEN-LAST:event_EditarGastoBTNActionPerformed

    //AGREGAR LOS GASTOS AL REGISTRO
    private void AgregarGastoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarGastoBTNActionPerformed

        if (gasto.getMensajeError() == null) {

            //Conecto con entidades gasto
            gasto.setCategoria(CategoriaBOX.getSelectedItem().toString());
            gasto.setMonto(MontoTXT.getText().replace(" ", ""));
            gasto.setFecha(FechaTXT.getText());
            gasto.setDescripcion(DescripcionTXT.getText());

            logic.AgregarGasto(gasto);

            //asigno la linea del row con el array object de entidades gasto
            tblModel.addRow(gasto.getFila());

            //reseteo informacion por introducir
            Reseteo();

        } else {
            JOptionPane.showMessageDialog(null, gasto.getMensajeError());
        }

    }//GEN-LAST:event_AgregarGastoBTNActionPerformed

    //ELIMINAR UNA FILA DEL REGISTRO
    private void EliminarGastoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarGastoBTNActionPerformed

        //"Si se cumple la siguiente condicion, ejecutar el codigo que esta abajo"
        if (RegisterTBL.getSelectedRowCount() != 1) {
            return;
        }

        if (gasto.getMensajeError() == null) {

            gasto.setCategoria((String) tblModel.getValueAt(gasto.getRow(), 1));
            gasto.setMonto((String) tblModel.getValueAt(gasto.getRow(), 2));
            gasto.setFecha((String) tblModel.getValueAt(gasto.getRow(), 3));
            gasto.setDescripcion((String) tblModel.getValueAt(gasto.getRow(), 4));

            logic.Eliminar(gasto);

            //Elimino la fila seleccionada por completo
            tblModel.removeRow(gasto.getRow());

        } else {
            JOptionPane.showMessageDialog(null, gasto.getMensajeError());
        }


    }//GEN-LAST:event_EliminarGastoBTNActionPerformed

    //Mostrar todos los gastos guardados previamente
    private void ResumenGastoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResumenGastoBTNActionPerformed

        if(gasto.getMensajeError()==null){
            
            
        logic.DefaultScreen(gasto);
        
        tblModel.setRowCount(0);
        
        for (Object[] spend : gasto.getListaGastos()) {
            
            tblModel.addRow(spend); }
        }
        
        
        else{
        JOptionPane.showMessageDialog(null, gasto.getMensajeError());
        }
        
        
        
        
        
        
    }//GEN-LAST:event_ResumenGastoBTNActionPerformed

    
    
    private void InformeMensualBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformeMensualBTNActionPerformed

        Resumen r = new Resumen();
        r.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_InformeMensualBTNActionPerformed

    
    
    //DOBLE CLICK A UN GASTO REGISTRADO Y LA INFORMACION SE PONE EN LOS ESPACIOS EN BLANCOS PARA EDITAR
    private void RegisterTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterTBLMouseClicked

        if (evt.getClickCount() == 1) {
            gasto.setRow(RegisterTBL.getSelectedRow());

            //redefiniendo cada valor de la fila seleccionada y alternando la columna correspondiente
            
            String numTemp = ((String) tblModel.getValueAt(gasto.getRow(), 0));
            gasto.setNumeracion(Integer.parseInt(numTemp));
            
            gasto.setCategoria((String) tblModel.getValueAt(gasto.getRow(), 1));
            gasto.setMonto((String) tblModel.getValueAt(gasto.getRow(), 2));
            gasto.setFecha((String) tblModel.getValueAt(gasto.getRow(), 3));
            gasto.setDescripcion((String) tblModel.getValueAt(gasto.getRow(), 4));
        }

        //Cuando clickeo 2 veces una fila
        if (evt.getClickCount() == 2) {
            gasto.setRow(RegisterTBL.getSelectedRow());

            //redefiniendo cada valor de la fila seleccionada y alternando la columna correspondiente
            gasto.setCategoria((String) tblModel.getValueAt(gasto.getRow(), 1));
            gasto.setMonto((String) tblModel.getValueAt(gasto.getRow(), 2));
            gasto.setFecha((String) tblModel.getValueAt(gasto.getRow(), 3));
            gasto.setDescripcion((String) tblModel.getValueAt(gasto.getRow(), 4));

            //redefino espacios del box para editar
            CategoriaBOX.setSelectedItem(gasto.getCategoria());
            MontoTXT.setText(gasto.getMonto().replace(" ", ""));
            FechaTXT.setText(gasto.getFecha());
            DescripcionTXT.setText(gasto.getDescripcion());

        }

    }//GEN-LAST:event_RegisterTBLMouseClicked

    private void DescripcionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescripcionTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescripcionTXTActionPerformed

    private void InformeAnualBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformeAnualBTNActionPerformed

        this.dispose();
        VentanaLogin vt = new VentanaLogin();
        vt.setVisible(true);
        
        

    }//GEN-LAST:event_InformeAnualBTNActionPerformed

    private void TotalBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalBTNActionPerformed
      
         Total t = new Total();
        t.setVisible(true);
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_TotalBTNActionPerformed

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
            java.util.logging.Logger.getLogger(GestionGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarGastoBTN;
    private javax.swing.JComboBox<String> CategoriaBOX;
    private javax.swing.JLabel CategoriaLBL;
    private javax.swing.JLabel DescripcionLBL;
    private javax.swing.JTextField DescripcionTXT;
    private javax.swing.JButton EditarGastoBTN;
    private javax.swing.JButton EliminarGastoBTN;
    private javax.swing.JLabel FechaLBL;
    private javax.swing.JTextField FechaTXT;
    private javax.swing.JButton InformeAnualBTN;
    private javax.swing.JButton InformeMensualBTN;
    private javax.swing.JLabel MontoLBL;
    private javax.swing.JTextField MontoTXT;
    private javax.swing.JTable RegisterTBL;
    private javax.swing.JButton ResumenGastoBTN;
    private javax.swing.JButton TotalBTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void initTable() {

        //seteo el formato del model, la linea inicial y que no se pueda editar 
        tblModel = new DefaultTableModel(titulos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        RegisterTBL.setModel(tblModel);

    }
}
