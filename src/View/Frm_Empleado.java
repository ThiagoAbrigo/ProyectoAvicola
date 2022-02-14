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
import lista.Controller.Lista;
import modelo.Persona;

/**
 *
 * @author Home
 */
public class Frm_Empleado extends javax.swing.JDialog {

    private EmpleadoController ec = new EmpleadoController();
    private TablaEmpleado modelo = new TablaEmpleado();
    private TablaEmpleado mdelo2 = new TablaEmpleado();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form Frm_Persona1
     */
    public Frm_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiar();
        cargarTable();

    }

    private void cargarTable() {
        modelo.setLista(ec.listar());
        tablaPersonas.setModel(modelo);
        tablaPersonas.updateUI();
    }

    private void limpiar() {
        ///Persona
        ec.setEmpleado(null);
        labelNombre.setText("");
        labelApellido.setText("");
        labelCedula.setText("");
        txtPagoHora.setText("");
        txtEmpleado.setText("");
        txtEmpleador.setText("");
        txtHoraLaborada.setText("");
        dateContratacion.setDate(null);
        dateSalida.setDate(null);
        txtRolDescripcion.setText("");
        cbxTipoRol.getItemAt(0);

    }

    private boolean verficarEspacios() {
        return (txtEmpleado.getText().trim().length() > 0 && txtEmpleador.getText().trim().length() > 0
                && txtHoraLaborada.getText().trim().length() > 0 && dateContratacion.getDate() != null && 
                txtRolDescripcion.getText().trim().length() > 0);
    }

    private void editar() {
        int seleccionar = cbxTipoRol.getSelectedIndex();
        String dato = cbxTipoRol.getItemAt(seleccionar);
        Date fechaInicio = dateContratacion.getDate();
        Date fechaFin = dateSalida.getDate();
        try {
            if (verficarEspacios()) {
                ec.getEmpleado().setNombre(labelNombre.getText());
                ec.getEmpleado().setApellido(labelApellido.getText());
                ec.getEmpleado().setCedula(labelCedula.getText());
                ec.getEmpleado().setPagoHora(Double.parseDouble(txtPagoHora.getText()));
                ec.getEmpleado().setSeguroSocialEmpleado(Double.parseDouble(txtEmpleado.getText()));
                ec.getEmpleado().setSeguroSocialEmpleador(Double.parseDouble(txtEmpleador.getText()));
                ec.getEmpleado().setHrsLaborada(Double.parseDouble(txtHoraLaborada.getText()));
                ec.getEmpleado().setFechaContratacion(sf.format(fechaInicio));
                if (fechaFin != null) {
                    ec.getEmpleado().setFechaSalida(sf.format(fechaFin));
                } else {
                    ec.getEmpleado().setFechaSalida("");
                }
                ec.getEmpleado().getRol().setCargo(String.valueOf(dato));

                ec.getEmpleado().getRol().setDescripcion(txtRolDescripcion.getText());
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
        } catch (Exception e) {
            System.out.println("Erroe en modificar Empleado " + e);
        }
    }

    public void leer() throws Exception {
        limpiar();
        Date fechaIn = null;
        Date fechaF = null;
        int seleccionar = tablaPersonas.getSelectedRow();
        if (seleccionar >= 0) {

            labelNombre.setText((String) tablaPersonas.getValueAt(seleccionar, 0));
            labelApellido.setText((String) tablaPersonas.getValueAt(seleccionar, 1));
            labelCedula.setText((String) tablaPersonas.getValueAt(seleccionar, 2));
            txtPagoHora.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 3)));
            txtEmpleado.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 4)));
            txtEmpleador.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 5)));
            txtHoraLaborada.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 6)));
            String fechaIni = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 7).toString();
            String fechaFin = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 8).toString();
            cbxTipoRol.setSelectedItem(String.valueOf(tablaPersonas.getValueAt(seleccionar, 9)));
            txtRolDescripcion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 10)));
            fechaIn = sf.parse(fechaIni);
            fechaF = sf.parse(fechaFin);
            dateContratacion.setDate(fechaIn);
            if (fechaFin != null) {
                dateSalida.setDate(fechaF);
            } else {
                ec.getEmpleado().setFechaSalida("");
            }  
        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar fila que desee cambiar", "Error", JOptionPane.ERROR_MESSAGE);
        }

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
        bttEditar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();
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

        cbxTipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Veterinario", "Cuidador", "Vendedor", "Chofer", "Contador" }));
        jPanel3.add(cbxTipoRol);
        cbxTipoRol.setBounds(100, 20, 127, 30);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(100, 90, 130, 10);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(530, 30, 250, 120);

        bttEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEditar.setText("Editar");
        bttEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEditarActionPerformed(evt);
            }
        });
        jPanel2.add(bttEditar);
        bttEditar.setBounds(340, 230, 100, 30);

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
        jScrollPane2.setBounds(10, 280, 760, 320);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(840, 691));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bttEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEditarActionPerformed
        editar();
    }//GEN-LAST:event_bttEditarActionPerformed

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
    private javax.swing.JComboBox<String> cbxTipoRol;
    private com.toedter.calendar.JDateChooser dateContratacion;
    private com.toedter.calendar.JDateChooser dateSalida;
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
    private javax.swing.JTextField txtPagoHora;
    private javax.swing.JTextField txtRolDescripcion;
    // End of variables declaration//GEN-END:variables
}
