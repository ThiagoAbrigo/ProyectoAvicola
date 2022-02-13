/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PersonaContoller;
import Controller.EmpleadoController;
import View.Table.TablaPersona;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class Frm_Persona extends javax.swing.JDialog {

    public PersonaContoller pc = new PersonaContoller();
    public EmpleadoController ec = new EmpleadoController();
    private TablaPersona modelo = new TablaPersona();
    private TablaPersona mdelo2 = new TablaPersona();

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
                //System.out.println("Esta acivado es true");
                estado = true;
            }
            if (checkRolBoqueado.isSelected()) {
                checkRolActivo.setSelected(false);
                //System.out.println("Esta blqueado es true");
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

    public void guardaEmpleado() {
        if (cbxTipoRol.getSelectedItem() == "Empleado") {

            ec.getEmpleado().setId(pc.getLisPers().tamanio() + 1);
            ec.getEmpleado().setNombre(txtNombre.getText());
            ec.getEmpleado().setApellido(txtApellido.getText());
            ec.getEmpleado().setCedula(txtCadula.getText());
            ec.getEmpleado().setPagoHora(0.00);
            ec.getEmpleado().setSeguroSocialEmpleado(0.00);
            ec.getEmpleado().setSeguroSocialEmpleador(0.00);
            ec.getEmpleado().setHrsLaborada(0.00);
            ec.getEmpleado().setFechaContratacion("No hay");
            ec.getEmpleado().setFechaSalida("No hay");

            ec.getEmpleado().getRol().setCargo("No asignado");
            ec.getEmpleado().getRol().setDescripcion("No asignado");
            if (ec.Save()) {
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pruebas() {
        //System.out.println(cbxTipoRol.getSelectedItem());
        if (cbxTipoRol.getSelectedItem() == "Empleado") {
            System.out.println("esta guradando empleado");
            if (ec.Save()) {
                JOptionPane.showMessageDialog(null, "Se guardar correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void guardar() {
        int valor = tablaPersonas.getRowCount();
        txtId.setText(String.valueOf(valor + 1));
        if (buscarGuardar()) {

        } else {
            if (verficarEspacios()) {
                pc.getPersona().setId(pc.getLisPers().tamanio() + 1);
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
                    if (cbxTipoRol.getSelectedItem() == "Empleado") {

                        ec.getEmpleado().setId(pc.getLisPers().tamanio() + 1);
                        ec.getEmpleado().setNombre(txtNombre.getText());
                        ec.getEmpleado().setApellido(txtApellido.getText());
                        ec.getEmpleado().setCedula(txtCadula.getText());
                        ec.getEmpleado().setPagoHora(0.00);
                        ec.getEmpleado().setSeguroSocialEmpleado(9.45);
                        ec.getEmpleado().setSeguroSocialEmpleador(11.50);
                        ec.getEmpleado().setHrsLaborada(0.00);
                        ec.getEmpleado().setFechaContratacion("No hay");
                        ec.getEmpleado().setFechaSalida("No hay");

                        ec.getEmpleado().getRol().setCargo("Ninguno");
                        ec.getEmpleado().getRol().setDescripcion("No asignado");
                        if (ec.Save()) {
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al guardar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
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

    }

    private void editar() {

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
                if (cbxTipoRol.getSelectedItem() == "Empleado") {

                    ec.getEmpleado().setId(Integer.valueOf(txtId.getText()));
                    ec.getEmpleado().setNombre(txtNombre.getText());
                    ec.getEmpleado().setApellido(txtApellido.getText());
                    ec.getEmpleado().setCedula(txtCadula.getText());
                    ec.getEmpleado().setPagoHora(0.00);
                    ec.getEmpleado().setSeguroSocialEmpleado(0.00);
                    ec.getEmpleado().setSeguroSocialEmpleador(0.00);
                    ec.getEmpleado().setHrsLaborada(0.00);
                    ec.getEmpleado().setFechaContratacion("No hay");
                    ec.getEmpleado().setFechaSalida("No hay");

                    ec.getEmpleado().getRol().setCargo("Ninguno");
                    ec.getEmpleado().getRol().setDescripcion("No asignado");
                    if (ec.Save()) {

                    }
                }
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

    public boolean buscarGuardar() {
        boolean repetido = false;
        for (int i = 0; i < tablaPersonas.getRowCount(); i++) {
            if (String.valueOf(tablaPersonas.getValueAt(i, 2)).compareToIgnoreCase(txtCadula.getText().toString()) == 0) {
                JOptionPane.showMessageDialog(null, "Cedula ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                txtCadula.setText("");
                repetido = true;
            }
            if (String.valueOf(tablaPersonas.getValueAt(i, 4)).compareToIgnoreCase(txtCorreo.getText().toString()) == 0) {
                JOptionPane.showMessageDialog(null, "Correo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                txtCorreo.setText("");
                repetido = true;
            }
        }
        return false;
    }

    public boolean buscarEditar() {
        boolean repetido = false;
        for (int i = 0; i < tablaPersonas.getRowCount(); i++) {
            if (String.valueOf(tablaPersonas.getValueAt(i, 2)).compareToIgnoreCase(txtCadula.getText().toString()) == 0) {
                JOptionPane.showMessageDialog(null, "Cedula ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                txtCadula.setText("");
                repetido = true;
            }
            if (String.valueOf(tablaPersonas.getValueAt(i, 4)).compareToIgnoreCase(txtCorreo.getText().toString()) == 0) {
                JOptionPane.showMessageDialog(null, "Correo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                txtCorreo.setText("");
                repetido = true;
            }
        }
        return false;
    }
    int tipoOrden = 0;

    public void ordenar() {
        //System.out.println(cbxOrdenar.getSelectedIndex());
        int cbxOrden = cbxOrdenar.getSelectedIndex();
        try {
            pc.setLisPers(null);
            mdelo2.setLista(pc.getLisPers());
            tablaPersonas.updateUI();
            cargarTable();
            if (tipoOrden == 0) {
                if (cbxOrden == 0) {
                    pc.getLisPers().seleccion_clase("nombre", 1);
                    mdelo2.setLista(pc.getLisPers());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 1) {
                    pc.getLisPers().seleccion_clase("apellido", 1);
                    mdelo2.setLista(pc.getLisPers());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 2) {
                    pc.getLisPers().seleccion_clase("cedula", 1);
                    mdelo2.setLista(pc.getLisPers());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                ////////////
                tipoOrden = 1;
            } else {
                if (cbxOrden == 0) {
                    pc.getLisPers().seleccion_clase("nombre", 2);
                    mdelo2.setLista(pc.getLisPers());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 1) {
                    pc.getLisPers().seleccion_clase("apellido", 2);
                    mdelo2.setLista(pc.getLisPers());
                    tablaPersonas.setModel(mdelo2);
                    tablaPersonas.updateUI();
                }
                if (cbxOrden == 2) {
                    pc.getLisPers().seleccion_clase("cedula", 2);
                    mdelo2.setLista(pc.getLisPers());
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

    public void eliminar() {
        try {
            if (txtId.getText() == "") {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                pc.getPersona().setId(Integer.parseInt(txtId.getText().toString()));
                ec.getEmpleado().setCedula(txtCadula.getText().toString());
                if (pc.Delete()) {
                    if (ec.Delete()) {

                    }
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
                cargarTable();
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar " + e);
        }
    }

    public void leer() throws Exception {
        limpiar();
        int seleccionar = tablaPersonas.getSelectedRow();
        //System.out.println("Seleccionar es "+seleccionar);
        //System.out.println("dato es "+String.valueOf(tablaPersonas.getValueAt(seleccionar, 0)));
        if (seleccionar >= 0) {
            //String.valueOf(tablaPersonas.getValueAt(seleccionar, 2))
            txtId.setText(String.valueOf(pc.getLisPers().value(pc.getLisPers().consultarDatoPosicion(seleccionar), "id")));
            txtNombre.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 0)));
            txtApellido.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 1)));
            txtCadula.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 2)));
            txtCelular.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 3)));
            txtCorreo.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 4)));
            txtDirecion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 5)));
            txtPassword.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 6)));
            cbxTipoRol.setSelectedItem(String.valueOf(tablaPersonas.getValueAt(seleccionar, 7)));
            txtRolDescripcion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 9)));

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
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        bttGuardar = new javax.swing.JButton();
        bttEditar = new javax.swing.JButton();
        bttLeer = new javax.swing.JButton();
        bttEliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();
        cbxOrdenar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        txtNombre.setBorder(null);
        jPanel2.add(txtNombre);
        txtNombre.setBounds(80, 30, 130, 30);

        txtCadula.setBorder(null);
        jPanel2.add(txtCadula);
        txtCadula.setBounds(80, 120, 130, 30);

        txtCelular.setBorder(null);
        jPanel2.add(txtCelular);
        txtCelular.setBounds(80, 210, 130, 30);

        txtCorreo.setBorder(null);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        jPanel2.add(txtCorreo);
        txtCorreo.setBounds(80, 160, 130, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 40, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cédula:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 130, 50, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Celular:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 210, 60, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Correo:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 170, 50, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Dirección:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(250, 30, 70, 20);

        txtDirecion.setColumns(20);
        txtDirecion.setRows(5);
        jScrollPane1.setViewportView(txtDirecion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(250, 50, 250, 100);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Apellido:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 90, 60, 20);

        txtApellido.setBorder(null);
        jPanel2.add(txtApellido);
        txtApellido.setBounds(80, 80, 130, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Contraseña:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(240, 180, 80, 15);

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

        checkRolActivo.setBackground(new java.awt.Color(255, 255, 255));
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
        jPanel3.add(checkRolActivo);
        checkRolActivo.setBounds(110, 100, 110, 30);

        cbxTipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Empleado", "Gerente", "Desarrollador" }));
        jPanel3.add(cbxTipoRol);
        cbxTipoRol.setBounds(100, 20, 127, 30);

        checkRolBoqueado.setBackground(new java.awt.Color(255, 255, 255));
        checkRolBoqueado.setText("Bloqueado");
        checkRolBoqueado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRolBoqueadoActionPerformed(evt);
            }
        });
        jPanel3.add(checkRolBoqueado);
        checkRolBoqueado.setBounds(110, 130, 110, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Estado:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 110, 46, 15);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(100, 90, 130, 10);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(530, 30, 250, 170);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(660, 330, 73, 23);

        txtPassword.setBorder(null);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtPassword);
        txtPassword.setBounds(320, 170, 130, 30);
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
        jButton1.setBounds(600, 280, 115, 30);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(80, 60, 130, 10);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(80, 110, 130, 10);
        jPanel2.add(jSeparator4);
        jSeparator4.setBounds(80, 150, 130, 10);
        jPanel2.add(jSeparator5);
        jSeparator5.setBounds(80, 240, 130, 10);
        jPanel2.add(jSeparator6);
        jSeparator6.setBounds(80, 190, 130, 10);
        jPanel2.add(jSeparator7);
        jSeparator7.setBounds(320, 200, 140, 10);

        bttGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttGuardar.setText("Crear");
        bttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(bttGuardar);
        bttGuardar.setBounds(30, 280, 90, 30);

        bttEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEditar.setText("Editar");
        bttEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEditarActionPerformed(evt);
            }
        });
        jPanel2.add(bttEditar);
        bttEditar.setBounds(180, 280, 100, 30);

        bttLeer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttLeer.setText("Leer");
        bttLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLeerActionPerformed(evt);
            }
        });
        jPanel2.add(bttLeer);
        bttLeer.setBounds(320, 280, 90, 30);

        bttEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEliminar.setText("Elminar");
        bttEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(bttEliminar);
        bttEliminar.setBounds(460, 280, 90, 30);

        jButton2.setText("Ordenar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(150, 330, 73, 23);

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
        cbxOrdenar.setBounds(20, 330, 90, 20);

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
        try {
            leer();
        } catch (Exception ex) {
            Logger.getLogger(Frm_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bttLeerActionPerformed

    private void bttEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_bttEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //buscarRepatidos();
        //cargarTable();
        pruebas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ordenar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenarActionPerformed

    private void tablaPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonasMouseClicked
        try {
            // TODO add your handling code here:
            leer();
        } catch (Exception ex) {
            Logger.getLogger(Frm_Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaPersonasMouseClicked

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

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
    private javax.swing.JComboBox<String> cbxOrdenar;
    private javax.swing.JComboBox<String> cbxTipoRol;
    private javax.swing.JCheckBox checkRolActivo;
    private javax.swing.JCheckBox checkRolBoqueado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
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
