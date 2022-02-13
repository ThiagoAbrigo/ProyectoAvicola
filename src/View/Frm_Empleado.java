/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EmpleadoController;
import View.Table.TablaEmpleado;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class Frm_Empleado extends javax.swing.JDialog {

    public EmpleadoController ec = new EmpleadoController();
    private TablaEmpleado modelo = new TablaEmpleado();
    private TablaEmpleado mdelo2 = new TablaEmpleado();

    /**
     * Creates new form Frm_Persona1
     */
    public Frm_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiar();
        cargarTable();
        txtId.setVisible(true);
    }

    private void cargarTable() {
        modelo.setLista(ec.listar());
        tablaPersonas.setModel(modelo);
        tablaPersonas.updateUI();
    }

    private void limpiar() {
        ///Persona
//        ec.setEmpleado(null);
//        txtId.setText("");
//        txtNombre.setText("");
//        txtApellido.setText("");
//        txtCadula.setText("");
//        txtCelular.setText("");
//        //txtCorreo.setText("");
//        txtDirecion.setText("");
//        txtPassword.setText("");
//        ///Roles
//        cbxTipoRol.setSelectedIndex(0);
//        txtRolDescripcion.setText("");

    }

    private boolean verficarEspacios() {
//        return (txtNombre.getText().trim().length() > 0 && txtCadula.getText().trim().length() > 0
//                && txtCelular.getText().trim().length() > 0 
//                && txtDirecion.getText().trim().length() > 0 && txtRolDescripcion.getText().trim().length() > 0);
        return true;
    }

    private void editar() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaInicio = dateContratacion.getDate();
            Date fechaFin = dateContratacion.getDate();
            
            if (dateContratacion != null) {
                    System.out.println(sf.format(fechaInicio));
                } else {
                    System.out.println("no hay fecha fin");
                }
            
        if (verficarEspacios()) {
            ec.getEmpleado().setId(Integer.valueOf(txtId.getText()));
//            ec.getEmpleado().setNombre(txtNombre.getText());
//            ec.getEmpleado().setApellido(txtApellido.getText());
//            ec.getEmpleado().setId(Integer.valueOf(txtId.getText()));
//                    ec.getEmpleado().setNombre(txtNombre.getText());
//                    ec.getEmpleado().setApellido(txtApellido.getText());
//                    ec.getEmpleado().setCedula(txtCadula.getText());
            ec.getEmpleado().setPagoHora(0.00);
            ec.getEmpleado().setSeguroSocialEmpleado(0.00);
            ec.getEmpleado().setSeguroSocialEmpleador(0.00);
            ec.getEmpleado().setHrsLaborada(0.0);
            ec.getEmpleado().setFechaContratacion("No hay");
            ec.getEmpleado().setFechaSalida("No hay");

            ec.getEmpleado().getRol().setCargo("No asignado");
            ec.getEmpleado().getRol().setDescripcion("No asignado");
            if (ec.Update()) {
                JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTable();

        } else {
            JOptionPane.showMessageDialog(null, "LLenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    int tipoOrden = 0;

    public void ordenar() {
        //System.out.println(cbxOrdenar.getSelectedIndex());
        int cbxOrden = cbxOrdenar.getSelectedIndex();
        try {
            ec.setLisEmpleado(null);
            mdelo2.setLista(ec.getLisEmpleado());
            tablaPersonas.updateUI();
            cargarTable();
            if (tipoOrden == 0) {
                if (cbxOrden == 0) {
                    ec.getLisEmpleado().seleccion_clase("nombre", 1);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 1) {
                    ec.getLisEmpleado().seleccion_clase("apellido", 1);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 2) {
                    ec.getLisEmpleado().seleccion_clase("cedula", 1);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                ////////////
                tipoOrden = 1;
            } else {
                if (cbxOrden == 0) {
                    ec.getLisEmpleado().seleccion_clase("nombre", 2);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 1) {
                    ec.getLisEmpleado().seleccion_clase("apellido", 2);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 2) {
                    ec.getLisEmpleado().seleccion_clase("cedula", 2);
                    mdelo2.setLista(ec.getLisEmpleado());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                ////////////////
                tipoOrden = 0;
            }

        } catch (Exception e) {
            System.out.println("erro ordenar " + e);
        }
    }

    public void leer() throws Exception {
            limpiar();
            int seleccionar = tablaPersonas.getSelectedRow();
            if (seleccionar >= 0) {
                //String.valueOf(tablaPersonas.getValueAt(seleccionar, 2))
                labelNombre.setText((String) tablaPersonas.getValueAt(seleccionar, 0));
                labelApellido.setText((String) tablaPersonas.getValueAt(seleccionar, 1));
                labelCedula.setText((String) tablaPersonas.getValueAt(seleccionar, 2));
                txtPagoHora.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 3)));
                txtEmpleado.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 4)));
                txtEmpleador.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 5)));
                txtHoraLaborada.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 6)));
                txtRolDescripcion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 7)));
                cbxTipoRol.setSelectedItem(String.valueOf(tablaPersonas.getValueAt(seleccionar, 8)));
                //cbxTipoRol.setSelectedIndex(2);
                String tipo = cbxTipoRol.getItemAt(1);
                System.out.println("tipos "+tipo);
                
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar fila que desee cambiar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        

    }

    public void prueba() throws Exception {
        
        for (int i = 0; i < ec.listar().tamanio(); i++) {
            ec.listar().value(ec.listar().consultarDatoPosicion(i), "hrsLaborada");
        }
      
       // System.out.println("dato "+tablaPersonas.getValueAt(1, 6));
        //System.out.println("Empleado "+ ec.listar().value(ec.listar().consultarDatoPosicion(1), ec.getEmpleado().getClass().getName()));
        //txtId.setText(String.valueOf(ec.getLisEmpleado().value(ec.getLisEmpleado().consultarDatoPosicion(1), "id")));
        /////////////////
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
//        Date fecha = dateContratacion.getDate();
//        String ff = sf.format(fecha);
//        System.out.println("fecha " + ff);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelCedula = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRolDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxTipoRol = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bttEditar = new javax.swing.JButton();
        bttLeer = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();
        cbxOrdenar = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelApellido = new javax.swing.JLabel();
        txtHoraLaborada = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtEmpleador = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        txtPagoHora = new javax.swing.JTextField();
        dateSalida = new com.toedter.calendar.JDateChooser();
        dateContratacion = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        labelCedula.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelCedula.setText("xxxxxxxxxxxxxxxxxx");
        jPanel2.add(labelCedula);
        labelCedula.setBounds(90, 100, 160, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cédula:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 100, 50, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Seguro Empleado");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(270, 100, 120, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Pago por hora");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 140, 100, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Apellido:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 70, 60, 20);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rol", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tipo");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 26, 15);

        txtRolDescripcion.setBorder(null);
        jPanel3.add(txtRolDescripcion);
        txtRolDescripcion.setBounds(100, 60, 127, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Descripción:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(9, 70, 80, 15);

        cbxTipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", " " }));
        jPanel3.add(cbxTipoRol);
        cbxTipoRol.setBounds(100, 20, 127, 30);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(100, 90, 130, 10);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(530, 30, 250, 120);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(650, 270, 73, 20);
        jPanel2.add(txtId);
        txtId.setBounds(10, 10, 59, 20);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Limpiar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(410, 250, 115, 30);

        bttEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEditar.setText("Editar");
        bttEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEditarActionPerformed(evt);
            }
        });
        jPanel2.add(bttEditar);
        bttEditar.setBounds(150, 250, 100, 30);

        bttLeer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttLeer.setText("Leer");
        bttLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLeerActionPerformed(evt);
            }
        });
        jPanel2.add(bttLeer);
        bttLeer.setBounds(280, 250, 90, 30);

        jButton2.setText("Ordenar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(150, 300, 73, 23);

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPersonasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPersonas);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 380, 750, 220);

        cbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Cedula" }));
        cbxOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenarActionPerformed(evt);
            }
        });
        jPanel2.add(cbxOrdenar);
        cbxOrdenar.setBounds(20, 300, 90, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Nombre:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(10, 40, 60, 20);

        labelNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre.setText("xxxxxxxxxxxxxxxxxx");
        jPanel2.add(labelNombre);
        labelNombre.setBounds(90, 40, 160, 20);

        labelApellido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelApellido.setText("xxxxxxxxxxxxxxxxxx");
        jPanel2.add(labelApellido);
        labelApellido.setBounds(90, 70, 160, 20);

        txtHoraLaborada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraLaboradaActionPerformed(evt);
            }
        });
        jPanel2.add(txtHoraLaborada);
        txtHoraLaborada.setBounds(400, 140, 110, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Hora laboradas");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(280, 140, 120, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Fecha Salida");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(370, 190, 80, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Seguro Empleador");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(270, 60, 120, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Fecha Contratacion");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(10, 190, 120, 20);

        txtEmpleador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadorActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmpleador);
        txtEmpleador.setBounds(400, 50, 110, 30);

        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmpleado);
        txtEmpleado.setBounds(400, 100, 110, 30);

        txtPagoHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagoHoraActionPerformed(evt);
            }
        });
        jPanel2.add(txtPagoHora);
        txtPagoHora.setBounds(120, 130, 110, 30);
        jPanel2.add(dateSalida);
        dateSalida.setBounds(470, 190, 150, 30);
        jPanel2.add(dateContratacion);
        dateContratacion.setBounds(160, 190, 150, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 790, 620);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(823, 691));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEditarActionPerformed
        editar();
    }//GEN-LAST:event_bttEditarActionPerformed

    private void bttLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLeerActionPerformed
        try {
            leer();
        } catch (Exception ex) {
            Logger.getLogger(Frm_Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bttLeerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            prueba();
            
            //cargarTable();
        } catch (Exception ex) {
            Logger.getLogger(Frm_Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ordenar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenarActionPerformed

    private void txtHoraLaboradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraLaboradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraLaboradaActionPerformed

    private void txtEmpleadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadorActionPerformed

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed

    private void txtPagoHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagoHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagoHoraActionPerformed

    private void tablaPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonasMouseClicked
        try {
            // TODO add your handling code here
            leer();
        } catch (Exception ex) {
            Logger.getLogger(Frm_Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaPersonasMouseClicked

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
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Empleado dialog = new Frm_Empleado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttEditar;
    private javax.swing.JButton bttLeer;
    private javax.swing.JComboBox<String> cbxOrdenar;
    private javax.swing.JComboBox<String> cbxTipoRol;
    private com.toedter.calendar.JDateChooser dateContratacion;
    private com.toedter.calendar.JDateChooser dateSalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelCedula;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtEmpleador;
    private javax.swing.JTextField txtHoraLaborada;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPagoHora;
    private javax.swing.JTextField txtRolDescripcion;
    // End of variables declaration//GEN-END:variables
}
