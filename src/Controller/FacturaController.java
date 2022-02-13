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
 * @author Home
 */
public class FacturaController<T> implements CRUD {

    private Lista<Factura> facturas = new Lista();
    private Factura factura;
    private Lista<T> lista = new Lista();
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;

    public Lista<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Lista<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFactura() {
        if (factura == null) {
            factura = new Factura();
        }
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public boolean Save() {
        //Factura factura = (Factura) dato;
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
            ps.setString (10, String.valueOf(factura.getTotal()));
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Update() {        
        return false;
    }

    @Override
    public boolean Delete() {
        //p = (Paciente) obj;
        PreparedStatement ps = null;
        Connection con = c.conectar();
        String sql = ("DELETE FROM factura WHERE id_factura = ?");
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, Math.toIntExact(factura.getId()));
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrido el siguiente erro: "+ e.getMessage());
            return false;
        }
    }

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
    
    public Lista<Factura> buscarFactura(String dato){
        Lista<Factura> lista = new Lista();
        Lista<Factura> aux = (Lista<Factura>) listar();
        for (int i = 0; i < listar().tamanio(); i++) {
            Factura g = aux.consultarDatoPosicion(i);
            if (g.getCedula().contains(dato)) {
                lista.insertarNodo(g);
                System.out.println(lista);
            }
        }
        return lista;
    }
}
