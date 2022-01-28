/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Galpon;

/**
 *
 * @author Home
 */
public class TableGalpon extends AbstractTableModel{
    private Lista<Galpon> lista = new Lista();

    public Lista<Galpon> getLista() {
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
        return 6;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Galpon dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0: return (dato.getId());
            case 1: return dato.getNumPollo();
            case 2: return dato.getRaza();
            case 3: return dato.getCtdSuministrada();
            case 4: return dato.getTbalanceado();
            case 5: return dato.getfDiarioAlimentacion();
            default: return null;             
        }            
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "ID";
            case 1:
                return "Nro POLLOS";
            case 2:
                return "RAZA";
            case 3:
                return "CANTIDAD";
            case 4:
                return "TIPO";
            case 5:
                return "ALIMENTACION";
                default:return null;

        }
    }
}
