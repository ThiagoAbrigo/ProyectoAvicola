/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Home
 */
public class ConexionBaseDatos {
    public static String base = "proyectoavicola";
    public static String user = "root";
    public static String password = "";
    public static String url = "jdbc:mysql://localhost:3306/" + base;
    public static String clase = "com.mysql.jdbc.Driver";
    public Connection conectar(){
        Connection conexion = null;
       try {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error conectar: "+e);
        }
        return conexion;
    }
}
