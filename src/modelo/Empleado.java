package modelo;

import java.util.Date;

/**
 *Clase Empleado encarga del rol empleado
 * @author LJ
 */
public class Empleado extends Persona {

    /**
     *Pagor que recibe el empleado por cada hora laborada
     *Dato de tipo double 
     */
    private double pagoHora;
    /**
     *Porcenteje del seguro social que elempleado paga de su sueldo
     * Dato de tipo double
     */
    private double seguroSocialEmpleado;
    /**
     *Porcentaje del seguro socila que el empleador u empresa paga al la asegurador
     *Dato de tipo double
     */
    private double seguroSocialEmpleador;
    /**
     *Horas en total que el usuario a trabajado previo a su pago
     * Dato de tipo double
     */
    private double hrsLaborada;
    /**
     *Fecha que el usuario fue contrato
     * Dato de tipo String (aaaa-mm-dd)
     */

    private Date fechaContratacion;
    /**
     *Fecha que el usuario dejo la empresa
     * Dato de tipo String (aaaa-mm-dd)
     */
    private Date fechaSalida;

    /**
     *Contructor empleado
     * @param pagoHora
     * @param seguroSocialEmpleado
     * @param seguroSocialEmpleador
     * @param hrsLaborada
     * @param fechaContratacion
     * @param fechaSalida
     * @param rol
     * @param id
     * @param nombre
     * @param apellido
     * @param cedula
     * @param celular
     * @param correo
     * @param direccion
     * @param password
     */

    public Empleado(double pagoHora, double seguroSocialEmpleado, double seguroSocialEmpleador, double hrsLaborada, Date fechaContratacion, Date fechaSalida, Rol rol, int id, String nombre, String apellido, String cedula, String celular, String correo, String direccion, String password) {
        super(rol, id, nombre, apellido, cedula, celular, correo, direccion, password);
        this.pagoHora = pagoHora;
        this.seguroSocialEmpleado = seguroSocialEmpleado;
        this.seguroSocialEmpleador = seguroSocialEmpleador;
        this.hrsLaborada = hrsLaborada;
        this.fechaContratacion = fechaContratacion;
        this.fechaSalida = fechaSalida;
    }

    /**
     *Constructor Empleado por default
     */

    public Empleado() {
    }

    /**
     *Obtener valor pago por hora de empleado
     * @return pagoHora tipo double
     */

    public double getPagoHora() {
        return pagoHora;
    }

    /**
     *Designar le valor que se pagara por cada hora trabaja
     * @param pagoHora tipo double
     */
    public void setPagoHora(double pagoHora) {
        this.pagoHora = pagoHora;
    }

    /**
     *Obter el porcenta que el empleado pago al seguro
     * @return seguroSocialEmpleado tipo double
     */
    public double getSeguroSocialEmpleado() {
        return seguroSocialEmpleado;
    }

    /**
     *Designar el porcentaje que el empleado pago de su sueldo al seguro
     * @param seguroSocialEmpleado tipo double
     */
    public void setSeguroSocialEmpleado(double seguroSocialEmpleado) {
        this.seguroSocialEmpleado = seguroSocialEmpleado;
    }

    /**
     *Obtener porcentaje del seguro social que paga el empleado
     * @return seguroSocialEmpleador tipo double
     */
    public double getSeguroSocialEmpleador() {
        return seguroSocialEmpleador;
    }

    /**
     *Designar el valor que el empleador debe pagar a sus empleados
     * @param seguroSocialEmpleador tipo double
     */
    public void setSeguroSocialEmpleador(double seguroSocialEmpleador) {
        this.seguroSocialEmpleador = seguroSocialEmpleador;
    }

    /**
     *Obtener el numero de horas que el empleado laboro
     * @return tipo double
     */
    public double getHrsLaborada() {
        return hrsLaborada;
    }

    /**
     *Designar el nro de hora que el empleado trabajo
     * @param hrsLaborada tipo double
     */
    public void setHrsLaborada(double hrsLaborada) {
        this.hrsLaborada = hrsLaborada;
    }

    /**
     *Obtener la fecha en la que elempleado inicio a laborar
     * @return fechaContratacion tipo String
     */
    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    /**
     *Asignar una fecha en la que el empleado va a empezar a trabajar o inicio a trabajar
     * @param fechaContratacion tipo String
     */
    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    /**
     *Obtener la fecha en la que el empleado salio de labarar
     * @return fechaSalida tipo String
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     *Asigna la fecha en la que el empleado ya no lavora
     * @param fechaSalida fechaSalida tipo String
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}

