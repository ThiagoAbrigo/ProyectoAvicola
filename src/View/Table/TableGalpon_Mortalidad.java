/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import View.Frm_PrincipalDemo;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Galpon;

/**
 *
 * @author Home
 */
public class TableGalpon_Mortalidad extends AbstractTableModel{
    private Lista<Galpon> lista = new Lista();
    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
   private Date fechamod = new Date();
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
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        
        Galpon dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0: return dato.getId();
            case 1: return dato.getNumPollo();
            case 2: return dato.getPollosMuertos();
            case 3: return dato.getExistencias();
            default: return null;             
        }            
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "ID";
            case 1:
                return "Nro. Pollos actual";
            case 2:
                return "Nro. Pollos Fallecidos";
            case 3:
                 return "Total";
               
                default:return null;

        }
    }
}
