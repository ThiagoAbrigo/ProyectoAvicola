/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lista.Controller.Lista;
import modelo.Persona;

/**
 *
 * @author usuario
 */
public class PersonaContoller<T> implements CRUD {

    private Lista<Persona> lisPers = new Lista();
    private Persona persona;
    private Lista<T> lista = new Lista();

    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;

    public Lista<Persona> getLisPers() {
        if (lisPers == null) {
            lisPers = new Lista<>();
        }
        return lisPers;
    }

    public void setLisPers(Lista<Persona> lisPers) {
        this.lisPers = lisPers;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO usuario(id_usuario, nombre, apellido, cedula, celular, correo, direccion, password, cargo, autorizacion, descripcion) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, persona.getId());
            //ps.setInt(1, new Long(lisPers.tamanio()+1));
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setString(4, persona.getCedula());
            ps.setString(5, persona.getCelular());
            ps.setString(6, persona.getCorreo());
            ps.setString(7, persona.getDireccion());
            ps.setString(8, persona.getPassword());
            ps.setString(9, persona.getRol().getCargo());
            ps.setBoolean(10, persona.getRol().isAutorizacion());
            ps.setString(11, persona.getRol().getDescripcion());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en guar en Base de datos " + ex);
            return false;
        }

    }

    @Override
    public boolean Update() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE usuario SET nombre =?, apellido =?, cedula =?, celular =?, correo =?, direccion =?, password =? , cargo =?, autorizacion =?, descripcion =? WHERE id_usuario =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getCedula());
            pst.setString(4, persona.getCelular());
            pst.setString(5, persona.getCorreo());
            pst.setString(6, persona.getDireccion());
            pst.setString(7, persona.getPassword());
            pst.setString(8, persona.getRol().getCargo());
            pst.setBoolean(9, persona.getRol().isAutorizacion());
            pst.setString(10, persona.getRol().getDescripcion());
            pst.setInt(11, persona.getId());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void parametroID(int id) {
        persona.setId(id);
    }

    public boolean Delete() {

        PreparedStatement ps = null;
        Connection con = c.conectar();
        String sql = ("DELETE FROM usuario WHERE id_usuario = ?");
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, persona.getId());
            //ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro al eliminar " + e);
            return false;
        }
    }

    public boolean iniciarSesion(Persona user) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = c.conectar();

        String sql = "SELECT id_usuario, correo, password, nombre ,cargo, autorizacion  From usuario where usuario =?";
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, persona.getCorreo());
            rs = pst.executeQuery();
            if (rs.next()) {
                if (persona.getPassword().equals(rs.getString(3))) {
                    persona.setId(rs.getInt(1));
                    persona.setNombre(rs.getString(4));
                    persona.getRol().setCargo(rs.getString(5));
                    persona.getRol().setAutorizacion(rs.getBoolean(6));
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error de conttraseña " + e);
            return false;
        }

    }

    public void ordenar(String oreden, Integer tipo) {
        lisPers.imprimir();
        lisPers.seleccion_clase(oreden, tipo);
        System.out.println("nuevo orden");
        lisPers.imprimir();
    }

    @Override
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM usuario");
            while (rs.next()) {
                Persona usuario = new Persona();
                lisPers.setClazz(usuario.getClass());
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setPassword(rs.getString("password"));
                usuario.getRol().setCargo(rs.getString("cargo"));
                usuario.getRol().setAutorizacion(rs.getBoolean("autorizacion"));
                usuario.getRol().setDescripcion(rs.getString("descripcion"));
                lisPers.insertarNodo(usuario);
                lista.insertarNodo((T) usuario);

            }

        } catch (Exception e) {
            System.out.println("Error en listar Usuario" + e);
        }
        setLisPers(lisPers);
        return lista;
    }

    public void comparaDatos(String atributo) throws Exception {
        T pivote;
        for (int i = 0; i < lisPers.tamanio(); i++) {
            pivote = (T) lisPers.consultarDatoPosicion(i);
        }

        for (int i = 0; i < lisPers.tamanio(); i++) {
            lisPers.value(lisPers.consultarDatoPosicion(i), atributo);
            System.out.println("usuario ==> " + lisPers.value(lisPers.consultarDatoPosicion(i), atributo));
            System.out.println("Cargo > " + lisPers.consultarDatoPosicion(i).toString());

        }
    }

}
