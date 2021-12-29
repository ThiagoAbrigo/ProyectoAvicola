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
public class TableFactura extends AbstractTableModel{
    private Lista<Factura> lista = new Lista();

    public Lista<Factura> getLista() {
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
        return 1;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Factura dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0: return dato.getPeso();
            default: return null;             
        }            
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Peso";
                default:return null;

        }
    }
}