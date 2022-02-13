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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    private TablaEmpleado modelEmpleado = new TablaEmpleado();
    private TableFactura modeloF = new TableFactura();
    private FacturaController facturaController = new FacturaController();

    /**
     * Creates new form Frm_Caja
     */
    public Frm_DatosCaja() {
        initComponents();
        setLocation(760, 50);
        jDialog1.setSize(870, 440);
        jDialog1.setLocationRelativeTo(null);
        dialogingresos.setBounds(0, 50, 775, 380);
        txtiddatocaja.setVisible(false);
        cargarTableCaja();
        cargarTableIngresos();
    }

    /**
     * carga la tabla de cajas
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
    private void cargarTableIngresos(){
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
     * limpia los campos
     */
    private void limpiarCaja() {
        txtingresos.setText("");
        labelsueldoempleado.setText("");
        labelpreciocaja.setText("");
    }

    private void AgregarCapital() {
//        if (txtcapital.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "campo vacio", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            cajaController.getCaja().setPresupuesto_Actual(Double.parseDouble(txtcapital.getText()));
//            JOptionPane.showMessageDialog(null, "Capital registrado", "Ok", JOptionPane.INFORMATION_MESSAGE);
//        }
    }

    private void PagarSueldos() throws Exception {
        Lista<Double> ph = new Lista<>();
        Lista<Double> hrl = new Lista<>();
        Lista<Double> segEmp = new Lista<>();
        Lista<Double> segEmpldr = new Lista<>();
        Lista<Double> gatosEmpleado = new Lista<>();
        //Double suelEmpleado = 0.00;
        Double gastoTotal = 0.00;
        for (int i = 0; i < empleadoController.listar().tamanio(); i++) {
            ph.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "pagoHora")) ;
            hrl.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "hrsLaborada")) ;
            segEmp.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "seguroSocialEmpleado")) ;
            segEmpldr.insertarNodo((Double) empleadoController.getLisEmpleado().value(empleadoController.getLisEmpleado().consultarDatoPosicion(i), "seguroSocialEmpleador")) ;
        }
        //Gasto totoal Todo empleado
        for (int i = 0; i < empleadoController.listar().tamanio(); i++) {
            gastoTotal = gastoTotal + (((ph.consultarDatoPosicion(i).intValue() 
                    * (hrl.consultarDatoPosicion(i).intValue()) 
                    * (segEmpldr.consultarDatoPosicion(i)))/100) 
                    + ((ph.consultarDatoPosicion(i).intValue() 
                    * (hrl.consultarDatoPosicion(i).intValue()))));
        }
        DecimalFormatSymbols sperador = new DecimalFormatSymbols();
        sperador.setDecimalSeparator('.');
        DecimalFormat format1 = new DecimalFormat("#.00", sperador);
        String sueldo = String.valueOf(format1.format(gastoTotal));
        Double s = Double.parseDouble(sueldo);
        labelsueldoempleado.setText(s.toString());
    }

    private void CompraAlimento() {
        Double precioquintal = 30.98;
        if (cbxcantidad.getSelectedItem().equals("0")) {
            labelpreciocaja.setText(String.valueOf(precioquintal * 0)+" dolares");
        } else if (cbxcantidad.getSelectedItem().equals("1")) {
            labelpreciocaja.setText(String.valueOf(precioquintal)+" dolares");
        } else if (cbxcantidad.getSelectedItem().equals("2")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 2)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("3")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 3)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("4")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 4)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("5")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 5)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("6")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 6)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("7")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 7)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("8")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 8)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("9")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 9)+" dolares"));
        } else if (cbxcantidad.getSelectedItem().equals("10")) {
            labelpreciocaja.setText(String.valueOf((precioquintal * 10)+" dolares"));
        }
    }
    
    private void GuardarCaja(){
        DecimalFormatSymbols sperador = new DecimalFormatSymbols();
        sperador.setDecimalSeparator('.');
        DecimalFormat format2 = new DecimalFormat("#.00", sperador);
        Double ingresos = Double.parseDouble(txtingresos.getText());
        Double egresos = Double.parseDouble(labelsueldoempleado.getText()) + Double.parseDouble(labelpreciocaja.getText());
        cajaController.getCaja().setIngresos(ingresos);
        cajaController.getCaja().setEgresos(egresos);
        cajaController.getCaja().setGanancia(ingresos - egresos);
        if (cajaController.Save()) {
            JOptionPane.showMessageDialog(null, "Registro Completo", "Ok", JOptionPane.INFORMATION_MESSAGE);
            limpiarCaja();
        }else{
            JOptionPane.showMessageDialog(null, "Error al registra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTableCaja();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaempleadoslista = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        dialogingresos = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableingresos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel16 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        txtcapital7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setText("LISTA DE EMPLEADOS");

        tablaempleadoslista.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaempleadoslista);

        jButton13.setText("ENVIAR");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(760, 760, 760)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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

        javax.swing.GroupLayout dialogingresosLayout = new javax.swing.GroupLayout(dialogingresos.getContentPane());
        dialogingresos.getContentPane().setLayout(dialogingresosLayout);
        dialogingresosLayout.setHorizontalGroup(
            dialogingresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogingresosLayout.setVerticalGroup(
            dialogingresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jButton14.setBounds(10, 10, 110, 24);
        jPanel2.add(txtingresos);
        txtingresos.setBounds(130, 10, 90, 30);

        jButton10.setText("Pagar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(10, 70, 75, 24);

        labelsueldoempleado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelsueldoempleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(labelsueldoempleado);
        labelsueldoempleado.setBounds(130, 70, 90, 24);

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
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelalimentoLayout.createSequentialGroup()
                        .addComponent(cbxcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19))
                    .addComponent(labelpreciocaja, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel16.setText("Agregar Capital:");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(10, 120, 110, 16);

        jButton8.setText("Agregar");
        jPanel2.add(jButton8);
        jButton8.setBounds(140, 170, 70, 24);
        jPanel2.add(txtcapital7);
        txtcapital7.setBounds(130, 114, 90, 30);

        jPanel8.add(jPanel2);
        jPanel2.setBounds(10, 290, 720, 220);

        jButton1.setText("Guardar Cambios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);
        jButton1.setBounds(310, 250, 120, 24);

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
            .addGap(0, 532, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

    }//GEN-LAST:event_jButton13ActionPerformed

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
    private javax.swing.JDialog dialogingresos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelpreciocaja;
    private javax.swing.JLabel labelsueldoempleado;
    private javax.swing.JTable tablaempleadoslista;
    private javax.swing.JTable tablecajas;
    private javax.swing.JTable tableingresos;
    private javax.swing.JTextField txtcapital7;
    private javax.swing.JTextField txtiddatocaja;
    private javax.swing.JTextField txtingresos;
    // End of variables declaration//GEN-END:variables
}
