/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

import Controller.ConexionBaseDatos;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import lista.Controller.Lista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Galpon;

/**
 *
 * @author Home
 * @param <T>
 */
public class AdaptadorGalponDao<T> implements Operaciones<T> {

    private Class<T> clazz;
    private Lista<T> lista = new Lista();
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;

//    public AdaptadorGalponDao(Class<T> clazz) {
//        this.clazz = clazz;
//        lista.setClazz(clazz);
//    }
    @Override
    public boolean guardar(T dato) {
        Galpon galpon = (Galpon) dato;
        Connection con = c.conectar();
        String sql = "INSERT INTO galpones(pollos,raza) VALUE(?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            //ps.setInt(1, galpon.getId());
            ps.setString(1, galpon.getNumPollo());
            ps.setString(2, galpon.getRaza());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Galpon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean modificar(Object object) {
        Galpon galpon = (Galpon) object;
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE galpones SET pollos =?, raza=? WHERE id_galpon =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, galpon.getNumPollo());
            System.out.println(galpon.getNumPollo());
            pst.setString(2, galpon.getRaza());
            System.out.println(galpon.getRaza());
            pst.setInt(3, galpon.getId());
            pst.executeUpdate();
            return true;
//            if (filas > 0) {
//                con.close();
//                return true;
//            } else {
//                con.close();
//                return false;
//            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrido el siguiente error: " + e.getMessage());
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
            rs = st.executeQuery("SELECT * FROM galpones");
            while (rs.next()) {
                Galpon galpon = new Galpon();
                galpon.setId(rs.getInt("id_galpon"));
                galpon.setNumPollo(rs.getString("pollos"));
                galpon.setRaza(rs.getString("raza"));
                lista.insertarNodo((T) galpon);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

}
