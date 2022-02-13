/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;
///

import java.awt.Frame.*;
import Controller.GalponController;
import Controller.PersonaContoller;
import Controller.VacunaController;
import View.Table.TablaPersona;
import View.Table.TableGalpon;
import View.Table.TableGalpon_Mortalidad;
import View.Table.TableVacuna;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import lista.Controller.Lista;

/**
 *
 * @author JavierSarango
 */
public class Frm_PrincipalMenu extends javax.swing.JDialog {

    fondoPaneles paneles = new fondoPaneles();
    fondoLabel logotipo = new fondoLabel();
    private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private VacunaController vacunaController = new VacunaController();
    private TableVacuna modelov = new TableVacuna();
    int xmouse, ymouse;
    public GalponController galponController = new GalponController();
    private TableGalpon modeloG = new TableGalpon();
    private TableGalpon_Mortalidad modeloM = new TableGalpon_Mortalidad();
    private TableGalpon_Mortalidad modeloGM = new TableGalpon_Mortalidad();
    private SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
    public PersonaContoller pc = new PersonaContoller();
    private TablaPersona modelo = new TablaPersona();

    /**
     * Se crea la ventana principal
     */
    public Frm_PrincipalMenu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtid.setVisible(false);
        poputTable();
        cargarTableGalpon();
        cargarTableVacuna();
        cargarTableMortalidad();
        txtidvacuna.setVisible(false);
        txtId.setVisible(false);
        cargarTableAdminitracion();
    }

    /*
        Métodos de Tablas
     */
 /*
        Carga la tabla de la administración
     */

    private void cargarTableAdminitracion() {
        modelo.setLista(pc.listar());
        tablaPersonas.setModel(modelo);
        tablaPersonas.updateUI();
    }

    /*
        Carga la tabla de galpones con los nombres de las columnas centradas
     */
    private void cargarTableGalpon() {
        modeloG.setLista(galponController.listar());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablegalpones.getColumnCount(); i++) {
            tablegalpones.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tablegalpones.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tablegalpones.setModel(modeloG);

        }
        tablegalpones.updateUI();

    }

    /*
        Carga la tabla de vacunas con los nombres de las columnas centradas
     */
    private void cargarTableVacuna() {
        modelov.setLista(vacunaController.listar());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablevacuna.getColumnCount(); i++) {
            tablevacuna.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tablevacuna.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tablevacuna.setModel(modelov);
        }
        tablevacuna.updateUI();
    }

    /*
        Carga la tabla de mortalidad de pollos
     */
    private void cargarTableMortalidad() {
        modeloGM.setLista(galponController.listarMortalidad());
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblMortalidad.getColumnCount(); i++) {
            tblMortalidad.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tblMortalidad.getColumnModel().getColumn(i).setCellRenderer(tcr);
            tblMortalidad.setModel(modeloGM);
        }
        tblMortalidad.updateUI();
    }
    /**
     * Carga el detalle de ingresos
     */

    /*
        Crea submenús en la tabla
     */
    public void poputTable() {
        JPopupMenu popuMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Aplicar Vacuna", new ImageIcon(getClass().getResource("/Image/vacuna.png")));
        JMenuItem menuItem2 = new JMenuItem("Registro de Muerte");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelSlider1.nextPanel(4, 0, JPRegistroVacuna, jPanelSlider1.right);
                int fila = tablegalpones.getSelectedRow();
                JPRegistroVacuna.setVisible(true);
                txfGalponSeleccionado.setText(tablegalpones.getValueAt(fila, 0).toString());

            }
        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jPanelSlider1.nextPanel(4, 0, JPMortalidad, jPanelSlider1.right);
                int fila = tablegalpones.getSelectedRow();
                JPMortalidad.setVisible(true);
                txfGalponSeleccionado1.setText(tablegalpones.getValueAt(fila, 0).toString());
                txtCtdPollosActual.setText(tablegalpones.getValueAt(fila, 1).toString());
            }
        });
        popuMenu.add(menuItem1);
        popuMenu.add(menuItem2);
        tablegalpones.setComponentPopupMenu(popuMenu);
    }

    /*
        Métodos guardar
     */
 /*
        Guarda los datos de las personas
     */

    private void guardar() {
        int valor = tablaPersonas.getRowCount();
        txtId.setText(String.valueOf(valor + 1));
        if (verficarEspacios()) {
            //pc.getPersona().setId(Integer.valueOf(txtId.getText()));
            pc.getPersona().setId(pc.getLisPers().tamanio() + 1);
            pc.getPersona().setNombre(txtNombre.getText());
            pc.getPersona().setApellido(txtApellido.getText());
            pc.getPersona().setCedula(txtCadula.getText());
            pc.getPersona().setCelular(txtCelular.getText());
            pc.getPersona().setCorreo(txtCorreo.getText());
            pc.getPersona().setDireccion(txtDirecion.getText());
            pc.getPersona().setPassword(txtPassword.getText().toString());

            //pc.getPersona().getRol().setCargo(cbxTipoRol.getItemAt(cbxTipoRol.getSelectedIndex()));
            pc.getPersona().getRol().setCargo(cbxTipoRol.getItemAt(cbxTipoRol.getSelectedIndex()));
            pc.getPersona().getRol().setAutorizacion(estadoRol());
            pc.getPersona().getRol().setDescripcion(txtRolDescripcion.getText());

            if (pc.Save()) {
                JOptionPane.showMessageDialog(null, "Se guardar correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiarAdministracion();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableAdminitracion();
        } else {
            JOptionPane.showMessageDialog(null, "LLenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
        Guarda informacion del galpón
     */
    private void guardarGalpon() {
        if (txtnumeropollo.getText().trim().isEmpty() || txtraza.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            galponController.getGalpon().setNumPollo(Integer.parseInt(txtnumeropollo.getText()));
            galponController.getGalpon().setRaza(txtraza.getText());
            galponController.getGalpon().setCtdSuministrada(Integer.parseInt(txtCtdBalanceadoSuministrada.getText().trim()));
            galponController.getGalpon().setTbalanceado(String.valueOf(cbTipoBalanceado.getSelectedItem()));
            galponController.getGalpon().setfDiarioAlimentacion(Integer.parseInt(cbFDAlimentacion.getSelectedItem().toString()));
            galponController.getGalpon().setfIncio(jDateFInicio.getDate());
            galponController.getGalpon().setfFin(jDatefFin.getDate());
            if (galponController.Save()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiardatosGalpon();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableGalpon();
        }
    }

    /*
        Guarda información de la vacuna
     */
    private void guardarVacuna() {
        if (txtnombreProducto.getText().trim().isEmpty() || txfDosis.getText().trim().isEmpty()
                || cbTipoFarmaco.getSelectedItem() == null || JtaJustificacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            vacunaController.getVacuna().setNombre(txtnombreProducto.getText());
            vacunaController.getVacuna().setFarmaco(String.valueOf(cbTipoFarmaco.getSelectedItem()));
            vacunaController.getVacuna().setJustificacion(JtaJustificacion.getText().trim());
            vacunaController.getVacuna().setDosis(Double.parseDouble(txfDosis.getText()));
            vacunaController.getVacuna().setOnevacuna(jDate1.getDate());
            vacunaController.getVacuna().setTwovacuna(jDate2.getDate());
            if (vacunaController.Save()) {
                JOptionPane.showMessageDialog(null, "Guardado", "Ok", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableVacuna();
        }
    }

    /*
        Guarda información de la mortalidad de pollos
     */
    private void guardarMortalidadPollo() {
        if (txtPollosFallecidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rellene el campo vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Integer ctActual = Integer.parseInt(txtCtdPollosActual.getText().trim());
            Integer ctFallecidos = Integer.parseInt(txtPollosFallecidos.getText().trim());
            galponController.getGalpon().setId(Integer.parseInt(txfGalponSeleccionado1.getText()));
            galponController.getGalpon().setNumPollo(ctActual);
            galponController.getGalpon().setPollosMuertos(ctFallecidos);
            galponController.getGalpon().setExistencias((ctActual - ctFallecidos));

            if (galponController.SaveMortalidad()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                cargarTableMortalidad();
                galponController.getGalpon().setNumPollo(galponController.getGalpon().getExistencias());
                if (galponController.Updategalpon()) {
                    JOptionPane.showMessageDialog(null, "Informacion del galpon actualizada", "Ok", JOptionPane.INFORMATION_MESSAGE);
                }
                cargarTableGalpon();
                limpiardatosMortalidad();

            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableMortalidad();
        }

    }

    /*
        Métodos Eliminar
     */
 /*
        Elimina al usuario seleccionado 
     */
    public void eliminar() {
        try {
            if (txtId.getText() == "") {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                pc.getPersona().setId(Integer.parseInt(txtId.getText().toString()));
                if (pc.Delete()) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                    limpiarAdministracion();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
                cargarTableAdminitracion();
            }
        } catch (Exception e) {
            System.out.println("Error en eliminar " + e);
        }
    }

    /*
        Elimina el galpón seleccionado
     */
    private void deleteGalpon() {
        int seleccionar = tablegalpones.getSelectedRow();
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            galponController.getGalpon().setId(Integer.parseInt(txtid.getText()));
            if (galponController.Delete()) {
                JOptionPane.showMessageDialog(null, "galpon eliminado exitosamente");
                limpiardatosGalpon();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
            cargarTableGalpon();
        }
    }

    /*
        Limpiar datos
     */
 /*
        Limpia los datos de los campos ventana administración
     */
    private void limpiarAdministracion() {
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

    /*
        Limpia los campos de la vista
     */
    private void limpiardatosGalpon() {
        txtCtdBalanceadoSuministrada.setText("");
        txtnumeropollo.setText("");
        txtraza.setText("");
        jDateFInicio.setDate(new Date());
        jDatefFin.setDate(new Date());
        BusquedaDesde.setDate(new Date());
        //BusquedaHasta.setDate(new Date());
        galponController.setGalpon(null);
        //cargarTableGalpon();
    }

    /*
        Limpia los campos de la tabla Mortalidad
     */
    private void limpiardatosMortalidad() {
        txfGalponSeleccionado1.setText("");
        txtCtdPollosActual.setText("");
        txtPollosFallecidos.setText("");
        galponController.setGalpon(null);

    }

    /*
        Limpia los campos de la vista
     */
    private void limpiardatosVacuna() {
        txtnombreProducto.setText(" ");
        JtaJustificacion.setText(" ");
        jDate1.setDate(new Date());
        jDate2.setDate(new Date());
        vacunaController.setVacuna(null);
        //cargarTableVacuna();
    }

    /*
        Metodos de seleccion
     */
 /*
        Recupera la información de la fila seleccionada hacia los campos respectivos en la vista
     */
    private void seleccionar() {
        String fechaIni = tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 6).toString();
        Date fechaIn = null;
        String fechaFin = tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 7).toString();
        Date fechaF = null;
        try {
            fechaIn = st.parse(fechaIni);
            fechaF = st.parse(fechaFin);
            this.txtid.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 0).toString());
            this.txtnumeropollo.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 1).toString());
            this.txtraza.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 2).toString());
            this.txtCtdBalanceadoSuministrada.setText(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 3).toString());
            this.cbTipoBalanceado.setSelectedItem(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 4).toString());
            this.cbFDAlimentacion.setSelectedItem(tablegalpones.getValueAt(tablegalpones.getSelectedRow(), 5).toString());
            this.jDateFInicio.setDate(fechaIn);
            this.jDatefFin.setDate(fechaF);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
        Recupera la información de la fila seleccionada hacia los campos respectivos en la vista
     */
    private void seleccionarVacuna() {

        String primeraVacuna = tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 5).toString();
        Date fechaprimera = null;
        String segundaVacuna = tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 6).toString();
        Date fechasegunda = null;
        try {
            fechaprimera = st.parse(primeraVacuna);
            fechasegunda = st.parse(segundaVacuna);
            this.txtidvacuna.setText(tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 0).toString());
            this.txtnombreProducto.setText(tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 1).toString());
            this.cbTipoFarmaco.setSelectedItem(tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 2).toString());
            this.JtaJustificacion.setText(tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 3).toString());
            this.txfDosis.setText(tablevacuna.getValueAt(tablevacuna.getSelectedRow(), 4).toString());
            this.jDate1.setDate(fechaprimera);
            this.jDate2.setDate(fechasegunda);

        } catch (Exception e) {
        }
    }

    /*
    Rescata los datos de la tabla y los muestra en los campos designados.
     */

    public void leer() throws Exception {
        limpiarAdministracion();
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
            //cbxTipoRol.setToolTipText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 8)));
            txtRolDescripcion.setText(String.valueOf(tablaPersonas.getValueAt(seleccionar, 9)));

        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar fila que desee cambiar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
        Métodos actualizar
     */
 /*
        Actualiza la información de vacuna
     */
    private void updateVacuna() {
        vacunaController.getVacuna().setId_vacuna(Integer.parseInt(txtidvacuna.getText()));
        vacunaController.getVacuna().setNombre(txtnombreProducto.getText());
        vacunaController.getVacuna().setFarmaco(String.valueOf(cbTipoFarmaco.getSelectedItem()));
        vacunaController.getVacuna().setJustificacion(JtaJustificacion.getText().trim());
        vacunaController.getVacuna().setDosis(Double.parseDouble(txfDosis.getText()));
        vacunaController.getVacuna().setOnevacuna(jDate1.getDate());
        vacunaController.getVacuna().setTwovacuna(jDate2.getDate());
        if (vacunaController.Update()) {
            JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
            limpiardatosVacuna();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTableVacuna();
    }

    /*
    Actualiza la informacion del galpon
     */
    private void Updategalpon() {
        galponController.getGalpon().setId(Integer.parseInt(txtid.getText()));
        galponController.getGalpon().setNumPollo(Integer.parseInt(txtnumeropollo.getText()));
        galponController.getGalpon().setRaza(txtraza.getText());
        galponController.getGalpon().setCtdSuministrada(Integer.parseInt(txtCtdBalanceadoSuministrada.getText().trim()));
        galponController.getGalpon().setTbalanceado(String.valueOf(cbTipoBalanceado.getSelectedItem()));
        galponController.getGalpon().setfDiarioAlimentacion(Integer.parseInt(String.valueOf(cbFDAlimentacion.getSelectedItem())));
        galponController.getGalpon().setfIncio(jDateFInicio.getDate());
        galponController.getGalpon().setfFin(jDatefFin.getDate());
        if (galponController.Update()) {
            JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
            limpiardatosGalpon();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarTableGalpon();

    }

    /*
        Métodos para ventana Frm_Persona
     */
 /*
        Verifica si el rol está activo o bloqueado
     */
    private boolean estadoRol() {
        Boolean estado = false;
        if (checkRolActivo.isSelected() && checkRolBoqueado.isSelected() == true) {
            checkRolBoqueado.setSelected(false);
            checkRolActivo.setSelected(false);
            JOptionPane.showMessageDialog(null, "Selecioanr solo un estado Rol", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkRolActivo.isSelected()) {
                checkRolBoqueado.setSelected(false);
                System.out.println("Esta activado es true");
                estado = true;
            }
            if (checkRolBoqueado.isSelected()) {
                checkRolActivo.setSelected(false);
                System.out.println("Esta bloqueado es true");
                estado = false;
            }
        }
        return estado;

    }

    /*
    Cumple la funcion de un update
    Permite editar la información que ha sido ingresada por el administrador
     */
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
                JOptionPane.showMessageDialog(null, "Se modifico correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiarAdministracion();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cargarTableAdminitracion();
        } else {
            JOptionPane.showMessageDialog(null, "LLenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
        Generar PDF
     */

 /*
        Genera un Documento PDF con la información obtenida de los campos de la vista al seleccionar
        alguna fila de la tabla
     */
    private void generarPDF() throws FileNotFoundException, DocumentException {
        if (!(txfGalponSeleccionado.getText().trim().isEmpty()
                || txtnombreProducto.getText().trim().isEmpty()
                || JtaJustificacion.getText().trim().isEmpty()
                || txfDosis.getText().trim().isEmpty())) {
            String galpon = txfGalponSeleccionado.getText().trim();
            FileOutputStream archivo = new FileOutputStream("Registro_Vacuna_Galpon_" + galpon + ".pdf", true);
            Document document = new Document();
            PdfWriter.getInstance(document, archivo);
            document.open();

            Paragraph paragraph = new Paragraph("Registro de Vacuna");
            paragraph.setAlignment(1);
            document.add(paragraph);

            document.add(new Paragraph("Galpón: " + txfGalponSeleccionado.getText().trim()));
            document.add(new Paragraph("Nombre del Producto: " + txtnombreProducto.getText().trim()));
            document.add(new Paragraph("Tipo de Producto: " + String.valueOf(cbTipoFarmaco.getSelectedItem())));
            document.add(new Paragraph("Dosis: " + txfDosis.getText().trim() + String.valueOf(cbMedidaDosis.getSelectedItem())));
            document.add(new Paragraph("Justificación: " + JtaJustificacion.getText().trim()));
            document.add(new Paragraph("Primera Inyección: " + st.format(jDate1.getDate())));
            document.add(new Paragraph("Próxima Inyección: " + st.format(jDate2.getDate())));
            document.close();
            //JOptionPane.showMessageDialog(null, "Archivo PDF creado correctamente", "Informacion", 1);

        } else {
            //JOptionPane.showMessageDialog(null, "Debe tener llenos todos los campos", "Atencion", 2);
        }
    }

    /*
        Abre el archivo PDF correspondiente al galpón seleccionado en la tabla
     */
    private void abrirpdf() {
        String galpon = txfGalponSeleccionado.getText().trim();
        try {
            File path = new File("Registro_Vacuna_Galpon_" + galpon + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Atencion", 2);
        }

    }

    /*
        Otros métodos
     */
 /*
    valida que los campos no estén vacíos
     */
    private boolean verficarEspacios() {
        return (txtNombre.getText().trim().length() > 0 && txtCadula.getText().trim().length() > 0
                && txtCelular.getText().trim().length() > 0 && txtCorreo.getText().trim().length() > 0
                && txtDirecion.getText().trim().length() > 0 && txtRolDescripcion.getText().trim().length() > 0);

    }

    /**
     * Buscar los galpones por fechas
     */
    private void listarporfecha() {
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = BusquedaDesde.getDate();
        Lista aux = new Lista();
        String fechaConvertida = dateformat.format(date);
        aux = galponController.buscarPorFechas(fechaConvertida);
        modeloG.setLista(aux);
        tablegalpones.setModel(modeloG);
        tablegalpones.updateUI();
    }
    /**
     * Ordena el numero de totales de pollos por quicksort
     */
    private void ordenarMortalidadQuicksort() {
        Lista aux = galponController.listarMortalidad();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblMortalidad.getColumnCount(); i++) {
            aux.ordenarQuicksort3(aux, 0, aux.tamanio()-1);
            tblMortalidad.getColumnModel().getColumn(i).setHeaderRenderer(tcr);
            tblMortalidad.getColumnModel().getColumn(i).setCellRenderer(tcr);
            modeloM.setLista(aux);
            tblMortalidad.setModel(modeloM);
        }
        tblMortalidad.updateUI();
    }
//    public void prueba() {
//        int seleccionar = tablaPersonas.getSelectedRow();
//        //String.valueOf(tablaPersonas.getValueAt(seleccionar, 2))
//        //int valor = tablaPersonas.getSelectedRow()+1;
//        //System.out.println("valor"+valor);
//
//        System.out.println(String.valueOf(tablaPersonas.getValueAt(seleccionar, 2)));
//
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        Cerrar = new javax.swing.JPanel();
        closetext = new javax.swing.JLabel();
        Navegador = new javax.swing.JPanel();
        Title = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BtnRegistroGalpon = new javax.swing.JPanel();
        registroGalpontxt = new javax.swing.JLabel();
        JPVacuna = new javax.swing.JPanel();
        vacunatxt = new javax.swing.JLabel();
        JPCerrarSesion = new javax.swing.JPanel();
        cerrarSesiontxt = new javax.swing.JLabel();
        JPRegMortalidad = new javax.swing.JPanel();
        mortalidadtxt = new javax.swing.JLabel();
        JPRAdministracion = new javax.swing.JPanel();
        administraciontxt = new javax.swing.JLabel();
        Fondo2 = new javax.swing.JPanel();
        jPanelSlider1 = new diu.swe.habib.JPanelSlider.JPanelSlider();
        JPFondo = new fondoPaneles();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        JLogotipo = new fondoLabel();
        jLabel21 = new javax.swing.JLabel();
        JPRegistroGalpones = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnumeropollo = new javax.swing.JTextField();
        txtraza = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablegalpones = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbTipoBalanceado = new javax.swing.JComboBox<>();
        jSeparator8 = new javax.swing.JSeparator();
        txtCtdBalanceadoSuministrada = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbFDAlimentacion = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDateFInicio = new com.toedter.calendar.JDateChooser();
        jDatefFin = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        BusquedaDesde = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        JPRegistroVacuna = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtnombreProducto = new javax.swing.JTextField();
        jDate1 = new com.toedter.calendar.JDateChooser();
        jDate2 = new com.toedter.calendar.JDateChooser();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        btnGuardarVacuna = new javax.swing.JButton();
        btnAbrirPdf = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbTipoFarmaco = new javax.swing.JComboBox<>();
        txfGalponSeleccionado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtaJustificacion = new javax.swing.JTextArea();
        txfDosis = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        cbMedidaDosis = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablevacuna = new javax.swing.JTable();
        btnActualizarVacuna = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtidvacuna = new javax.swing.JTextField();
        JPMortalidad = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txfGalponSeleccionado1 = new javax.swing.JTextField();
        txtCtdPollosActual = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtPollosFallecidos = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMortalidad = new javax.swing.JTable();
        btnRegistrarMortalidad = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtCadula = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDirecion = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtRolDescripcion = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        checkRolActivo = new javax.swing.JCheckBox();
        cbxTipoRol = new javax.swing.JComboBox<>();
        checkRolBoqueado = new javax.swing.JCheckBox();
        jLabel40 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        txtId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        bttEliminar = new javax.swing.JButton();
        bttLeer = new javax.swing.JButton();
        bttEditar = new javax.swing.JButton();
        bttGuardar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Fondo1.setBackground(new java.awt.Color(255, 255, 255));
        Fondo1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Fondo1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        closetext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closetext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closetext.setText("X");
        closetext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closetextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closetextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closetextMouseExited(evt);
            }
        });

        javax.swing.GroupLayout CerrarLayout = new javax.swing.GroupLayout(Cerrar);
        Cerrar.setLayout(CerrarLayout);
        CerrarLayout.setHorizontalGroup(
            CerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(closetext, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );
        CerrarLayout.setVerticalGroup(
            CerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(closetext, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(1056, Short.MAX_VALUE)
                .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(Cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Fondo1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 30));

        Navegador.setBackground(new java.awt.Color(102, 102, 255));
        Navegador.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Navegador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setBackground(new java.awt.Color(102, 51, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Panel de Control");

        javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
        Title.setLayout(TitleLayout);
        TitleLayout.setHorizontalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        TitleLayout.setVerticalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Navegador.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 50));

        BtnRegistroGalpon.setBackground(new java.awt.Color(102, 102, 255));
        BtnRegistroGalpon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnRegistroGalponMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnRegistroGalponMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRegistroGalponMouseExited(evt);
            }
        });

        registroGalpontxt.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        registroGalpontxt.setForeground(new java.awt.Color(255, 255, 255));
        registroGalpontxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registroGalpontxt.setText("Registro Galpón");

        javax.swing.GroupLayout BtnRegistroGalponLayout = new javax.swing.GroupLayout(BtnRegistroGalpon);
        BtnRegistroGalpon.setLayout(BtnRegistroGalponLayout);
        BtnRegistroGalponLayout.setHorizontalGroup(
            BtnRegistroGalponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BtnRegistroGalponLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(registroGalpontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BtnRegistroGalponLayout.setVerticalGroup(
            BtnRegistroGalponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BtnRegistroGalponLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registroGalpontxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Navegador.add(BtnRegistroGalpon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 180, 40));

        JPVacuna.setBackground(new java.awt.Color(102, 102, 255));
        JPVacuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPVacunaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPVacunaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPVacunaMouseExited(evt);
            }
        });

        vacunatxt.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        vacunatxt.setForeground(new java.awt.Color(255, 255, 255));
        vacunatxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vacunatxt.setText("Vacunas");

        javax.swing.GroupLayout JPVacunaLayout = new javax.swing.GroupLayout(JPVacuna);
        JPVacuna.setLayout(JPVacunaLayout);
        JPVacunaLayout.setHorizontalGroup(
            JPVacunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPVacunaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vacunatxt, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        JPVacunaLayout.setVerticalGroup(
            JPVacunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vacunatxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Navegador.add(JPVacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, 40));

        JPCerrarSesion.setBackground(new java.awt.Color(102, 102, 255));
        JPCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPCerrarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPCerrarSesionMouseExited(evt);
            }
        });

        cerrarSesiontxt.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        cerrarSesiontxt.setForeground(new java.awt.Color(255, 255, 255));
        cerrarSesiontxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cerrarSesiontxt.setText("Cerrar Sesión");

        javax.swing.GroupLayout JPCerrarSesionLayout = new javax.swing.GroupLayout(JPCerrarSesion);
        JPCerrarSesion.setLayout(JPCerrarSesionLayout);
        JPCerrarSesionLayout.setHorizontalGroup(
            JPCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cerrarSesiontxt, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        JPCerrarSesionLayout.setVerticalGroup(
            JPCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cerrarSesiontxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Navegador.add(JPCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 190, 40));

        JPRegMortalidad.setBackground(new java.awt.Color(102, 102, 255));
        JPRegMortalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPRegMortalidadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPRegMortalidadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPRegMortalidadMouseExited(evt);
            }
        });

        mortalidadtxt.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        mortalidadtxt.setForeground(new java.awt.Color(255, 255, 255));
        mortalidadtxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mortalidadtxt.setText("Registro Mortalidad");

        javax.swing.GroupLayout JPRegMortalidadLayout = new javax.swing.GroupLayout(JPRegMortalidad);
        JPRegMortalidad.setLayout(JPRegMortalidadLayout);
        JPRegMortalidadLayout.setHorizontalGroup(
            JPRegMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPRegMortalidadLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(mortalidadtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPRegMortalidadLayout.setVerticalGroup(
            JPRegMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mortalidadtxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Navegador.add(JPRegMortalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 180, 40));

        JPRAdministracion.setBackground(new java.awt.Color(102, 102, 255));
        JPRAdministracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPRAdministracionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPRAdministracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPRAdministracionMouseExited(evt);
            }
        });

        administraciontxt.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        administraciontxt.setForeground(new java.awt.Color(255, 255, 255));
        administraciontxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administraciontxt.setText("Administracion");
        administraciontxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                administraciontxtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JPRAdministracionLayout = new javax.swing.GroupLayout(JPRAdministracion);
        JPRAdministracion.setLayout(JPRAdministracionLayout);
        JPRAdministracionLayout.setHorizontalGroup(
            JPRAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPRAdministracionLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(administraciontxt, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPRAdministracionLayout.setVerticalGroup(
            JPRAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(administraciontxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        Navegador.add(JPRAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 40));

        Fondo1.add(Navegador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 520));

        Fondo2.setLayout(null);

        jPanelSlider1.setBorder(null);

        JPFondo.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelHora1.setFont(new java.awt.Font("MS UI Gothic", 1, 48)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Dirección: Olmedo y Eugenio Espejo");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Teléfono de Contacto: 0959457834");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Correo Electrónico: granjaavicola_sanatonio@hotmail.com");

        javax.swing.GroupLayout JPFondoLayout = new javax.swing.GroupLayout(JPFondo);
        JPFondo.setLayout(JPFondoLayout);
        JPFondoLayout.setHorizontalGroup(
            JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPFondoLayout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPFondoLayout.createSequentialGroup()
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPFondoLayout.createSequentialGroup()
                        .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
        );
        JPFondoLayout.setVerticalGroup(
            JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPFondoLayout.createSequentialGroup()
                .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPFondoLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(JLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPFondoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(19, 19, 19))
        );

        jPanelSlider1.add(JPFondo, "card3");

        JPRegistroGalpones.setBackground(new java.awt.Color(255, 255, 255));
        JPRegistroGalpones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JPRegistroGalpones.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel5.setText("Número de Pollos");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(20, 140, 110, 15);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel6.setText("veces");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(710, 20, 40, 15);

        txtnumeropollo.setBorder(null);
        jPanel4.add(txtnumeropollo);
        txtnumeropollo.setBounds(160, 130, 140, 30);

        txtraza.setBorder(null);
        jPanel4.add(txtraza);
        txtraza.setBounds(160, 190, 150, 30);
        jPanel4.add(jSeparator2);
        jSeparator2.setBounds(160, 160, 150, 10);
        jPanel4.add(jSeparator3);
        jSeparator3.setBounds(160, 220, 150, 10);

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);
        btnGuardar.setBounds(540, 430, 130, 30);

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizar);
        btnActualizar.setBounds(330, 430, 110, 30);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar);
        btnEliminar.setBounds(80, 430, 130, 30);

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
        tablegalpones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablegalponesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablegalpones);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(20, 290, 800, 120);

        jLabel8.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel8.setText("Tipo de Balanceado:");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(20, 20, 120, 15);

        cbTipoBalanceado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Engorde", "Crecimiento" }));
        jPanel4.add(cbTipoBalanceado);
        cbTipoBalanceado.setBounds(150, 10, 100, 30);
        jPanel4.add(jSeparator8);
        jSeparator8.setBounds(160, 90, 150, 10);

        txtCtdBalanceadoSuministrada.setBorder(null);
        jPanel4.add(txtCtdBalanceadoSuministrada);
        txtCtdBalanceadoSuministrada.setBounds(160, 60, 140, 30);

        jLabel9.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel9.setText("Cantidad Suministrada:");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(20, 70, 140, 15);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Frecuencia Diaria de Alimentación:");
        jLabel2.setMaximumSize(new java.awt.Dimension(114, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(114, 16));
        jPanel4.add(jLabel2);
        jLabel2.setBounds(450, 20, 200, 16);

        cbFDAlimentacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4" }));
        jPanel4.add(cbFDAlimentacion);
        cbFDAlimentacion.setBounds(660, 10, 40, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Periodo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel7.setText("Fecha Inicio:");

        jLabel10.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel10.setText("Fecha Fin:");

        jDateFInicio.setDateFormatString("yyyy-MM-dd");

        jDatefFin.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateFInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDatefFin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jDatefFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))))
        );

        jPanel4.add(jPanel1);
        jPanel1.setBounds(450, 70, 310, 150);

        jLabel22.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel22.setText("Raza");
        jPanel4.add(jLabel22);
        jLabel22.setBounds(20, 210, 40, 15);

        jLabel30.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel30.setText("Búsqueda de Galpón");
        jPanel4.add(jLabel30);
        jLabel30.setBounds(230, 250, 140, 15);

        BusquedaDesde.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(BusquedaDesde);
        BusquedaDesde.setBounds(370, 244, 160, 30);

        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);
        jButton2.setBounds(540, 250, 70, 24);

        JPRegistroGalpones.add(jPanel4);
        jPanel4.setBounds(10, 40, 840, 470);

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRO DE GALPONES");
        JPRegistroGalpones.add(jLabel3);
        jLabel3.setBounds(300, 0, 240, 30);
        JPRegistroGalpones.add(txtid);
        txtid.setBounds(30, 10, 50, 24);

        jPanelSlider1.add(JPRegistroGalpones, "card2");

        JPRegistroVacuna.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setLayout(null);

        txtnombreProducto.setBorder(null);
        jPanel2.add(txtnombreProducto);
        txtnombreProducto.setBounds(140, 40, 250, 30);
        jPanel2.add(jDate1);
        jDate1.setBounds(590, 160, 160, 30);
        jPanel2.add(jDate2);
        jDate2.setBounds(590, 200, 160, 30);
        jPanel2.add(jSeparator6);
        jSeparator6.setBounds(140, 30, 70, 10);

        jLabel18.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel18.setText("Tipo de Producto:");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(10, 90, 100, 15);

        btnGuardarVacuna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarVacuna.setText("Guardar");
        btnGuardarVacuna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarVacuna.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVacunaActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardarVacuna);
        btnGuardarVacuna.setBounds(140, 250, 100, 40);

        btnAbrirPdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAbrirPdf.setText("Abrir PDF");
        btnAbrirPdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirPdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrirPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPdfActionPerformed(evt);
            }
        });
        jPanel2.add(btnAbrirPdf);
        btnAbrirPdf.setBounds(410, 250, 110, 40);

        jLabel19.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel19.setText("Galpón Seleccionado:");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(10, 10, 130, 15);

        jLabel23.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel23.setText("Próxima:");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(490, 210, 80, 15);

        cbTipoFarmaco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fármaco", "Biológico" }));
        jPanel2.add(cbTipoFarmaco);
        cbTipoFarmaco.setBounds(140, 80, 120, 30);

        txfGalponSeleccionado.setEditable(false);
        txfGalponSeleccionado.setBackground(new java.awt.Color(255, 255, 255));
        txfGalponSeleccionado.setBorder(null);
        jPanel2.add(txfGalponSeleccionado);
        txfGalponSeleccionado.setBounds(140, 0, 60, 30);

        jLabel24.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel24.setText("Nombre Producto:");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(10, 50, 120, 15);
        jPanel2.add(jSeparator10);
        jSeparator10.setBounds(140, 70, 250, 10);

        JtaJustificacion.setColumns(20);
        JtaJustificacion.setRows(5);
        jScrollPane1.setViewportView(JtaJustificacion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(140, 130, 260, 100);

        txfDosis.setBorder(null);
        jPanel2.add(txfDosis);
        txfDosis.setBounds(620, 30, 60, 30);
        jPanel2.add(jSeparator7);
        jSeparator7.setBounds(620, 60, 70, 10);

        jLabel26.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel26.setText("Dosis:");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(490, 40, 50, 15);

        cbMedidaDosis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "g", "mg", "ml" }));
        jPanel2.add(cbMedidaDosis);
        cbMedidaDosis.setBounds(710, 30, 60, 30);

        jLabel25.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel25.setText("Justificación:");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(10, 130, 90, 15);

        jLabel27.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel27.setText("Administra:");
        jPanel2.add(jLabel27);
        jLabel27.setBounds(490, 90, 80, 15);

        jLabel28.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel28.setText("Primera:");
        jPanel2.add(jLabel28);
        jLabel28.setBounds(490, 170, 80, 15);

        jLabel29.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel29.setText("Fecha Vacunas");
        jPanel2.add(jLabel29);
        jLabel29.setBounds(610, 130, 110, 15);

        tablevacuna.setModel(new javax.swing.table.DefaultTableModel(
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
        tablevacuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablevacunaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablevacuna);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(20, 310, 820, 150);

        btnActualizarVacuna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnActualizarVacuna.setText("Actualizar");
        btnActualizarVacuna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizarVacuna.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarVacunaActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizarVacuna);
        btnActualizarVacuna.setBounds(270, 250, 110, 40);

        jLabel16.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("REGISTRO DE VACUNAS");

        javax.swing.GroupLayout JPRegistroVacunaLayout = new javax.swing.GroupLayout(JPRegistroVacuna);
        JPRegistroVacuna.setLayout(JPRegistroVacunaLayout);
        JPRegistroVacunaLayout.setHorizontalGroup(
            JPRegistroVacunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPRegistroVacunaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtidvacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
            .addGroup(JPRegistroVacunaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPRegistroVacunaLayout.setVerticalGroup(
            JPRegistroVacunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPRegistroVacunaLayout.createSequentialGroup()
                .addGroup(JPRegistroVacunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidvacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelSlider1.add(JPRegistroVacuna, "card4");

        JPMortalidad.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("REGISTRO DE MORTALIDAD DE POLLOS");

        jLabel33.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel33.setText("Id Galpón:");

        jLabel34.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel34.setText("Cantidad de Pollos:");

        jLabel35.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel35.setText("Pollos Fallecidos:");

        txfGalponSeleccionado1.setEditable(false);
        txfGalponSeleccionado1.setBackground(new java.awt.Color(255, 255, 255));
        txfGalponSeleccionado1.setBorder(null);

        txtCtdPollosActual.setBorder(null);

        txtPollosFallecidos.setBorder(null);

        tblMortalidad.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblMortalidad);

        btnRegistrarMortalidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRegistrarMortalidad.setText("Registrar");
        btnRegistrarMortalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarMortalidadActionPerformed(evt);
            }
        });

        jButton3.setText("Ordenar por Total");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPMortalidadLayout = new javax.swing.GroupLayout(JPMortalidad);
        JPMortalidad.setLayout(JPMortalidadLayout);
        JPMortalidadLayout.setHorizontalGroup(
            JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPMortalidadLayout.createSequentialGroup()
                .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txfGalponSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPMortalidadLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtCtdPollosActual, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPMortalidadLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtPollosFallecidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(600, 600, 600)
                        .addComponent(btnRegistrarMortalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPMortalidadLayout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(317, 317, 317))
        );
        JPMortalidadLayout.setVerticalGroup(
            JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPMortalidadLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33))
                    .addComponent(txfGalponSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel34))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addComponent(txtCtdPollosActual, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(JPMortalidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel35))
                    .addGroup(JPMortalidadLayout.createSequentialGroup()
                        .addComponent(txtPollosFallecidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRegistrarMortalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelSlider1.add(JPMortalidad, "card5");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtNombre.setBorder(null);

        txtCadula.setBorder(null);

        txtCelular.setBorder(null);

        txtCorreo.setBorder(null);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Cédula:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Celular:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Correo:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Dirección");

        txtDirecion.setColumns(20);
        txtDirecion.setRows(5);
        jScrollPane5.setViewportView(txtDirecion);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Apellido:");

        txtApellido.setBorder(null);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Contraseña:");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Rol", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Tipo");

        txtRolDescripcion.setBorder(null);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Descripcion");

        checkRolActivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        cbxTipoRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Gerente", "Desarrollador" }));

        checkRolBoqueado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkRolBoqueado.setText("Bloqueado");
        checkRolBoqueado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRolBoqueadoActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Estado");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(cbxTipoRol, 0, 138, Short.MAX_VALUE)
                    .addComponent(checkRolActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkRolBoqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRolDescripcion))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(cbxTipoRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39)
                    .addComponent(txtRolDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addComponent(checkRolActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkRolBoqueado)
                .addContainerGap())
        );

        txtPassword.setBorder(null);

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setBorder(null);
        txtId.setEnabled(false);

        jButton1.setText("Limpiar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bttEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEliminar.setText("Elminar");
        bttEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEliminarActionPerformed(evt);
            }
        });

        bttLeer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttLeer.setText("Leer");
        bttLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLeerActionPerformed(evt);
            }
        });

        bttEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttEditar.setText("Editar");
        bttEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttEditarActionPerformed(evt);
            }
        });

        bttGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bttGuardar.setText("Crear");
        bttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(39, 39, 39)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(bttGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(bttEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(bttLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(bttEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel37)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36)
                                            .addComponent(jLabel12)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel4)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCadula, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(311, 311, 311)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(39, 39, 39)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(606, 606, 606)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(33, 33, 33)
                                .addComponent(jLabel36)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel12))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtCadula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14))
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bttGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bttLeer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addComponent(jButton1))
        );

        jPanel3.add(jPanel5);
        jPanel5.setBounds(10, 10, 850, 340);

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
        jScrollPane6.setViewportView(tablaPersonas);

        jPanel3.add(jScrollPane6);
        jScrollPane6.setBounds(20, 360, 800, 140);

        jPanelSlider1.add(jPanel3, "card6");

        Fondo2.add(jPanelSlider1);
        jPanelSlider1.setBounds(0, 0, 870, 520);

        Fondo1.add(Fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 860, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1101, 574));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /*
        Botones y eventos 
     */
    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        // TODO add your handling code here:
        xmouse = evt.getX();
        ymouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xmouse, y - ymouse);
    }//GEN-LAST:event_headerMouseDragged

    private void closetextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closetextMouseClicked
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Cerrar?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (opcion == JOptionPane.NO_OPTION) {
        }
    }//GEN-LAST:event_closetextMouseClicked

    private void closetextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closetextMouseEntered
        // TODO add your handling code here:
        closetext.setForeground(Color.black);
        Cerrar.setBackground(Color.red);
        closetext.setForeground(Color.WHITE);
    }//GEN-LAST:event_closetextMouseEntered

    private void closetextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closetextMouseExited
        // TODO add your handling code here:
        Cerrar.setBackground(Color.white);
        closetext.setForeground(Color.black);
    }//GEN-LAST:event_closetextMouseExited

    private void BtnRegistroGalponMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroGalponMouseEntered
        BtnRegistroGalpon.setBackground(new Color(153, 153, 255));
        registroGalpontxt.setForeground(Color.WHITE);

    }//GEN-LAST:event_BtnRegistroGalponMouseEntered

    private void BtnRegistroGalponMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroGalponMouseExited

        BtnRegistroGalpon.setBackground(new Color(102, 102, 255));
        registroGalpontxt.setForeground(Color.WHITE);

    }//GEN-LAST:event_BtnRegistroGalponMouseExited

    private void BtnRegistroGalponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroGalponMouseClicked
        jPanelSlider1.nextPanel(2, 0, JPRegistroGalpones, jPanelSlider1.right);
    }//GEN-LAST:event_BtnRegistroGalponMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Updategalpon();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarGalpon();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void JPVacunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPVacunaMouseClicked
        // TODO add your handling code here:
        jPanelSlider1.nextPanel(2, 0, JPRegistroVacuna, jPanelSlider1.right);
    }//GEN-LAST:event_JPVacunaMouseClicked

    private void JPVacunaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPVacunaMouseEntered
        // TODO add your handling code here:
        JPVacuna.setBackground(new Color(153, 153, 255));
        vacunatxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPVacunaMouseEntered

    private void JPVacunaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPVacunaMouseExited
        // TODO add your handling code here:
        JPVacuna.setBackground(new Color(102, 102, 255));
        vacunatxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPVacunaMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        // TODO add your handling code here:
        btnGuardar.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        // TODO add your handling code here:
        btnGuardar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        // TODO add your handling code here:
        btnActualizar.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        btnActualizar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnGuardarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVacunaActionPerformed
        try {
            guardarVacuna();
            generarPDF();
            limpiardatosVacuna();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarVacunaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        deleteGalpon();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablegalponesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablegalponesMouseClicked
        seleccionar();
    }//GEN-LAST:event_tablegalponesMouseClicked

    private void btnActualizarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarVacunaActionPerformed
        updateVacuna();

    }//GEN-LAST:event_btnActualizarVacunaActionPerformed

    private void tablevacunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablevacunaMouseClicked
        seleccionarVacuna();
    }//GEN-LAST:event_tablevacunaMouseClicked

    private void btnAbrirPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPdfActionPerformed
        abrirpdf();
    }//GEN-LAST:event_btnAbrirPdfActionPerformed

    private void JPCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCerrarSesionMouseClicked
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(null, "Esta Seguro de Cerrar Sesión", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
            Login log = new Login();
            log.setVisible(true);
        } else if (opcion == JOptionPane.NO_OPTION) {
        }

    }//GEN-LAST:event_JPCerrarSesionMouseClicked

    private void JPCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCerrarSesionMouseEntered
        // TODO add your handling code here:
        JPCerrarSesion.setBackground(new Color(153, 153, 255));
        cerrarSesiontxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPCerrarSesionMouseEntered

    private void JPCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPCerrarSesionMouseExited
        // TODO add your handling code here:
        JPCerrarSesion.setBackground(new Color(102, 102, 255));
        cerrarSesiontxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPCerrarSesionMouseExited

    private void JPRegMortalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRegMortalidadMouseClicked
        // TODO add your handling code here:
        jPanelSlider1.nextPanel(2, 0, JPMortalidad, jPanelSlider1.left);
    }//GEN-LAST:event_JPRegMortalidadMouseClicked

    private void JPRegMortalidadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRegMortalidadMouseEntered
        // TODO add your handling code here:
        JPRegMortalidad.setBackground(new Color(153, 153, 255));
        mortalidadtxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPRegMortalidadMouseEntered

    private void JPRegMortalidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRegMortalidadMouseExited
        // TODO add your handling code here:
        JPRegMortalidad.setBackground(new Color(102, 102, 255));
        mortalidadtxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPRegMortalidadMouseExited

    private void btnRegistrarMortalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarMortalidadActionPerformed
        // TODO add your handling code here:
        guardarMortalidadPollo();
    }//GEN-LAST:event_btnRegistrarMortalidadActionPerformed

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
            Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bttLeerActionPerformed

    private void bttEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_bttEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        limpiarAdministracion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JPRAdministracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRAdministracionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JPRAdministracionMouseClicked

    private void JPRAdministracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRAdministracionMouseEntered
        JPRAdministracion.setBackground(new Color(153, 153, 255));
        administraciontxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPRAdministracionMouseEntered

    private void JPRAdministracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPRAdministracionMouseExited
        JPRAdministracion.setBackground(new Color(102, 102, 255));
        administraciontxt.setForeground(Color.WHITE);
    }//GEN-LAST:event_JPRAdministracionMouseExited

    private void administraciontxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_administraciontxtMouseClicked
        jPanelSlider1.nextPanel(2, 0, jPanel3, jPanelSlider1.left);
    }//GEN-LAST:event_administraciontxtMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listarporfecha();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ordenarMortalidadQuicksort();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_PrincipalMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frm_PrincipalMenu dialog = new Frm_PrincipalMenu(new javax.swing.JFrame(), true);
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

    /*
        Agrega una imagen al JPanel
     */
    class fondoPaneles extends JPanel {

        private Image pollo;

        @Override
        public void paint(Graphics g) {
            pollo = new ImageIcon(getClass().getResource("/Image/fondo-granja.png")).getImage();
            g.drawImage(pollo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }

    }

    /*
    Agrega una imagen al JLabel
     */
    class fondoLabel extends JLabel {

        private Image logo;

        @Override
        public void paint(Graphics g) {
            logo = new ImageIcon(getClass().getResource("/Image/logotipo-granja.png")).getImage();
            g.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtnRegistroGalpon;
    private com.toedter.calendar.JDateChooser BusquedaDesde;
    private javax.swing.JPanel Cerrar;
    private javax.swing.JPanel Fondo1;
    private javax.swing.JPanel Fondo2;
    private javax.swing.JLabel JLogotipo;
    private javax.swing.JPanel JPCerrarSesion;
    private javax.swing.JPanel JPFondo;
    private javax.swing.JPanel JPMortalidad;
    private javax.swing.JPanel JPRAdministracion;
    private javax.swing.JPanel JPRegMortalidad;
    private javax.swing.JPanel JPRegistroGalpones;
    private javax.swing.JPanel JPRegistroVacuna;
    private javax.swing.JPanel JPVacuna;
    private javax.swing.JTextArea JtaJustificacion;
    private javax.swing.JPanel Navegador;
    private javax.swing.JPanel Title;
    private javax.swing.JLabel administraciontxt;
    private javax.swing.JButton btnAbrirPdf;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarVacuna;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarVacuna;
    private javax.swing.JButton btnRegistrarMortalidad;
    private javax.swing.JButton bttEditar;
    private javax.swing.JButton bttEliminar;
    private javax.swing.JButton bttGuardar;
    private javax.swing.JButton bttLeer;
    private javax.swing.JComboBox<String> cbFDAlimentacion;
    private javax.swing.JComboBox<String> cbMedidaDosis;
    private javax.swing.JComboBox<String> cbTipoBalanceado;
    private javax.swing.JComboBox<String> cbTipoFarmaco;
    private javax.swing.JComboBox<String> cbxTipoRol;
    private javax.swing.JLabel cerrarSesiontxt;
    private javax.swing.JCheckBox checkRolActivo;
    private javax.swing.JCheckBox checkRolBoqueado;
    private javax.swing.JLabel closetext;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDate1;
    private com.toedter.calendar.JDateChooser jDate2;
    private com.toedter.calendar.JDateChooser jDateFInicio;
    private com.toedter.calendar.JDateChooser jDatefFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private diu.swe.habib.JPanelSlider.JPanelSlider jPanelSlider1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel mortalidadtxt;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private javax.swing.JLabel registroGalpontxt;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JTable tablegalpones;
    private javax.swing.JTable tablevacuna;
    private javax.swing.JTable tblMortalidad;
    private javax.swing.JTextField txfDosis;
    private javax.swing.JTextField txfGalponSeleccionado;
    private javax.swing.JTextField txfGalponSeleccionado1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCadula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCtdBalanceadoSuministrada;
    private javax.swing.JTextField txtCtdPollosActual;
    private javax.swing.JTextArea txtDirecion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPollosFallecidos;
    private javax.swing.JTextField txtRolDescripcion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtidvacuna;
    private javax.swing.JTextField txtnombreProducto;
    private javax.swing.JTextField txtnumeropollo;
    private javax.swing.JTextField txtraza;
    private javax.swing.JLabel vacunatxt;
    // End of variables declaration//GEN-END:variables
}
