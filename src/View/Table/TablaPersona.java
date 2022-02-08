/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Persona;

/**
 *
 * @author usuario
 */
public class TablaPersona extends AbstractTableModel {

    private Lista<Persona> lista = new Lista();

    public Lista<Persona> getLista() {
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
        return 10;
    }

    @Override
    public Object getValueAt(int i, int i1) {
    Persona dato = lista.consultarDatoPosicion(i);
    switch(i1){
        //case 0: return (dato.getId());
        case 0: return dato.getNombre();
        case 1: return dato.getApellido();
        case 2: return dato.getCedula();
        case 3: return dato.getCelular();
        case 4: return dato.getCorreo();
        case 5: return dato.getDireccion();
        case 6: return dato.getPassword();
        case 7: return dato.getRol().getCargo();
        case 8: return dato.getRol().isAutorizacion();
        case 9: return dato.getRol().getDescripcion();
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
        case 3: return "Celular";
        case 4: return "Correo";
        case 5: return "Direccion";
        case 6: return "Password";
        case 7: return "Rol";
        case 8: return "Estado";
        case 9: return "Descripcion";
        default: return null;  
        }
    }
}
