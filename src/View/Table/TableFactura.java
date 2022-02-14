/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Factura;

/**
 *
 * @author John
 */
public class TableFactura extends AbstractTableModel {

    /**
     * lista de tipo factura
     */
    private Lista<Factura> lista = new Lista();

    /**
     * metodo para obtener la lista de tipo factura
     *
     * @return lista tipo factura
     */
    public Lista<Factura> getLista() {
        return lista;
    }

    /**
     * metodo para ingresar una lista
     *
     * @param lista
     */
    public void setLista(Lista lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.tamanio();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Factura dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0:
                return dato.getNombreCliente();
            case 1:
                return dato.getCantidad();
            case 2:
                return dato.getDescripcionProducto();
            case 3:
                return dato.getPrecioUnitario();
            case 4:
                return dato.getTotal();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Nombre Cliente";
            case 1:
                return "Cantidad";
            case 2:
                return "Descripcion";
            case 3:
                return "Precio Unitario";
            case 4:
                return "Precio Total";
            default:
                return null;

        }
    }
}
