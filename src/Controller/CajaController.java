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
import modelo.Caja;

/**
 * Metodos implementados de CRUD 
 * @author Santiago Abrigo
 */
public class CajaController<T> implements CRUD{
    private Caja caja;
    Lista<Caja> cajas = new Lista<>();
    private Lista<T> lista = new Lista();
    ConexionBaseDatos c = new ConexionBaseDatos();
    Statement st;
    ResultSet rs;
    
    public Caja getCaja() {
        if (caja == null) {
            caja = new Caja();
        }
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Lista<Caja> getCajas() {
        return cajas;
    }

    public void setCajas(Lista<Caja> cajas) {
        this.cajas = cajas;
    }
    /**
     * Guarda el registro de cajas
     * @return boolean
     */
    @Override
    public boolean Save() {
        Connection con = c.conectar();
        String sql = "INSERT INTO caja(id_caja,ingresos,egresos, Ganancia) VALUE(?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, caja.getId_caja());
            ps.setDouble(2, caja.getIngresos());
            ps.setDouble(3, caja.getEgresos());
            ps.setDouble(4, caja.getGanancia());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean Update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Lista los ingresos, egresos y ganancia de ventas
     * @return lista de la caja
     */
    @Override
    public Lista<T> listar() {
        st = null;
        rs = null;
        lista = new Lista();
        try {
            Connection con = c.conectar();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM caja");
            while (rs.next()) {
                Caja caja = new Caja();
                //cajas.setClazz(caja.getClass());
                caja.setId_caja(rs.getInt("id_caja"));
                caja.setIngresos(rs.getDouble("ingresos"));
                caja.setEgresos(rs.getDouble("egresos"));
                caja.setGanancia(rs.getDouble("ganancia"));
                //cajas.insertarNodo(caja);
                lista.insertarNodo((T)caja);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    /**
     * busca las ganancias por busqueda binaria
     * @param dato es el valor que recibe de la vista que se busca
     * @param caja le envia la lista
     * @return Lista de la caja
     */
    public Lista<Caja> buscarPorGanacias(Double dato, Lista caja) {
        Lista<Caja> a = new Lista();
        int central, izquierda, derecha;
        izquierda = 0;
        derecha = caja.tamanio() - 1;
        while (izquierda <= derecha) {
            central = (izquierda + derecha) / 2;
            Caja valorcentral = (Caja) caja.consultarDatoPosicion(central);
            if (valorcentral.getGanancia() == dato.doubleValue()) {
                a.insertarNodo(valorcentral);
                return a;
            }
            if (valorcentral.getGanancia().compareTo(dato) > 0) {
                derecha = central - 1;
            } else {
                izquierda = central + 1;
            }

        }
        return a;
    }
}
