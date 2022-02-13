/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Caja;
import modelo.DetalleFactura;

/**
 *
 * @author Home
 */
public class TableCaja extends AbstractTableModel{
    private Lista<Caja> lista = new Lista();

    public Lista<Caja> getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.tamanio();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Caja dato = lista.consultarDatoPosicion(i);
        //DetalleFactura d = li.consultarDatoPosicion(i);
        switch (i1) {
            case 0: return (dato.getId_caja());
            case 1: return dato.getIngresos();
            case 2: return dato.getEgresos();
            case 3: return dato.getGanancia();
            default: return null;             
        }            
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "ID";
            case 1:
                return "INGRESOS";
            case 2:
                return "EGRESOS";
            case 3:
                return "GANANCIA";
                default:return null;

        }
    }
}
