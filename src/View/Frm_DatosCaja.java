/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CajaController;
import Controller.EmpleadoController;
import Controller.FacturaController;
import View.Table.TablaEmpleado;
import View.Table.TableCaja;
import View.Table.TableFactura;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public class Frm_DatosCaja extends javax.swing.JFrame {

    private TableCaja modelo = new TableCaja();
    CajaController cajaController = new CajaController();
    private EmpleadoController empleadoController = new EmpleadoController();
    private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private TableFactura modeloF = new TableFactura();
    private FacturaController facturaController = new FacturaController();

    /**
     * Creates new form Frm_Caja
     */
    public Frm_DatosCaja() {
        initComponents();
        setLocation(760, 50);
        dialogingresos.setBounds(0, 50, 775, 425);
        txtiddatocaja.setVisible(false);
        cargarTableCaja();
        cargarTableIngresos();
        sumadeTotaldeVEntas();
    }

    /**
     * carga la tabla de cajas y el modelo
     */
    private void cargarTableCaja() {
        modelo.setLista(cajaController.listar());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablecajas.getColumnCount(); i++) {
            tablecajas.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tablecajas.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tablecajas.setModel(modelo);
        }
        tablecajas.updateUI();
    }

    /**
     * Carga los detalle de los ingresos
     */
    private void cargarTableIngresos() {
        modeloF.setLista(facturaController.listar());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableingresos.getColumnCount(); i++) {
            tableingresos.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tableingresos.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tableingresos.setModel(modeloF);
        }
        tableingresos.updateUI();
    }

    /**
     * limpia los campos de la ventana caja
     */
    private void limpiarCaja() {
        txtingresos.setText("");
        labelsueldoempleado.setText("");
        labelpreciocaja.setText("");
        cajaController.setCaja(null);
    }

    /**
     * suma de todos los empleados registrados
     *
     * @throws Exception
     */
    private void PagarSueldos() throws Exception {
        Lista<Double> ph = new Lista<>();
        Lista<Double> hrl = new Lista<>();
        Lista<Double> segEmp = new Lista<>();
        Lista<Double> segEmpldr = new Lista<>();
        Lista<Double> gatosEmpleado = new Lista<>();
        //Double suelEmpleado = 0.00;
        Double gastoTotal = 0.00;
        for (int i = 0; i < empleadoController.listar().tamanio(); i++) {
            ph.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "pagoHora"));
            hrl.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "hrsLaborada"));
            segEmp.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "seguroSocialEmpleado"));
            segEmpldr.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "seguroSocialEmpleador"));
        }
        //Gasto totoal Todo empleado
        for (int i = 0; i < empleadoController.listar().tamanio(); i++) {
            gastoTotal = gastoTotal + (((ph.consultarDatoPosicion(i).intValue()
                    * (hrl.consultarDatoPosicion(i).intValue())
                    * (segEmpldr.consultarDatoPosicion(i))) / 100)
                    + ((ph.consultarDatoPosicion(i).intValue()
                    * (hrl.consultarDatoPosicion(i).intValue()))));
        }

        Double s = Math.round(gastoTotal * 1000) / 1000.0;
        labelsueldoempleado.setText(s.toString());
    }

    /**
     * multilplica la quintales por la cantidad
     */
    private void CompraAlimento() {
        Double precioquintal = 30.98;
        if (cbxcantidad.getSelectedItem().equals("0")) {
            labelpreciocaja.setText(String.valueOf(precioquintal * 0));
        } else if (cbxcantidad.getSelectedItem().equals("1")) {
            labelpreciocaja.setText(String.valueOf(precioquintal));
        } else if (cbxcantidad.getSelectedItem().equals("2")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 2)));
        } else if (cbxcantidad.getSelectedItem().equals("3")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 3)));
        } else if (cbxcantidad.getSelectedItem().equals("4")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 4)));
        } else if (cbxcantidad.getSelectedItem().equals("5")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 5)));
        } else if (cbxcantidad.getSelectedItem().equals("6")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 6)));
        } else if (cbxcantidad.getSelectedItem().equals("7")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 7)));
        } else if (cbxcantidad.getSelectedItem().equals("8")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 8)));
        } else if (cbxcantidad.getSelectedItem().equals("9")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 9)));
        } else if (cbxcantidad.getSelectedItem().equals("10")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 10)));
        }
    }

    /**
     * Guarda los registros de la caja
     */
    private void GuardarCaja() {
        if (txtingresos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Double a = Double.parseDouble(labelsueldoempleado.getText().trim());
            Double b = Double.parseDouble(labelpreciocaja.getText().trim());
            Double c = a + b;
            Double ingresos = Double.parseDouble(txtingresos.getText());
            Double egresos = c;
            cajaController.getCaja().setIngresos(ingresos);
            cajaController.getCaja().setEgresos(egresos);
            cajaController.getCaja().setGanancia(ingresos - egresos);
            if (cajaController.Save()) {
                JOptionPane.showMessageDialog(null, "Registro Completo", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiarCaja();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrarse", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableCaja();
        }
    }

    /**
     * Suma de la columana del total de ventas de la tabla de ingresos
     */
    private void sumadeTotaldeVEntas() {
        Double suma = 0.0;
        Double p = 0.0;
        if (tableingresos.getRowCount() > 0) {
            for (int i = 0; i < tableingresos.getRowCount(); i++) {
                p = Double.parseDouble(tableingresos.getValueAt(i, 4).toString());
                suma += p;
            }
            labeltotalventas.setText(suma.toString());
        }
    }

    private void OrdenarGananciasQuicksort() {
        int selec = cbxordenacion.getSelectedIndex();
        if (selec == 0) {
            cargarTableCaja();
        } else if (selec == 1) {
            Lista aux = cajaController.listar();
            aux.ordenarQuicksort(aux, 0, aux.tamanio() - 1);
            modelo.setLista(aux);
            tablecajas.setModel(modelo);
            tablecajas.updateUI();
        }
    }

    private void buscarganarcias() {
        Lista aux = new Lista();
        aux = cajaController.buscarPorGanacias(Double.parseDouble(txtbuscarganancia.getText()), modelo.getLista());
        modelo.setLista(aux);
        tablecajas.setModel(modelo);
        tablecajas.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogingresos = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableingresos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labeltotalventas = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtiddatocaja = new javax.swing.JTextField();
        JPaneltabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecajas = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        txtingresos = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        labelsueldoempleado = new javax.swing.JLabel();
        JPanelalimento = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbxcantidad = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        labelpreciocaja = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtbuscarganancia = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        cbxordenacion = new javax.swing.JComboBox<>();

        jPanel1.setLayout(null);

        tableingresos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableingresos);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(10, 70, 730, 260);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("DETALLE DE INGRESO POR VENTAS");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(220, 20, 350, 24);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Total de ventas:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(530, 360, 100, 16);
        jPanel1.add(labeltotalventas);
        labeltotalventas.setBounds(640, 350, 100, 30);

        javax.swing.GroupLayout dialogingresosLayout = new javax.swing.GroupLayout(dialogingresos.getContentPane());
        dialogingresos.getContentPane().setLayout(dialogingresosLayout);
        dialogingresosLayout.setHorizontalGroup(
            dialogingresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
        );
        dialogingresosLayout.setVerticalGroup(
            dialogingresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel15.setText("CONTROL DE CAJA");
        jPanel8.add(jLabel15);
        jLabel15.setBounds(250, 20, 260, 32);
        jPanel8.add(txtiddatocaja);
        txtiddatocaja.setBounds(20, 30, 50, 24);

        JPaneltabla.setLayout(null);

        tablecajas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablecajas);

        JPaneltabla.add(jScrollPane1);
        jScrollPane1.setBounds(6, 6, 718, 132);

        jButton12.setText("Guardar Cambios");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        JPaneltabla.add(jButton12);
        jButton12.setBounds(300, 290, 130, 24);

        jPanel8.add(JPaneltabla);
        JPaneltabla.setBounds(10, 100, 730, 150);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        jButton14.setText("Ver ingresos");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14);
        jButton14.setBounds(10, 30, 110, 24);
        jPanel2.add(txtingresos);
        txtingresos.setBounds(130, 30, 90, 30);

        jButton10.setText("Pagar Empleado");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(10, 110, 130, 24);

        labelsueldoempleado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelsueldoempleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(labelsueldoempleado);
        labelsueldoempleado.setBounds(160, 110, 90, 24);

        JPanelalimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel17.setText("Tipo Balanceado");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Engorde", "Crecimiento" }));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setText("Cantidad");

        cbxcantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cbxcantidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxcantidadItemStateChanged(evt);
            }
        });

        jLabel19.setText("Quintales");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel20.setText("Precio");

        javax.swing.GroupLayout JPanelalimentoLayout = new javax.swing.GroupLayout(JPanelalimento);
        JPanelalimento.setLayout(JPanelalimentoLayout);
        JPanelalimentoLayout.setHorizontalGroup(
            JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelalimentoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelalimentoLayout.createSequentialGroup()
                        .addComponent(cbxcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19))
                    .addComponent(labelpreciocaja, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        JPanelalimentoLayout.setVerticalGroup(
            JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelalimentoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbxcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(labelpreciocaja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel2.add(JPanelalimento);
        JPanelalimento.setBounds(400, 20, 270, 160);

        jPanel8.add(jPanel2);
        jPanel2.setBounds(10, 290, 720, 200);

        jButton1.setText("Guardar Cambios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);
        jButton1.setBounds(310, 250, 150, 24);

        txtbuscarganancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscargananciaActionPerformed(evt);
            }
        });
        jPanel8.add(txtbuscarganancia);
        txtbuscarganancia.setBounds(340, 60, 120, 30);

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton3);
        jButton3.setBounds(480, 60, 70, 30);

        cbxordenacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ganancias" }));
        cbxordenacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxordenacionItemStateChanged(evt);
            }
        });
        jPanel8.add(cbxordenacion);
        cbxordenacion.setBounds(570, 60, 130, 26);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            PagarSueldos();
        } catch (Exception ex) {
            Logger.getLogger(Frm_DatosCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        dialogingresos.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void cbxcantidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxcantidadItemStateChanged
        CompraAlimento();
    }//GEN-LAST:event_cbxcantidadItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GuardarCaja();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscarganarcias();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtbuscargananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscargananciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscargananciaActionPerformed

    private void cbxordenacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxordenacionItemStateChanged
        OrdenarGananciasQuicksort();
    }//GEN-LAST:event_cbxordenacionItemStateChanged

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
            java.util.logging.Logger.getLogger(Frm_DatosCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_DatosCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_DatosCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_DatosCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_DatosCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelalimento;
    private javax.swing.JPanel JPaneltabla;
    private javax.swing.JComboBox<String> cbxcantidad;
    private javax.swing.JComboBox<String> cbxordenacion;
    private javax.swing.JDialog dialogingresos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelpreciocaja;
    private javax.swing.JLabel labelsueldoempleado;
    private javax.swing.JLabel labeltotalventas;
    private javax.swing.JTable tablecajas;
    private javax.swing.JTable tableingresos;
    private javax.swing.JTextField txtbuscarganancia;
    private javax.swing.JTextField txtiddatocaja;
    private javax.swing.JTextField txtingresos;
    // End of variables declaration//GEN-END:variables
}
