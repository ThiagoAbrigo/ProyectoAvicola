package Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lista.Controller.Lista;
import modelo.Empleado;

/**
 *Encargado de la administracion o gestion de los datos y metodos de Empleado
 * @author LJ
 */
public class EmpleadoController<T> implements CRUD {

    /**
     *Declaracion de lista detipo Empleado
     */
    private Lista<Empleado> lisEmpleado = new Lista();
    /**
     *Declaracion de Objeto de tipo Persona
     */
    private Empleado empleado;
    /**
     *Declaracion de Lista de tipo generico
     */
    private Lista<T> lista = new Lista();
    /**
     *Declaracion a conexion a BBD
     */
    ConexionBaseDatos c = new ConexionBaseDatos();
    /**
     *Declaracion de Statement
     */
    Statement st;
    /**
     *Declaracion de ResultSet
     */
    ResultSet rs;

    /**
     *Declaracion de lista de tipo Empleado
     * @return Lista de tipo Empleado
     */

    public Lista<Empleado> getLisEmpleado() {
        if (lisEmpleado == null) {
            lisEmpleado = new Lista<>();
        }
        return lisEmpleado;
    }

    /**
     *Definir los datos de una Lista de tipo Empleado
     * @param lisEmpleado de tipo Lista Empleado
     */
    public void setLisEmpleado(Lista<Empleado> lisEmpleado) {
        this.lisEmpleado = lisEmpleado;
    }

    /**
     *Obtenr el Objeto Empleado
     * @return el objeto Empleado
     */
    public Empleado getEmpleado() {
        if (empleado == null) {
            empleado = new Empleado();
        }
        return empleado;
    }

    /**
     *Designar un valor de tipo Objeto Empleado
     * @param empleado de tipo Empleado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     *Guardar en la BDD los empleados
     * @return truen si se guardo correctamente caso contrario false
     */

    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO empleado (id_empleado, nombre, apellido, cedula, pago_hora, seguro_social_empleado, "
                + "seguro_social_empleador, hora_laborada, Fecha_contratacion, fecha_Salida, rol, descripcion) "
                + "VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getCedula());
            ps.setDouble(5, empleado.getPagoHora());
            ps.setDouble(6, empleado.getSeguroSocialEmpleado());
            ps.setDouble(7, empleado.getSeguroSocialEmpleador());
            ps.setDouble(8, empleado.getHrsLaborada());
            ps.setString(9, empleado.getFechaContratacion());
            ps.setString(10, empleado.getFechaSalida());

            ps.setString(11, empleado.getRol().getCargo());
            ps.setString(12, empleado.getRol().getDescripcion());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en guar en Base de datos " + ex);
            return false;
        }

    }

    /**
     *Modificar una BDD por la cedula
     * @return true si se modifico correctamente caso contrario false
     */
    @Override
    public boolean Update() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE empleado SET pago_hora =?, seguro_social_empleado =?, "
                + "seguro_social_empleador =?, hora_laborada =?, fecha_contratacion =?, "
                + "fecha_salida =?, rol =?, descripcion =? WHERE cedula =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setDouble(1, empleado.getPagoHora());
            pst.setDouble(2, empleado.getSeguroSocialEmpleado());
            pst.setDouble(3, empleado.getSeguroSocialEmpleador());
            pst.setDouble(4, empleado.getHrsLaborada());
            pst.setString(5, empleado.getFechaContratacion());
            pst.setString(6, empleado.getFechaSalida());
            pst.setString(7, empleado.getRol().getCargo());
            pst.setString(8, empleado.getRol().getDescripcion());
            pst.setString(9, empleado.getCedula());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     *Eliminar datos de una BDD por numero de cedula
     * @return true si se elimino correctamente caso contrario false
     */
    public boolean Delete() {
        PreparedStatement ps = null;
        Connection con = c.conectar();
        String sql = ("DELETE FROM empleado WHERE cedula = ?");
        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, empleado.getCedula());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro al eliminar " + e);
            return false;
        }
    }

    /**
     *Obtener una lista de empleado agrgada en la BDD
     * @return lista de tipo Generica
     */
    @Override
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM empleado");
            while (rs.next()) {
                Empleado user = new Empleado();
                lisEmpleado.setClazz(user.getClass());
                user.setId(rs.getInt("id_empleado"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setCedula(rs.getString("cedula"));
                user.setPagoHora(rs.getDouble("pago_hora"));
                user.setSeguroSocialEmpleado(rs.getDouble("seguro_social_empleado"));
                user.setSeguroSocialEmpleador(rs.getDouble("seguro_social_empleador"));
                user.setHrsLaborada(rs.getDouble("hora_laborada"));
                user.setFechaContratacion(rs.getString("fecha_contratacion"));
                user.setFechaSalida(rs.getString("fecha_salida"));
                user.getRol().setCargo(rs.getString("rol"));
                user.getRol().setDescripcion(rs.getString("descripcion"));
                lisEmpleado.insertarNodo(user);
                lista.insertarNodo((T) user);
            }
        } catch (Exception e) {
            System.out.println("Error en listar Usuario " + e);
        }
        setLisEmpleado(lisEmpleado);
        return lista;
    }
}
