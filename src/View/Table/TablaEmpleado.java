package View.Table;

import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;
import modelo.Empleado;

/**
 *Esta clase se encarga de carga los datos a una tabla
 * @author LJ
 */
public class TablaEmpleado extends AbstractTableModel {

    /**
     * Declaracion de lista para ser cargada
     */
    private Lista<Empleado> lista = new Lista();

    /**
     * Obtener una lista de tipo Empleado
     * @return  lista tipo Empleado
     */
    public Lista<Empleado> getLista() {
        return lista;
    }

    /**
     *Asignar o escribir una lista
     * @param lista tipi Lista
     */
    public void setLista(Lista lista) {
        this.lista = lista;
    }

    /**
     *Designar el tamaño de las filas con el metodo .tamanio de Lista
     * @return Int del tamaño de la lista
     */
    @Override
    public int getRowCount() {
        return lista.tamanio();
    }

    /**
     *Designar el tamaño de las columnas
     * @return tamaño de columnas de tipo Int
     */
    @Override
    public int getColumnCount() {
        return 11;
    }

    /**
     *Asignar los datos de la tabla en cada fila i y columna il 
     * @param i de tipo int
     * @param i1 de tipo int
     * @return datos de la tabla
     */
    @Override
    public Object getValueAt(int i, int i1) {
        Empleado dato = lista.consultarDatoPosicion(i);
        switch (i1) {
            //case 0: return (dato.getId());
            case 0:
                return dato.getNombre();
            case 1:
                return dato.getApellido();
            case 2:
                return dato.getCedula();
            case 3:
                return dato.getPagoHora();
            case 4:
                return dato.getSeguroSocialEmpleado();
            case 5:
                return dato.getSeguroSocialEmpleador();
            case 6:
                return dato.getHrsLaborada();
            case 7:
                return dato.getFechaContratacion();
            case 8:
                return dato.getFechaSalida();
            case 9:
                return dato.getRol().getCargo();
            case 10:
                return dato.getRol().getDescripcion();
            default:
                return null;

        }
    }

    /**
     *Asignar el nombre de la cabezara de cada columna 
     * @param i nro de columna iniciando desde 0
     * @return Nombre de cada Columna asignado
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
                return "Pago Hora";
            case 4:
                return "Seguro Empleado";
            case 5:
                return "Seguro Empleador";
            case 6:
                return "Horas Laboradas";
            case 7:
                return "Fecha Contratacion";
            case 8:
                return "Fecha salida";
            case 9:
                return "Rol";
            case 10:
                return "Descripcion";
            default:
                return null;
        }
    }
}
