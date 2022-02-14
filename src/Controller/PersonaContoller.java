package Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lista.Controller.Lista;
import modelo.Persona;

/**
 *Metodo encargo de controlar las acciones de Persona
 * @author LJ
 */
public class PersonaContoller<T> implements CRUD {

    /**
     *Declaracion de lista de tipo Persona
     */
    private Lista<Persona> lisPers = new Lista();
    /**
     *Declaracion de Lista de tipo Persona
     */
    private Persona persona;
    /**
     *Declaracion de listata tipo Lista
     */
    private Lista<T> lista = new Lista();
    /**
     *Declaracion de Lista de Usuario Logiados de la clase Persona
     */
    Lista<Persona> usarioLogin = new Lista<>();
    /**
     *Declaracion de usuario este es el correo
     */
    private String usario = "";
    /**
     *Declaracion de Contraseña
     */

    private String pass = "";
    /**
     *Declaracion de permitira Ingreso de tipo boolean
     */
    private boolean permitirIngreso = false;
    /**
     *Declaracion de conecion a la BBD
     */

    ConexionBaseDatos c = new ConexionBaseDatos();
    /**
     *Declaracion de Statement
     */
    Statement st;
    /**
     *Declaracion de ResulSet
     */
    ResultSet rs;

    /**
     *Obtener datos de una lista de tipo Persona
     * @return lisPers de tipo Lista Persona
     */
    public Lista<Persona> getLisPers() {
        if (lisPers == null) {
            lisPers = new Lista<>();
        }
        return lisPers;
    }

    /**
     *Designar valores a la lista Persona
     * @param lisPers de tipo Persona
     */
    public void setLisPers(Lista<Persona> lisPers) {
        this.lisPers = lisPers;
    }

    /**
     *Obtenre el Objeto Persona
     * @return
     */
    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    /**
     *Asignar un Objeto de tipo Persona
     * @param persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     *Metodo para guardar datos en una BDD
     * @return true si se gurardo correctamente caso contrario false
     */
    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO usuario(id_usuario, nombre, apellido, cedula, celular, correo, direccion, password, cargo, autorizacion, descripcion) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, persona.getId());
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

    /**
     *Metodo para modificar los datos en una BDD
     * @return true si se modifico correctamente caso contrario false
     */
    @Override
    public boolean Update() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE usuario SET nombre =?, apellido =?, celular =?, correo =?, direccion =?, password =? , cargo =?, autorizacion =?, descripcion =? WHERE cedula =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            
            pst.setString(3, persona.getCelular());
            pst.setString(4, persona.getCorreo());
            pst.setString(5, persona.getDireccion());
            pst.setString(6, persona.getPassword());
            pst.setString(7, persona.getRol().getCargo());
            pst.setBoolean(8, persona.getRol().isAutorizacion());
            pst.setString(9, persona.getRol().getDescripcion());
            pst.setString(10, persona.getCedula());
            //pst.setInt(11, persona.getId());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     *Obtener el parametro ID
     * @param id identificador de tipo Persona
     */
    public void parametroID(int id) {
        persona.setId(id);
    }

    /**
     *Borra dato de una BDD segun el numero de Cedula
     * @return true si el dato fue eliminado correctamente caso contrario false
     */
    public boolean Delete() {

        PreparedStatement ps = null;
        Connection con = c.conectar();
        String sql = ("DELETE FROM usuario WHERE cedula = ?");
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, persona.getCedula());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro al eliminar " + e);
            return false;
        }
    }
    /**
     *Obtener los datos de una BDD y asignarla en una lista generica
     * @return una lista de tipo generica 
     */
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

    /**
     *Medodo para obtener el usuario des el Login
     * @param user de tipo String
     */
    public void user(String user) {
        this.usario = user;
    }

    /**
     *Obtener la contraseña ingresada en el Login
     * @param password de tipo String
     */
    public void Password(String password) {
        this.pass = password;
    }

    /**
     *Medoto para comprobar el ingreso Exitoso al sistema
     * @return truen si es ingreso exitoso caso contrario false
     */
    public boolean ingresoPermitidao() {
        boolean aux = false;
        aux = permitirIngreso;
        return aux;
    }

    /**
     *Lista persona para obner una lista de las persona que ingresa al sistema
     * @return Lista de personas ingresadas
     */
    public Lista<Persona> registroInicioSecion() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM registroseccion");
            while (rs.next()) {
                Persona usuario = new Persona();
                usarioLogin.setClazz(usuario.getClass());
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.getRol().setCargo(rs.getString("rol"));
                usarioLogin.insertarNodo(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error en listar Usuario" + e);
        }
        return usarioLogin;
    }

    /**
     *Medodo para comparar si los datos son iguales a los ingresados en el Login si se da el caso retorna la lsita de Ingreso
     * @return @throws Exception
     */
    public Lista<Persona> comparaDatos() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for (int i = 0; i < listar().tamanio(); i++) {
            if ((lisPers.value(lisPers.consultarDatoPosicion(i), "correo").equals(this.usario)
                    && (lisPers.value(lisPers.consultarDatoPosicion(i), "password").equals(this.pass)))) {
                Persona usuario = new Persona();
                lisPers.setClazz(usuario.getClass());
                usuario.setNombre((String) lisPers.value(lisPers.consultarDatoPosicion(i), "nombre"));
                usuario.setApellido((String) lisPers.value(lisPers.consultarDatoPosicion(i), "apellido"));
                usuario.setCorreo((String) lisPers.value(lisPers.consultarDatoPosicion(i), "correo"));
                usuario.setPassword((String) lisPers.value(lisPers.consultarDatoPosicion(i), "password"));
                usuario.getRol().setCargo(lisPers.consultarDatoPosicion(i).toString());

                usarioLogin.insertarNodo(usuario);
                permitirIngreso = true;
                Connection con = c.conectar();
                String sql = "INSERT INTO registroseccion(nombre, apellido, correo, rol, fecha) VALUE(?,?,?,?,?)";
                try {
                    PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                    ps.setString(1, usuario.getNombre());
                    ps.setString(2, usuario.getApellido());
                    ps.setString(3, usuario.getCorreo());
                    ps.setString(4, usuario.getRol().getCargo());
                    ps.setString(5, dtf.format(LocalDateTime.now()));
                    ps.executeUpdate();
                    ps.close();

                } catch (SQLException ex) {
                    System.out.println("Error en guar en Base de datos " + ex);
                }
            } else {

            }
        }
        return usarioLogin;
    }

}
