package Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import lista.Controller.Lista;
import modelo.Vacuna;

/**
 *
 * @author Home
 */
public class VacunaController<T> implements CRUD{
    private Lista<Vacuna> vacunas = new Lista();
    private Vacuna vacuna;
    private Lista<T> lista = new Lista();
    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;

    public Lista<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(Lista<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public Vacuna getVacuna() {
        if (vacuna == null) {
            vacuna = new Vacuna();
        }
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }
    
    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO vacunas(id_vacuna,nombre,farmaco, justificacion, dosis, primeraVacuna, segundaVacuna) VALUE(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, vacuna.getId_vacuna());
            ps.setString(2, vacuna.getNombre());
            ps.setString(3, vacuna.getFarmaco());
            ps.setString(4, vacuna.getJustificacion());
            ps.setDouble(5, vacuna.getDosis());
            ps.setString(6, std.format(vacuna.getOnevacuna()));
            ps.setString(7, std.format(vacuna.getTwovacuna()));
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean Update() {
        PreparedStatement pst;
        Connection con = c.conectar();
        String sql = ("UPDATE vacunas SET nombre =?, farmaco =?, justificacion =?, dosis =?, primeraVacuna =?, segundaVacuna =? WHERE id_vacuna =?");
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, vacuna.getNombre());
            pst.setString(2, vacuna.getFarmaco());
            pst.setString(3, vacuna.getJustificacion());
            pst.setDouble(4, vacuna.getDosis());
            pst.setString(5, std.format(vacuna.getOnevacuna()));
            pst.setString(6, std.format(vacuna.getTwovacuna()));
            pst.setInt(7, vacuna.getId_vacuna());
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
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM vacunas");
            while (rs.next()) {
                Vacuna vacuna = new Vacuna();
                vacuna.setId_vacuna(rs.getInt("id_vacuna"));
                vacuna.setNombre(rs.getString("nombre"));
                vacuna.setFarmaco(rs.getString("farmaco"));
                vacuna.setJustificacion(rs.getString("justificacion"));
                vacuna.setDosis(rs.getDouble("dosis"));
                vacuna.setOnevacuna(rs.getDate("primeraVacuna"));
                vacuna.setTwovacuna(rs.getDate("segundaVacuna"));
                lista.insertarNodo((T) vacuna);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
