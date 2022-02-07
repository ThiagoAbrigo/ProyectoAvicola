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
        return 11;
    }

    @Override
    public Object getValueAt(int i, int i1) {
    Persona dato = lista.consultarDatoPosicion(i);
    switch(i1){
        case 0: return (dato.getId());
        case 1: return dato.getNombre();
        case 2: return dato.getApellido();
        case 3: return dato.getCedula();
        case 4: return dato.getCelular();
        case 5: return dato.getCorreo();
        case 6: return dato.getDireccion();
        case 7: return dato.getPassword();
        case 8: return dato.getRol().getCargo();
        case 9: return dato.getRol().isAutorizacion();
        case 10: return dato.getRol().getDescripcion();
        default: return null; 
                
    }
    }
    @Override
    public String getColumnName(int i) {
        switch(i){
        case 0: return "ID";
        case 1: return "Nombre";
        case 2: return "Apellido";
        case 3: return "Cedula";
        case 4: return "Celular";
        case 5: return "Correo";
        case 6: return "Direccion";
        case 7: return "Password";
        case 8: return "Rol";
        case 9: return "Estado";
        case 10: return "Descripcion";
        default: return null;  
        }
    }
}
