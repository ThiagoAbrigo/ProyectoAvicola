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
 * Metodos implementado del Crud y busqueda por fecha
 * @author Santiago Abrigo y Javier Sarango
 */
public class GalponController<T> implements CRUD {

    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
    private Date fechamod = new Date();
    private Lista<Galpon> galpones = new Lista();
    private Galpon galpon;
    private Lista<T> lista = new Lista();
    private Class<T> clazz;
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;
    public GalponController(){
        
    }
    public GalponController(Class<T> clazz){
        this.clazz = clazz;
        lista.setClazz(clazz);
    }
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

    /**
     * Guarda galpones con BDD
     *
     * @return Boolean
     */
    @Override
    public boolean Save() {
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

    /**
     * Actualiza galpon BDD
     *
     * @return Boolean
     */
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

    /**
     * Elimina galpon BDD
     *
     * @return Boolean
     */
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

    /**
     * Lista galpones en la tabla
     *
     * @return Lista
     */
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
                galpones.insertarNodo(galpon);
                lista.insertarNodo((T) galpon);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    /**
     * Guarda las muertes de la cantidad de pollos que hay en el galpon
     *
     * @return Boolean
     */
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
    /**
     * Actualiza la cantidad de pollos
     * @return 
     */
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

    /**
     * Lista los pollos muertos con su respectivo galpon
     *
     * @return Lista
     */
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
                galpones.setClazz(galpon.getClass());
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
    /**
     * Busca los galpones por fecha del dia que son ingresados
     * @param date
     * @return lista de galpones
     */
    public Lista<Galpon> buscarPorFechas(String date) {
        Lista<Galpon> lista = new Lista();
        Lista<Galpon> aux = (Lista<Galpon>) listar();
        for (int i = 0; i < listar().tamanio(); i++) {
            Galpon p = aux.consultarDatoPosicion(i);
            if (p.getfIncio().toString().toLowerCase().contains(date.toLowerCase())){
                lista.insertarNodo(p);
            }
        }
        return lista;
    }

}
