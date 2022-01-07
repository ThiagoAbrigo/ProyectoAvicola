/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GalponController;
import View.Table.TableGalpon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author Home
 */
public class Frm_Galpon extends javax.swing.JFrame {

    public GalponController galponController = new GalponController();
    private TableGalpon modelo = new TableGalpon();

    /**
     * Creates new form Frm_Galpon
     */
    public Frm_Galpon() {
        super("Registro de Galpones");
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        limpiardatos();
        poputTable();
        cargarTable();
    }

    private void cargarTable() {
        modelo.setLista(galponController.listar());
        tablegalpones.setModel(modelo);
        tablegalpones.updateUI();
    }

    private void seleccionar() {
        int fila = tablegalpones.getSelectedRow();
        try {
            String id, numpollo, raza, peso, muestra;
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un dato de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                numpollo = tablegalpones.getValueAt(fila, 1).toString();
                raza = tablegalpones.getValueAt(fila, 2).toString();
                peso = tablegalpones.getValueAt(fila, 3).toString();
                muestra = tablegalpones.getValueAt(fila, 4).toString();
                txtnumeropollo.setText(numpollo);
                txtraza.setText(raza);
                txtpeso.setText(peso);
                txtmuestra.setText(muestra);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void poputTable() {
        JPopupMenu popuMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Aplicar Vacuna", new ImageIcon(getClass().getResource("/Image/vacuna.png")));
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frm_Vacunas vacunas = new Frm_Vacunas();
                int fila = tablegalpones.getSelectedRow();
                vacunas.setVisible(true);
                dispose();
                Frm_Vacunas.txtnum.setText(tablegalpones.getValueAt(fila, 0).toString());
            }
        });
        popuMenu.add(menuItem1);
        tablegalpones.setComponentPopupMenu(popuMenu);
    }

    private void limpiardatos() {
        txtnumeropollo.setText("");
        txtraza.setText("");
        txtpeso.setText("");
        txtmuestra.setText("");
        galponController.setGalpon(null);
        cargarTable();
    }
    private void guardar() {
        if (txtnumeropollo.getText().trim().isEmpty() || txtraza.getText().trim().isEmpty()
                || txtpeso.getText().trim().isEmpty() || txtmuestra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            galponController.getGalpon().setNumPollo(txtnumeropollo.getText());
            galponController.getGalpon().setRaza(txtraza.getText());
            galponController.getGalpon().setPeso(txtpeso.getText());
            galponController.getGalpon().setMuestra(txtmuestra.getText());
            if (galponController.getGalpon().getId() == null) {
                if (galponController.Save()) {
                    JOptionPane.showMessageDialog(null, "Se guardo` correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                    limpiardatos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            //ciudadController.getCiudad().setId(new Long(ciudadController.listar().tamanio() + 1));
        }
    }
    private void Updategalpon(){
//        if (txtnumeropollo.getText().trim().isEmpty() || txtraza.getText().trim().isEmpty()
//                || txtpeso.getText().trim().isEmpty() || txtmuestra.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
            galponController.getGalpon().setNumPollo(txtnumeropollo.getText());
            galponController.getGalpon().setRaza(txtraza.getText());
            galponController.getGalpon().setPeso(txtpeso.getText());
            galponController.getGalpon().setMuestra(txtmuestra.getText());
            if (galponController.getGalpon().getId() == null) {
                if (galponController.Update()) {
                    JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                    limpiardatos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            //}
            //ciudadController.getCiudad().setId(new Long(ciudadController.listar().tamanio() + 1));
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
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablegalpones = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnumeropollo = new javax.swing.JTextField();
        txtmuestra = new javax.swing.JTextField();
        txtpeso = new javax.swing.JTextField();
        txtraza = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialog1.setFont(new java.awt.Font("Agency FB", 0, 12)); // NOI18N
        jDialog1.setSize(new java.awt.Dimension(482, 441));
        jDialog1.getContentPane().setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 204, 0));
        jPanel5.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel6.setText("LISTA DE GALPONES");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(100, 20, 210, 16);

        tablegalpones.setBackground(new java.awt.Color(255, 255, 204));
        tablegalpones.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        tablegalpones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablegalpones);

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 380, 240);

        jButton6.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/enviar.png"))); // NOI18N
        jButton6.setText("Enviar");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(300, 300, 80, 70);

        jDialog1.getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 0, 470, 420);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE GALPONES");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(130, 0, 240, 30);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(20, 10, 500, 30);

        jPanel3.setBackground(new java.awt.Color(255, 204, 51));
        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel2.setText("Muestra");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(440, 10, 60, 15);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel3.setText("Número de Pollos");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 10, 130, 15);

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel4.setText("Raza");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(200, 10, 40, 15);

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel5.setText("Peso");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(320, 10, 40, 14);
        jPanel3.add(txtnumeropollo);
        txtnumeropollo.setBounds(30, 30, 90, 30);
        jPanel3.add(txtmuestra);
        txtmuestra.setBounds(430, 30, 70, 30);
        jPanel3.add(txtpeso);
        txtpeso.setBounds(310, 30, 60, 30);
        jPanel3.add(txtraza);
        txtraza.setBounds(180, 30, 74, 30);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(null);

        jButton1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/acualizar.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(230, 10, 100, 80);

        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/eliminar.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton2);
        jButton2.setBounds(340, 10, 90, 80);

        jButton3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/guardar.png"))); // NOI18N
        jButton3.setText("Guardar");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jButton3.setBounds(130, 10, 90, 80);

        jButton4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Tabla.png"))); // NOI18N
        jButton4.setText("Registros");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);
        jButton4.setBounds(20, 10, 100, 80);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(30, 80, 450, 100);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 50, 520, 200);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 540, 270);

        setSize(new java.awt.Dimension(555, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jDialog1.setVisible(true);
        Updategalpon();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        seleccionar();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Galpon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Galpon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Galpon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Galpon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Galpon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablegalpones;
    private javax.swing.JTextField txtmuestra;
    private javax.swing.JTextField txtnumeropollo;
    private javax.swing.JTextField txtpeso;
    private javax.swing.JTextField txtraza;
    // End of variables declaration//GEN-END:variables
}
