package modelo;

import java.io.Serializable;

/**
 *Clase Padre para todo los usuario
 * @author LJ
 */
public class Persona implements Serializable {

    /**
     *Rol que va a desempeñar el usuario en el sistema
     * Dato de tipo objeto Rol
     */
    private Rol rol;
    /**
     *Es el id o llave unica que cada usuario va a tener
     *Dato de tipo int
     */
    private int id;
    /**
     *Nombres de los usuarios
     *Dato de tipo String
     */
    private String nombre;
    /**
     *Apellido de los usuarios
     *Dato de tipo String
     */
    private String apellido;
    /**
     *Cedula que pertenece al usuario
     *Dato de tipo String
     */
    private String cedula;
    /**
     *Nro de celular o telefono del usuario
     *Dato de tipo String 
     */
    private String celular;
    /**
     *Correo del usuario
     *Dato de tipo String 
     */
    private String correo;
    /**
     *Direccion de domicio del usuario
     *Dato de tipo String
     */
    private String direccion;
    /**
     *Contraseña de cada usuario para poder acceder al sistema
     * Dato de tipo String
     */
    private String password;

    /**
     *Contrutor de la clase Persona 
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
    public Persona(Rol rol, int id, String nombre, String apellido, String cedula, String celular, String correo, String direccion, String password) {
        this.rol = rol;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.password = password;
    }

    /**
     *Constructor por defualt de Persona
     */
    public Persona() {

    }

    /**
     *Devuelve el rol designado para cada usuario
     * @return rol tipo rol
     */
    public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

    /**
     *Otorga rol a los usuario
     * @param rol
     */

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     *Obtiene el identificador unico de cada usuario
     * @return id tipi int
     */

    public int getId() {
        return id;
    }

    /**Designar identificador unico a cada usuario
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Obtiene el nombre de usuario
     * @return nombre tipo String
     */

    public String getNombre() {
        return nombre;
    }

    /**
     *Otorgar nobre de usuario en el sistema
     * @param nombre tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Obtiene el apellido de usuario
     * @return apellido tipo String
     */

    public String getApellido() {
        return apellido;
    }

    /**
     *Otorgar apellido de usuario en el sistema 
     * @param apellido tipo String
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     *Obtener nro de cedula de usuario, tambien es unico
     * @return cedula tipo String
     */
    public String getCedula() {
        return cedula;
    }

    /**
     *Designar nro de cedula de usuario, Tambien puede ser pasaporte
     * @param cedula tipo String
     */

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     *Obtener nro de celular del usuario
     * @return celalar tipo String
     */
    public String getCelular() {
        return celular;
    }

    /**
     *Otorgar nro de celular de usuario al sistema 
     * @param celular tipo String
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     *Obtener correo electronico de usuario
     * @return correo tipo String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *Otorgar correo electronico de usuario en el sistema
     * @param correo tipo String
     */

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *Obtener la direccion de domicilio de usuario
     * @return direccion tipo String
     */

    public String getDireccion() {
        return direccion;
    }

    /**
     *Otorgar direccion de domicilio de usuario en el sistema
     * @param direccion tipo String
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *Obtener la contraseña de usuario para iniciar sesion
     * @return password tipo String
     */

    public String getPassword() {
        return password;
    }

    /**
     *Otorgar contraseña de usuario
     * @param password tipo String
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *Rol designado de usuario
     * @return rol tipo String
     */
    @Override
    public String toString() {
        return rol.getCargo();
    }

}

