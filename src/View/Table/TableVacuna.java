/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Vacuna;

/**
 *
 * @author Home
 */
public class TableVacuna extends AbstractTableModel{
    private Lista<Vacuna> lista = new Lista();

    public Lista<Vacuna> getLista() {
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
        return 7;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Vacuna dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0: return (dato.getId_vacuna());
            case 1: return dato.getNombre();
            case 2: return dato.getFarmaco();
            case 3: return dato.getJustificacion();
            case 4: return dato.getDosis();
            case 5: return dato.getOnevacuna();
            case 6: return dato.getTwovacuna();
            default: return null;             
        }            
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "ID";
            case 1:
                return "NOMBRE";
            case 2:
                return "FARMACO";
            case 3:
                return "JUSTIFICACION";
            case 4:
                return "DOSIS";
            case 5:
                return "1 VACUNA";
            case 6:
                return "2 VACUNA";
                default:return null;

        }
    }
}
