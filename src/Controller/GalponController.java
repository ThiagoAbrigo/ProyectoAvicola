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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lista.Controller.Lista;
import modelo.Galpon;

/**
 *
 * @author Home
 */
public class GalponController<T> implements CRUD {

    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
    private Date fechamod = new Date();
    private Lista<Galpon> galpones = new Lista();
    private Galpon galpon;
    private Lista<T> lista = new Lista();
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;

    public Lista<Galpon> getGalpones() {
        return galpones;
    }

    public void setGalpones(Lista<Galpon> galpones) {
        this.galpones = galpones;
    }

    public Galpon getGalpon() {
        if (galpon == null) {
            galpon = new Galpon();
        }
        return galpon;
    }

    public void setGalpon(Galpon galpon) {
        this.galpon = galpon;
    }

    @Override
    public boolean Save() {
        //Galpon galpon = (Galpon) dato;
        Connection con = c.conectar();
        String sql = "INSERT INTO galpones(id_galpon,pollos,raza, cant_Suministrada, Tipo_Balanceado, alimentacion, creacion, finaliza) VALUE(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, galpon.getId());
            ps.setInt(2, galpon.getNumPollo());
            ps.setString(3, galpon.getRaza());
            ps.setInt(4, galpon.getCtdSuministrada());
            ps.setString(5, galpon.getTbalanceado());
            ps.setInt(6, galpon.getfDiarioAlimentacion());
            ps.setString(7, std.format(galpon.getfIncio()));
            ps.setString(8, std.format(galpon.getfFin()));

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Galpon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Update() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE galpones SET pollos =?, raza =?, cant_Suministrada =?, Tipo_Balanceado =?, alimentacion =?, creacion =?, finaliza=? WHERE id_galpon =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, galpon.getNumPollo());
            pst.setString(2, galpon.getRaza());
            pst.setInt(3, galpon.getCtdSuministrada());
            pst.setString(4, galpon.getTbalanceado());
            pst.setInt(5, galpon.getfDiarioAlimentacion());
            pst.setString(6, std.format(galpon.getfIncio()));
            pst.setString(7, std.format(galpon.getfFin()));
            pst.setInt(8, galpon.getId());
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
        //p = (Paciente) obj;
        PreparedStatement ps = null;
        Connection con = c.conectar();
        String sql = ("DELETE FROM galpones WHERE id_galpon = ?");
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, galpon.getId());
            ps.executeUpdate();
            con.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrido el siguiente erro: " + e.getMessage());
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
                galpon.setNumPollo(rs.getInt("pollos"));
                galpon.setRaza(rs.getString("raza"));
                galpon.setCtdSuministrada(rs.getInt("cant_Suministrada"));
                galpon.setTbalanceado(rs.getString("Tipo_Balanceado"));
                galpon.setfDiarioAlimentacion(rs.getInt("alimentacion"));
                galpon.setfIncio(rs.getDate("creacion"));
                galpon.setfFin(rs.getDate("finaliza"));
                lista.insertarNodo((T) galpon);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public boolean SaveMortalidad() {
        //Galpon galpon = (Galpon) dato;
        Connection con = c.conectar();
        String sql = "INSERT INTO mortalidad(id_galpon,pollos_actuales,pollos_fallecidos,total) VALUE(?,?,?,?)";

        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, galpon.getId());
            ps.setInt(2, galpon.getNumPollo());
            ps.setInt(3, galpon.getPollosMuertos());
            ps.setInt(4, galpon.getExistencias());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Galpon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Lista<T> listarMortalidad() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM mortalidad");
            while (rs.next()) {
                Galpon galpon = new Galpon();
                galpon.setId(rs.getInt("id_galpon"));
                galpon.setNumPollo(rs.getInt("pollos_actuales"));
                galpon.setPollosMuertos(rs.getInt("pollos_fallecidos"));
                galpon.setExistencias(rs.getInt("total"));

                lista.insertarNodo((T) galpon);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public boolean Updategalpon() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE galpones SET pollos =? WHERE id_galpon =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, galpon.getNumPollo());
            pst.setInt(2, galpon.getId());
            pst.executeUpdate();
            pst.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrido el siguiente error: " + e.getMessage());
            return false;
        }
    }
//    public Lista<Galpon> buscargalpon(Date dato1, Date dato2){
//        Lista<Galpon> lista = new Lista();
//
//        Lista<Galpon> aux = (Lista<Galpon>) listarporFecha(dato1, dato2);
//        for (int i = 0; i < listarporFecha(dato1, dato2).tamanio(); i++) {
//            Galpon g = aux.consultarDatoPosicion(i);
//            if (g.getfIncio().equals(dato1)||g.getfIncio().equals(dato2)) {
//                lista.insertarNodo(g);
//                System.out.println(lista);
//            }
//        }
//        return lista;
//    }

//        public Lista<Galpon> listarporFecha(Date fecha1, Date fecha2) {
//        st = null;
//        rs = null;
//        lista = new Lista();
//        try {
//            Connection con = c.conectar();
//            st = (Statement) con.createStatement();
//            rs = st.executeQuery("SELECT * FROM galpones");
//            
//            while (rs.next()) {
//                Galpon galpon = new Galpon();
//                galpon.setId(rs.getInt("id_galpon"));
//                galpon.setNumPollo(rs.getInt("pollos"));
//                galpon.setRaza(rs.getString("raza"));
//                galpon.setCtdSuministrada(rs.getInt("cant_Suministrada"));
//                galpon.setTbalanceado(rs.getString("Tipo_Balanceado"));
//                galpon.setfDiarioAlimentacion(rs.getInt("alimentacion"));
//                galpon.setfIncio(rs.getDate("creacion"));
//                galpon.setfFin(rs.getDate("finaliza"));
//                lista.insertarNodo((T) galpon);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return (Lista<Galpon>) lista;
//    }
}
