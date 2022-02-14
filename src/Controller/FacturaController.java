/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lista.Controller.Lista;
import modelo.Factura;

/**
 *
 * @author John
 */
public class FacturaController<T> implements CRUD {

    /**
     * Una lista de facturas
     */
    private Lista<Factura> facturas = new Lista();
    /**
     * modelo de factura
     */
    private Factura factura;
    /**
     * una lista tipo T
     */
    private Lista<T> lista = new Lista();
    /**
     * variable para establecer la conexion a base de datos
     */
    ConexionBaseDatos c = new ConexionBaseDatos();
    /**
     * variable para establecer la conexion a base de datos
     */
    Statement st;
    /**
     * variable para establecer la conexion a base de datos
     */
    ResultSet rs;

    /**
     * Permite obtener la lista tipo factura
     *
     * @return lista factura
     */
    public Lista<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Permite ingresar los datos de la lista de facturas
     *
     * @param facturas
     */
    public void setFacturas(Lista<Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * Permite obtener la factura como objeto
     *
     * @return factura
     */
    public Factura getFactura() {
        if (factura == null) {
            factura = new Factura();
        }
        return factura;
    }

    /**
     * Permite ingresar los datos almacenados en el objeto factura
     *
     * @param factura
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Permite guardar los datos de factura en la base de datos
     *
     * @return boolean (true si se hace correctamente/false si ocurre un error)
     */
    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO factura(id_factura,cliente,cedula, direccion, telefono, fecha_emision, cantidad, descripcion, precio_unitario, total) VALUE(?,?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(factura.getFechaEmision());
        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, factura.getId());
            ps.setString(2, factura.getNombreCliente());
            ps.setString(3, factura.getCedula());
            ps.setString(4, factura.getDireccion());
            ps.setString(5, factura.getTelefono());
            ps.setDate(6, date1);
            ps.setString(7, String.valueOf(factura.getCantidad()));
            ps.setString(8, factura.getDescripcionProducto());
            ps.setString(9, String.valueOf(factura.getPrecioUnitario()));
            ps.setString(10, String.valueOf(factura.getTotal()));
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * construtor por default
     *
     * @return false
     */
    @Override
    public boolean Update() {
        return false;
    }

    /**
     * construtor por default
     *
     * @return false
     */
    @Override
    public boolean Delete() {
        return false;
    }

    /**
     * Permite obtener los datos de la base de datos y almacenarlos en una lista
     *
     * @return lista tipo T
     */
    @Override
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM factura");
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setId(rs.getInt("id_factura"));
                factura.setNombreCliente(rs.getString("cliente"));
                factura.setDireccionEmpresa(rs.getString("direccion"));
                factura.setCedula(rs.getString("cedula"));
                factura.setTelefonoE(rs.getString("telefono"));
                factura.setFechaEmision(rs.getDate("fecha_emision"));
                factura.setCantidad(Integer.valueOf(rs.getString("cantidad")));
                factura.setDescripcionProducto(rs.getString("descripcion"));
                factura.setPrecioUnitario(Double.valueOf(rs.getString("precio_unitario")));
                factura.setTotal(Double.valueOf(rs.getString("total")));
                lista.insertarNodo((T) factura);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    /**
     * Permite realizar una busqueda Secuencial en base a una cadena obtenida
     * por el usuario
     *
     * @param dato
     * @return lista tipo factura
     */
    public Lista<Factura> buscarFactura(String dato) {
        Lista<Factura> lista = new Lista();
        Lista<Factura> aux = (Lista<Factura>) listar();
        for (int i = 0; i < listar().tamanio(); i++) {
            Factura f = aux.consultarDatoPosicion(i);
            if (f.getNombreCliente().toLowerCase().contains(dato.toLowerCase())) {
                lista.insertarNodo(f);
            }
        }
        return lista;
    }
}
