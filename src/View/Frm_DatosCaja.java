/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CajaController;
import Controller.EmpleadoController;
import View.Table.TablaEmpleado;
import View.Table.TableCaja;
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

    /**
     * Creates new form Frm_Caja
     */
    public Frm_DatosCaja() {
        initComponents();
        setLocationRelativeTo(null);
        jDialog1.setSize(870, 440);
        jDialog1.setLocationRelativeTo(null);
        JPanelPagarSueldo.setVisible(false);
        JPanelalimento.setVisible(false);
        JpanelCapital.setVisible(false);
        txtiddatocaja.setVisible(false);
        cargarTableCaja();
        cargarTableEmpleados();
    }

    /**
     * Muestra paneles segun el cbxcontrol
     */
    private void mostrarPaneles() {
        if (cbxcontrol.getSelectedItem().equals("Agregar Capital")) {
            JpanelCapital.setVisible(true);
            JPanelPagarSueldo.setVisible(false);
            JPanelalimento.setVisible(false);
        } else if (cbxcontrol.getSelectedItem().equals("Pagar Sueldos")) {
            JpanelCapital.setVisible(false);
            JPanelPagarSueldo.setVisible(true);
            JPanelalimento.setVisible(false);
        } else if (cbxcontrol.getSelectedItem().equals("Alimentos")) {
            JpanelCapital.setVisible(false);
            JPanelPagarSueldo.setVisible(false);
            JPanelalimento.setVisible(true);
        }
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
     * limpia los campos
     */
    private void limpiarCaja() {
        txtcapital.setText("");
        txtsueldoempleado.setText("");
    }

    /**
     * Cargar lista de empleados
     */
    private void cargarTableEmpleados() {
        modelEmpleado.setLista(empleadoController.listar());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaempleadoslista.getColumnCount(); i++) {
            tablaempleadoslista.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tablaempleadoslista.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tablaempleadoslista.setModel(modelEmpleado);
        }
        tablaempleadoslista.updateUI();
    }

    private void enviardatos() {
        try {
            int fila = tablaempleadoslista.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un dato de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                labelempleado.setText(tablaempleadoslista.getValueAt(fila, 0).toString());
            }
        } catch (Exception e) {
        }
    }

    private void AgregarCapital() {
        if (txtcapital.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "campo vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            cajaController.getCaja().setPresupuesto_Actual(Double.parseDouble(txtcapital.getText()));
            JOptionPane.showMessageDialog(null, "Capital registrado", "Ok", JOptionPane.INFORMATION_MESSAGE);
        }
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
//            gastoTotal = gastoTotal + ((((ph.consultarDatoPosicion(i)hrl.consultarDatoPosicion(i))(segEmpldr.consultarDatoPosicion(i)))/100)
//                    +((ph.consultarDatoPosicion(i)*hrl.consultarDatoPosicion(i))));
            ////suelEmpleado = suelEmpleado +((ph.consultarDatoPosicion(i)*hrl.consultarDatoPosicion(i)));////
        }
    }

    private void CompraAlimento() {
        Double precioquintal = 30.98;
        if (cbxcantidad.getSelectedItem().equals("1")) {
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
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtiddatocaja = new javax.swing.JTextField();
        JpanelCapital = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtcapital7 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        cbxcontrol = new javax.swing.JComboBox<>();
        JPanelPagarSueldo = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        txtsueldoempleado = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        labelempleado = new javax.swing.JLabel();
        JPanelalimento = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbxcantidad = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        labelpreciocaja = new javax.swing.JLabel();
        JPaneltabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablecajas = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel15.setText("CONTROL DE CAJA");
        jPanel8.add(jLabel15);
        jLabel15.setBounds(250, 20, 260, 32);
        jPanel8.add(txtiddatocaja);
        txtiddatocaja.setBounds(20, 30, 50, 24);

        JpanelCapital.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JpanelCapital.setLayout(null);

        jLabel16.setText("Agregar Capital:");
        JpanelCapital.add(jLabel16);
        jLabel16.setBounds(20, 30, 110, 16);
        JpanelCapital.add(txtcapital7);
        txtcapital7.setBounds(20, 60, 74, 24);

        jButton8.setText("Agregar");
        JpanelCapital.add(jButton8);
        jButton8.setBounds(130, 40, 70, 24);

        jPanel8.add(JpanelCapital);
        JpanelCapital.setBounds(10, 340, 210, 120);

        cbxcontrol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Agregar Capital", "Pagar Sueldos", "Alimentos" }));
        cbxcontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxcontrolActionPerformed(evt);
            }
        });
        jPanel8.add(cbxcontrol);
        cbxcontrol.setBounds(300, 70, 140, 26);

        JPanelPagarSueldo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton9.setText("Empleado");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Pagar");

        labelempleado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelempleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout JPanelPagarSueldoLayout = new javax.swing.GroupLayout(JPanelPagarSueldo);
        JPanelPagarSueldo.setLayout(JPanelPagarSueldoLayout);
        JPanelPagarSueldoLayout.setHorizontalGroup(
            JPanelPagarSueldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPagarSueldoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelPagarSueldoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(txtsueldoempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(JPanelPagarSueldoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelempleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPanelPagarSueldoLayout.setVerticalGroup(
            JPanelPagarSueldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPagarSueldoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(JPanelPagarSueldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsueldoempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        jPanel8.add(JPanelPagarSueldo);
        JPanelPagarSueldo.setBounds(230, 340, 230, 120);

        JPanelalimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Tipo Balanceado");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Engorde", "Crecimiento" }));

        jLabel18.setText("Cantidad");

        cbxcantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel19.setText("Quintales");

        jLabel20.setText("Precio");

        jButton11.setText("Pagar");

        javax.swing.GroupLayout JPanelalimentoLayout = new javax.swing.GroupLayout(JPanelalimento);
        JPanelalimento.setLayout(JPanelalimentoLayout);
        JPanelalimentoLayout.setHorizontalGroup(
            JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelalimentoLayout.createSequentialGroup()
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(labelpreciocaja, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPanelalimentoLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
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
                .addGroup(JPanelalimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPanelalimentoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel20))
                    .addGroup(JPanelalimentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelpreciocaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel8.add(JPanelalimento);
        JPanelalimento.setBounds(480, 340, 260, 160);

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

        javax.swing.GroupLayout JPaneltablaLayout = new javax.swing.GroupLayout(JPaneltabla);
        JPaneltabla.setLayout(JPaneltablaLayout);
        JPaneltablaLayout.setHorizontalGroup(
            JPaneltablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPaneltablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPaneltablaLayout.setVerticalGroup(
            JPaneltablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPaneltablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.add(JPaneltabla);
        JPaneltabla.setBounds(10, 100, 730, 180);

        jButton12.setText("Guardar Cambios");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton12);
        jButton12.setBounds(300, 290, 130, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxcontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxcontrolActionPerformed
        mostrarPaneles();
    }//GEN-LAST:event_cbxcontrolActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //jDialog1.setBounds(0, 0, 870, 450);
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        enviardatos();
        jDialog1.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

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
    private javax.swing.JPanel JPanelPagarSueldo;
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JPanel JPanelPrincipal1;
    private javax.swing.JPanel JPanelPrincipal2;
    private javax.swing.JPanel JPanelPrincipal3;
    private javax.swing.JPanel JPanelPrincipal4;
    private javax.swing.JPanel JPanelPrincipal5;
    private javax.swing.JPanel JPanelPrincipal6;
    private javax.swing.JPanel JPanelalimento;
    private javax.swing.JPanel JPaneltabla;
    private javax.swing.JPanel JpanelCapital;
    private javax.swing.JComboBox<String> cbxcantidad;
    private javax.swing.JComboBox<String> cbxcontrol;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelempleado;
    private javax.swing.JLabel labelpreciocaja;
    private javax.swing.JTable tablaempleadoslista;
    private javax.swing.JTable tablecajas;
    private javax.swing.JTextField txtcapital;
    private javax.swing.JTextField txtcapital1;
    private javax.swing.JTextField txtcapital2;
    private javax.swing.JTextField txtcapital3;
    private javax.swing.JTextField txtcapital4;
    private javax.swing.JTextField txtcapital5;
    private javax.swing.JTextField txtcapital6;
    private javax.swing.JTextField txtcapital7;
    private javax.swing.JTextField txtidcaja;
    private javax.swing.JTextField txtidcaja1;
    private javax.swing.JTextField txtidcaja2;
    private javax.swing.JTextField txtidcaja3;
    private javax.swing.JTextField txtidcaja4;
    private javax.swing.JTextField txtidcaja5;
    private javax.swing.JTextField txtidcaja6;
    private javax.swing.JTextField txtiddatocaja;
    private javax.swing.JTextField txtsueldoempleado;
    // End of variables declaration//GEN-END:variables
}
