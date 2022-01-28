/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ConexionBaseDatos;
import Controller.GalponController;
import PaqueteDAO.GalponDAO;
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
    private GalponDAO galponDAO = new GalponDAO();
    private TableGalpon modelo = new TableGalpon();
    //DefaultTableModel modelo;

    /**
     * Creates new form Frm_Galpon
     */
    public Frm_Galpon() {
        super("Registro de Galpones");
        setResizable(false);
        initComponents();
        txtid.setVisible(false);
        setLocationRelativeTo(null);
        poputTable();
        cargarTable();
    }

    private void cargarTable() {
        modelo.setLista(galponController.listar());
        tablegalpones.setModel(modelo);
        tablegalpones.updateUI();
//        DefaultTableModel modelo = (DefaultTableModel) tablegalpones.getModel();
//        modelo.setRowCount(0);
//        int columnas;
//        int [] anchos = {10,100,100};
//        for (int i = 0; i < tablegalpones.getColumnCount(); i++) {
//            tablegalpones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//        }
//        try {
//            c = con.conectar();
//            ps = (PreparedStatement) c.prepareStatement("SELECT id_galpon, pollos, raza FROM galpones");
//            rs = ps.executeQuery();
//            rsmd = (ResultSetMetaData) rs.getMetaData();
//            columnas = rsmd.getColumnCount();
//            while (rs.next()) {                
//                Object[] fila = new Object[columnas];
//                for (int i = 0; i < columnas; i++) {
//                    fila[i] = rs.getObject(i+1);
//                }
//                modelo.addRow(fila);
//            }
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
    private void seleccionar() {
        try {
            this.txtid.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 0).toString());
            this.txtnumeropollo.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 1).toString());
            this.txtraza.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 2).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buscar() {
    }

    public void poputTable() {
        JPopupMenu popuMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Aplicar Vacuna", new ImageIcon(getClass().getResource("/Image/vacuna.png")));
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Frm_Vacunas vacunas = new Frm_Vacunas();
//                int fila = tablegalpones.getSelectedRow();
//                vacunas.setVisible(true);
//                Frm_Vacunas.txtnum.setText(tablegalpones.getValueAt(fila, 0).toString());
//                dispose();
            }
        });
        popuMenu.add(menuItem1);
        tablegalpones.setComponentPopupMenu(popuMenu);
    }

    private void limpiardatos() {
        galponController.setGalpon(null);
        txtraza.setText("");
        txtnumeropollo.setText("");
        txtid.setText("");
    }
    private void guardar() {
        if (txtnumeropollo.getText().trim().isEmpty() || txtraza.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            galponDAO.getGalpones().setNumPollo(txtnumeropollo.getText());
            galponDAO.getGalpones().setRaza(txtraza.getText());
            if (galponDAO.guardar()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiardatos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTable();
        }
    }

    private void Updategalpon() {
        galponController.getGalpon().setId(Integer.parseInt(txtid.getText()));
        galponController.getGalpon().setNumPollo(txtnumeropollo.getText());
        galponController.getGalpon().setRaza(txtraza.getText());
        if (galponController.Update()) {
            JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
            limpiardatos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTable();
    }

    private void delete() {
        int seleccionar = tablegalpones.getSelectedRow();
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            galponController.getGalpon().setId(Integer.parseInt(txtid.getText()));
            if (galponController.Delete()) {
                JOptionPane.showMessageDialog(null, "galpon eliminado exitosamente");
                limpiardatos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
            cargarTable();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablegalpones = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnumeropollo = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtraza = new javax.swing.JTextField();

        jDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialog1.setFont(new java.awt.Font("Agency FB", 0, 12)); // NOI18N
        jDialog1.setSize(new java.awt.Dimension(482, 441));
        jDialog1.getContentPane().setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 204, 0));
        jPanel5.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel6.setText("LISTA DE GALPONES");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(90, 20, 210, 16);

        tablegalpones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Num_Pollos", "Raza"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablegalpones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablegalponesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablegalpones);

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(20, 60, 360, 230);

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

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel3.setText("Número de Pollos");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(100, 10, 130, 15);

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel4.setText("Raza");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(320, 10, 40, 15);
        jPanel3.add(txtnumeropollo);
        txtnumeropollo.setBounds(100, 30, 90, 30);
        jPanel3.add(txtid);
        txtid.setBounds(430, 40, 74, 30);

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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
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
        jPanel3.add(txtraza);
        txtraza.setBounds(310, 30, 74, 30);

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        delete();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablegalponesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablegalponesMouseClicked
        seleccionar();
    }//GEN-LAST:event_tablegalponesMouseClicked

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
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablegalpones;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnumeropollo;
    private javax.swing.JTextField txtraza;
    // End of variables declaration//GEN-END:variables
}
