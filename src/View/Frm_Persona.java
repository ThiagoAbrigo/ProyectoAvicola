/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PersonaContoller;
import View.Table.TablaPersona;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class Frm_Persona extends javax.swing.JDialog {
    public PersonaContoller pc = new PersonaContoller();
    private TablaPersona modelo = new TablaPersona();
    /**
     * Creates new form Frm_Persona1
     */
    public Frm_Persona(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiar();
        cargarTable();
        txtId.setVisible(false);
    }

    private void cargarTable() {
        modelo.setLista(pc.listar());
        tablaPersonas.setModel(modelo);
        tablaPersonas.updateUI();
    }

    private boolean estadoRol() {
        Boolean estado = false;
        if (checkRolActivo.isSelected() && checkRolBoqueado.isSelected() == true) {
            checkRolBoqueado.setSelected(false);
            checkRolActivo.setSelected(false);
            JOptionPane.showMessageDialog(null, "Selecioanr solo un estado Rol", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkRolActivo.isSelected()) {
                checkRolBoqueado.setSelected(false);
                System.out.println("Esta acivado es true");
                estado = true;
            }
            if (checkRolBoqueado.isSelected()) {
                checkRolActivo.setSelected(false);
                System.out.println("Esta blqueado es true");
                estado = false;
            }
        }
        return estado;

    }

    private void limpiar() {
        ///Persona
        pc.setPersona(null);
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCadula.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        txtDirecion.setText("");
        txtPassword.setText("");
        ///Roles
        cbxTipoRol.setSelectedIndex(0);
        txtRolDescripcion.setText("");
        checkRolActivo.setSelected(false);
        checkRolBoqueado.setSelected(false);

    }

    private boolean verficarEspacios() {
        return (txtNombre.getText().trim().length() > 0 && txtCadula.getText().trim().length() > 0
                && txtCelular.getText().trim().length() > 0 && txtCorreo.getText().trim().length() > 0
                && txtDirecion.getText().trim().length() > 0 && txtRolDescripcion.getText().trim().length() > 0);

    }

    public void prueba() {
        int seleccionar = tablaPersonas.getSelectedRow();
        //String.valueOf(tablaPersonas.getValueAt(seleccionar, 2))
        //int valor = tablaPersonas.getSelectedRow()+1;
        //System.out.println("valor"+valor);

        System.out.println(String.valueOf(tablaPersonas.getValueAt(seleccionar, 2)));

    }

    private void guardar() {
        //Integer aux = (pc.getPersona() !=null) ? pc.getPersona().getId()+1: 1;
        if (pc.getPersona() == null) {
            pc.getPersona().setId(1);
            txtId.setText(String.valueOf(pc.getPersona().getId() + 1));
        }
        //txtId.setText(String.valueOf(new Long(String.valueOf(aux))));
        if (verficarEspacios()) {
            pc.getPersona().setId(Integer.valueOf(txtId.getText()));
            pc.getPersona().setNombre(txtNombre.getText());
            pc.getPersona().setApellido(txtApellido.getText());
            pc.getPersona().setCedula(txtCadula.getText());
            pc.getPersona().setCelular(txtCelular.getText());
            pc.getPersona().setCorreo(txtCorreo.getText());
            pc.getPersona().setDireccion(txtDirecion.getText());
            pc.getPersona().setPassword(txtPassword.getText().toString());

            pc.getPersona().getRol().setCargo(cbxTipoRol.getItemAt(cbxTipoRol.getSelectedIndex()));
            pc.getPersona().getRol().setAutorizacion(estadoRol());
            pc.getPersona().getRol().setDescripcion(txtRolDescripcion.getText());

            if (pc.Save()) {
                JOptionPane.showMessageDialog(null, "Se guardar correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTable();
        } else {
            JOptionPane.showMessageDialog(null, "LLenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        int seleccionar = tablaPersonas.getSelectedRow() + 1;
        String aux = String.valueOf(seleccionar);
        txtId.setText(aux);
        if (verficarEspacios()) {

            pc.getPersona().setId(Integer.valueOf(txtId.getText()));
            pc.getPersona().setNombre(txtNombre.getText());
            pc.getPersona().setApellido(txtApellido.getText());
            pc.getPersona().setCedula(txtCadula.getText());
            pc.getPersona().setCelular(txtCelular.getText());
            pc.getPersona().setCorreo(txtCorreo.getText());
            pc.getPersona().setDireccion(txtDirecion.getText());
            pc.getPersona().setPassword(txtPassword.getText().toString());

            pc.getPersona().getRol().setCargo(cbxTipoRol.getItemAt(cbxTipoRol.getSelectedIndex()));
            pc.getPersona().getRol().setAutorizacion(estadoRol());
            pc.getPersona().getRol().setDescripcion(txtRolDescripcion.getText());
            if (pc.Update()) {
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

    public void eliminar() {

        int seleccionar = tablaPersonas.getSelectedRow();
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            pc.getPersona().setId(Integer.parseInt(String.valueOf(tablaPersonas.getValueAt(seleccionar, 0))));
            if (pc.Delete()) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
            cargarTable();
        }
    }

    public void leer() {
        limpiar();
        int seleccionar = tablaPersonas.getSelectedRow();
        if (seleccionar >= 0) {
            //String.valueOf(tablaPersonas.getValueAt(seleccionar, 2))
            txtId.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 0)));
            txtNombre.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 1)));
            txtApellido.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 2)));
            txtCadula.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 3)));
            txtCelular.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 4)));
            txtCorreo.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 5)));
            txtDirecion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 6)));
            txtPassword.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 7)));
            //cbxTipoRol.setToolTipText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 8)));
            txtRolDescripcion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 10)));
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
        txtNombre = new javax.swing.JTextField();
        txtCadula = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDirecion = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRolDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        checkRolActivo = new javax.swing.JCheckBox();
        cbxTipoRol = new javax.swing.JComboBox<>();
        checkRolBoqueado = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtId = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        bttGuardar = new javax.swing.JButton();
        bttEditar = new javax.swing.JButton();
        bttLeer = new javax.swing.JButton();
        bttEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(null);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Cedula");

        jLabel4.setText("Celular");

        jLabel5.setText("Correo");

        jLabel6.setText("Direccion");

        txtDirecion.setColumns(20);
        txtDirecion.setRows(5);
        jScrollPane1.setViewportView(txtDirecion);

        jLabel7.setText("Apellido");

        jLabel9.setText("Password");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Rol"));

        jLabel1.setText("Tipo");

        jLabel8.setText("Descripcion");

        checkRolActivo.setText("Activo");
        checkRolActivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkRolActivoMouseClicked(evt);
            }
        });
        checkRolActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRolActivoActionPerformed(evt);
            }
        });
        checkRolActivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkRolActivoKeyPressed(evt);
            }
        });

        cbxTipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Empleado", "Gerente", "Desarrollador" }));

        checkRolBoqueado.setText("Bloqueado");
        checkRolBoqueado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRolBoqueadoActionPerformed(evt);
            }
        });

        jLabel10.setText("Estado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTipoRol, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkRolActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRolDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkRolBoqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbxTipoRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRolDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(checkRolActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkRolBoqueado))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bttGuardar.setText("Crear");
        bttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttGuardarActionPerformed(evt);
            }
        });

        bttEditar.setText("Editar");
        bttEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEditarActionPerformed(evt);
            }
        });

        bttLeer.setText("Leer");
        bttLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLeerActionPerformed(evt);
            }
        });

        bttEliminar.setText("Elminar");
        bttEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bttGuardar)
                .addGap(18, 18, 18)
                .addComponent(bttEditar)
                .addGap(18, 18, 18)
                .addComponent(bttLeer)
                .addGap(18, 18, 18)
                .addComponent(bttEliminar)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttGuardar)
                    .addComponent(bttEditar)
                    .addComponent(bttLeer)
                    .addComponent(bttEliminar))
                .addContainerGap())
        );

        jButton1.setText("Limpiar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCadula, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCadula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 670, 351);

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
        jScrollPane2.setViewportView(tablaPersonas);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 370, 670, 240);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void checkRolActivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkRolActivoMouseClicked

    }//GEN-LAST:event_checkRolActivoMouseClicked

    private void checkRolActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRolActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRolActivoActionPerformed

    private void checkRolActivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkRolActivoKeyPressed
        // TODO add your handling code here:
        System.out.println("Se activo");
    }//GEN-LAST:event_checkRolActivoKeyPressed

    private void checkRolBoqueadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRolBoqueadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRolBoqueadoActionPerformed

    private void bttGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_bttGuardarActionPerformed

    private void bttEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEditarActionPerformed
        editar();
    }//GEN-LAST:event_bttEditarActionPerformed

    private void bttLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLeerActionPerformed
        leer();
    }//GEN-LAST:event_bttLeerActionPerformed

    private void bttEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_bttEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiar();
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
            java.util.logging.Logger.getLogger(Frm_Persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_Persona dialog = new Frm_Persona(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bttEliminar;
    private javax.swing.JButton bttGuardar;
    private javax.swing.JButton bttLeer;
    private javax.swing.JComboBox<String> cbxTipoRol;
    private javax.swing.JCheckBox checkRolActivo;
    private javax.swing.JCheckBox checkRolBoqueado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCadula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDirecion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRolDescripcion;
    // End of variables declaration//GEN-END:variables
}
