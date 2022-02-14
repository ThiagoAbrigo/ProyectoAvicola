package modelo;

import java.io.Serializable;

/**
 *Clase encarga de dar Roles a cada Persona,Empleado
 * @author LJ
 */
public class Rol implements Serializable {

    /**
     *El rol que la persona va desempeañar, Puedes ser empleado, desarrollador, gerente, etc.
     */
    private String cargo;
    /**
     *Es true si el rol de usario es autorizado para usarlo caso contrario sera false.
     */
    private boolean autorizacion;
    /**
     *Es una breve descripcion de la activada que va a realizar
     */
    private String descripcion;

    /**
     *Contructor Rol
     * @param cargo
     * @param autorizacion
     * @param descripcion
     */
    public Rol(String cargo, boolean autorizacion, String descripcion) {
        this.cargo = cargo;
        this.autorizacion = autorizacion;
        this.descripcion = descripcion;
    }

    /**
     *Contrutor por deafault de rol
     */
    public Rol() {

    }

    /**
     *Obtener cargo designado en rol
     * @return cargo
     */

    public String getCargo() {
        return cargo;
    }

    /**
     *Designar cargo o rol
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     *Obtine la informacion de si el usuario tiene o no acceso al sistema
     * @return true si tiene acceso y false si no tiene
     */

    public boolean isAutorizacion() {
        return autorizacion;
    }

    /**
     *Desinagnar u otorgar al usuario acceso al sistema 
     * @param autorizacion
     */

    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    /**
     *Devuelve la descripcion u actividad que le fue designado al usuario
     * @return
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     *Designar actividad o descripcion de que vaa reliazr el usuario  
     * @param descripcion
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *Devuelve el cargo en String 
     * @return cargo
     */
    @Override
    public String toString() {
        return this.cargo;
    }

}
