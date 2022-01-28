/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

import Controller.ConexionBaseDatos;
import com.mysql.jdbc.PreparedStatement;
import lista.Controller.Lista;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.RegistroUsuario;

/**
 *
 * @author Home
 */
public class AdapatadorRegistroDao<T> implements Operaciones<T> {

    private Class<T> clazz;
    private Lista<T> lista = new Lista<>();
    ConexionBaseDatos c = new ConexionBaseDatos();

    public AdapatadorRegistroDao(Class<T> clazz) {
        this.clazz = clazz;
        lista.setClazz(clazz);
    }

    @Override
    public boolean guardar(T dato) {
        RegistroUsuario usuario = (RegistroUsuario)dato;
        Connection con = c.conectar();
        String sql = "INSERT INTO usuarios(id_usuarios,usuario,password) VALUE(?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, usuario.getId_Usuarios());
            ps.setString(2, usuario.getUser());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }

    @Override
    public boolean modificar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lista<T> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
