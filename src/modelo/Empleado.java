/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Empleado extends Persona{
    private Persona persona;
    private int hrsLaborada;
    private int seguroSocial;
    private Date fechaContratacion;
    private Date fechaSalida;
    private String infoActividad;

    public Empleado(Persona persona, int hrsLaborada, int seguroSocial, Date fechaContratacion, Date fechaSalida, String infoActividad, Rol rol, int id, String nombre, String apellido, String cedula, String celular, String correo, String direccion, String password) {
        super(rol, id, nombre, apellido, cedula, celular, correo, direccion, password);
        this.persona = persona;
        this.hrsLaborada = hrsLaborada;
        this.seguroSocial = seguroSocial;
        this.fechaContratacion = fechaContratacion;
        this.fechaSalida = fechaSalida;
        this.infoActividad = infoActividad;
    }

    public Empleado(Persona persona, int hrsLaborada, int seguroSocial, Date fechaContratacion, Date fechaSalida, String infoActividad) {
        this.persona = persona;
        this.hrsLaborada = hrsLaborada;
        this.seguroSocial = seguroSocial;
        this.fechaContratacion = fechaContratacion;
        this.fechaSalida = fechaSalida;
        this.infoActividad = infoActividad;
    }

    

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getHrsLaborada() {
        return hrsLaborada;
    }

    public void setHrsLaborada(int hrsLaborada) {
        this.hrsLaborada = hrsLaborada;
    }

    public int getSeguroSocial() {
        return seguroSocial;
    }

    public void setSeguroSocial(int seguroSocial) {
        this.seguroSocial = seguroSocial;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getInfoActividad() {
        return infoActividad;
    }

    public void setInfoActividad(String infoActividad) {
        this.infoActividad = infoActividad;
    }
    
    
}
