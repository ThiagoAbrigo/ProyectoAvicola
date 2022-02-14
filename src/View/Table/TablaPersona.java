package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Persona;

/**
 *Esta clase se encarga de carga los datos a una tabla
 * @author LJ
 */
public class TablaPersona extends AbstractTableModel {

    /**
     *Declaracion de lista para ser cargada
     */
    private Lista<Persona> lista = new Lista();

    /**
     *Obtener una lista de tipo Persona
     * @return lista de tipo Persona
     */
    public Lista<Persona> getLista() {
        return lista;
    }

    /**
     *Asignar una lista de tipo Lista
     * @param lista de tipo Lista
     */
    public void setLista(Lista lista) {
        this.lista = lista;
    }

    /**
     *Designar el tamaño de las filas con el metodo .tamanio de Lista
     * @return nro de filas de tipo int
     */
    @Override
    public int getRowCount() {
        return lista.tamanio();
    }

    /**
     *Obtener el numero de columnas
     * @return nro de columnas de la tabla
     */
    @Override
    public int getColumnCount() {
        return 10;
    }

    /**
     *Obtener los datos disponible de toda la tabla
     * @param i es la fila de tipo int
     * @param i1 es columna de tipo int
     * @return
     */
    @Override
    public Object getValueAt(int i, int i1) {
        Persona dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            case 0:
                return dato.getNombre();
            case 1:
                return dato.getApellido();
            case 2:
                return dato.getCedula();
            case 3:
                return dato.getCelular();
            case 4:
                return dato.getCorreo();
            case 5:
                return dato.getDireccion();
            case 6:
                return dato.getPassword();
            case 7:
                return dato.getRol().getCargo();
            case 8:
                return dato.getRol().isAutorizacion();
            case 9:
                return dato.getRol().getDescripcion();
            default:
                return null;

        }
    }

    /**
     *Obtener el nombre de cabezera de cada columa iniciando desde 0
     * @param i nuro de columna
     * @return Nombre de la columna 
     */
    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Cedula";
            case 3:
                return "Celular";
            case 4:
                return "Correo";
            case 5:
                return "Direccion";
            case 6:
                return "Password";
            case 7:
                return "Rol";
            case 8:
                return "Estado";
            case 9:
                return "Descripcion";
            default:
                return null;
        }
    }
}
