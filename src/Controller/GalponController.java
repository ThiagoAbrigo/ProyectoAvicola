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
        String sql = "INSERT INTO galpones(id_galpon,pollos,raza, cant_Suministrada, Tipo_Balanceado, alimentacion) VALUE(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, galpon.getId());
            ps.setString(2, galpon.getNumPollo());
            ps.setString(3, galpon.getRaza());
            ps.setInt(4, galpon.getCtdSuministrada());
            ps.setString(5, galpon.getTbalanceado());
            ps.setInt(6, galpon.getfDiarioAlimentacion());
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
        String sql = ("UPDATE galpones SET pollos =?, raza =?, cant_Suministrada =?, Tipo_Balanceado =?, alimentacion =? WHERE id_galpon =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, galpon.getNumPollo());
            pst.setString(2, galpon.getRaza());
            pst.setInt(3, galpon.getCtdSuministrada());
            pst.setString(4, galpon.getTbalanceado());
            pst.setInt(5, galpon.getfDiarioAlimentacion());
            pst.setInt(6, galpon.getId());
            pst.executeUpdate();
            pst.close();
            return true;
//            if (filas < 0) {
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
//            if(filas>0){
//                con.close();
//                return true;
//            }else{
//                con.close();
//                return false;
//            }
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
            rs = st.executeQuery("SELECT * FROM galpones");
            while (rs.next()) {
                Galpon galpon = new Galpon();
                galpon.setId(rs.getInt("id_galpon"));
                galpon.setNumPollo(rs.getString("pollos"));
                galpon.setRaza(rs.getString("raza"));
                galpon.setCtdSuministrada(rs.getInt("cant_Suministrada"));
                galpon.setTbalanceado(rs.getString("Tipo_Balanceado"));
                galpon.setfDiarioAlimentacion(rs.getInt("alimentacion"));
                lista.insertarNodo((T) galpon);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    public Lista<Galpon> buscargalpon(String dato){
        Lista<Galpon> lista = new Lista();
//        //String[] titulos = {"ID","Cedula","Nombre","Apellidos","FechaNacimiento","Telefono","Genero","Email"};
//        String registros[] = new String[200];
//        String sql;
//        sql = "SELECT * FROM galpones WHERE pollos LIKE '%" + dato + "%'";
//        //modelo = new DefaultTableModel(null, titulos);
//        conexion = ConexionBaseDatos.conectar();
//        try {
//            st = (Statement)conexion.createStatement();
//            rs = st.executeQuery(sql);
//            while(rs.next()){
//                registros[0] = rs.getString("id_galpon");
//                registros[1] = rs.getString("pollos");
//                registros[2] = rs.getString("raza");
//                lista.insertarNodo((Galpon) (T) registros);
//                
//                //modelo.addRow(registros);
//            }
//            //JtablePacientes.setModel(modelo);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al buscar los datos: " + e.getMessage());
//        }
        Lista<Galpon> aux = (Lista<Galpon>) listar();
        for (int i = 0; i < listar().tamanio(); i++) {
            Galpon g = aux.consultarDatoPosicion(i);
            if (g.getNumPollo().contains(dato)) {
                lista.insertarNodo(g);
                System.out.println(lista);
            }
        }
        return lista;
    }
}
