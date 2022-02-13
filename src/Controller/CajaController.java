/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.itextpdf.text.log.Logger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.lang.System.Logger.Level;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import lista.Controller.Lista;
import modelo.Caja;
import modelo.DetalleFactura;

/**
 *
 * @author Home
 */
public class CajaController<T> implements CRUD{
    private Caja caja;
    Lista<Caja> cajas = new Lista<>();
    private Lista<T> lista = new Lista();
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;
    
    public Caja getCaja() {
        if (caja == null) {
            caja = new Caja();
        }
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Lista<Caja> getCajas() {
        return cajas;
    }

    public void setCajas(Lista<Caja> cajas) {
        this.cajas = cajas;
    }
    

    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO caja(id_caja,ingresos,egresos, ganancia) VALUE(?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, caja.getId_caja());
            ps.setDouble(2, caja.getIngresos());
            ps.setDouble(3, caja.getEgresos());
            ps.setDouble(4, caja.getGanancia());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean Update() {
        PreparedStatement pst;
        java.sql.Connection con = c.conectar();
        String sql = ("UPDATE caja SET ingresos =?, egresos =?, ganancia =? WHERE id_caja =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setDouble(1, caja.getIngresos());
            pst.setDouble(2, caja.getEgresos());
            pst.setDouble(3, caja.getGanancia());
            pst.setInt(4, caja.getId_caja());
            pst.executeUpdate();
            pst.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrido el siguiente error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM caja");
            while (rs.next()) {
                Caja caja = new Caja();
                caja.setId_caja(rs.getInt("id_caja"));
                caja.setIngresos(rs.getDouble("ingresos"));
                caja.setEgresos(rs.getDouble("egresos"));
                caja.setGanancia(rs.getDouble("ganancia"));
                lista.insertarNodo((T)caja);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
