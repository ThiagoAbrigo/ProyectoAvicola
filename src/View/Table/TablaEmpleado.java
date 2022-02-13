/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Empleado;

/**
 *
 * @author usuario
 */
public class TablaEmpleado extends AbstractTableModel {

    private Lista<Empleado> lista = new Lista();

    public Lista<Empleado> getLista() {
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
        return 11;
    }

    @Override
    public Object getValueAt(int i, int i1) {
    Empleado dato = lista.consultarDatoPosicion(i);
    switch(i1){
        //case 0: return (dato.getId());
        case 0: return dato.getNombre();
        case 1: return dato.getApellido();
        case 2: return dato.getCedula();
        case 3: return dato.getPagoHora();
        case 4: return dato.getSeguroSocialEmpleado();
        case 5: return dato.getSeguroSocialEmpleador();
        case 6: return dato.getHrsLaborada();
        case 7: return dato.getFechaContratacion();
        case 8: return dato.getFechaSalida();
        case 9: return dato.getRol().getCargo();
        case 10: return dato.getRol().getDescripcion();
        default: return null; 
                
    }
    }
    
    
    
    @Override
    public String getColumnName(int i) {
        switch(i){
        //case 0: return "ID";
        case 0: return "Nombre";
        case 1: return "Apellido";
        case 2: return "Cedula";
        case 3: return "Pago Hora";
        case 4: return "Seguro Empleado";
        case 5: return "Seguro Empleador";
        case 6: return "Horas Laboradas";
        case 7: return "Fecha Contratacion";
        case 8: return "Fecha salida";
        case 9: return "Rol";
        case 10: return "Descripcion";
        default: return null;  
        }
    }
}
