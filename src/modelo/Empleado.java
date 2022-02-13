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
public class Empleado extends Persona {

    private double pagoHora;
    private double seguroSocialEmpleado;
    private double seguroSocialEmpleador;
    private String hrsLaborada;
    private String fechaContratacion;
    private String fechaSalida;
    private String infoActividad;

    public Empleado(double pagoHora, double seguroSocialEmpleado, double seguroSocialEmpleador, String hrsLaborada, String fechaContratacion, String fechaSalida, String infoActividad, Rol rol, int id, String nombre, String apellido, String cedula, String celular, String correo, String direccion, String password) {
        super(rol, id, nombre, apellido, cedula, celular, correo, direccion, password);
        this.pagoHora = pagoHora;
        this.seguroSocialEmpleado = seguroSocialEmpleado;
        this.seguroSocialEmpleador = seguroSocialEmpleador;
        this.hrsLaborada = hrsLaborada;
        this.fechaContratacion = fechaContratacion;
        this.fechaSalida = fechaSalida;
        this.infoActividad = infoActividad;
    }

    public Empleado(double pagoHora, double seguroSocialEmpleado, double seguroSocialEmpleador, String hrsLaborada, String fechaContratacion, String fechaSalida, String infoActividad) {
        this.pagoHora = pagoHora;
        this.seguroSocialEmpleado = seguroSocialEmpleado;
        this.seguroSocialEmpleador = seguroSocialEmpleador;
        this.hrsLaborada = hrsLaborada;
        this.fechaContratacion = fechaContratacion;
        this.fechaSalida = fechaSalida;
        this.infoActividad = infoActividad;
    }

    public Empleado() {
    }

    public double getPagoHora() {
        return pagoHora;
    }

    public void setPagoHora(double pagoHora) {
        this.pagoHora = pagoHora;
    }

    public double getSeguroSocialEmpleado() {
        return seguroSocialEmpleado;
    }

    public void setSeguroSocialEmpleado(double seguroSocialEmpleado) {
        this.seguroSocialEmpleado = seguroSocialEmpleado;
    }

    public double getSeguroSocialEmpleador() {
        return seguroSocialEmpleador;
    }

    public void setSeguroSocialEmpleador(double seguroSocialEmpleador) {
        this.seguroSocialEmpleador = seguroSocialEmpleador;
    }

    public String getHrsLaborada() {
        return hrsLaborada;
    }

    public void setHrsLaborada(String hrsLaborada) {
        this.hrsLaborada = hrsLaborada;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getInfoActividad() {
        return infoActividad;
    }

    public void setInfoActividad(String infoActividad) {
        this.infoActividad = infoActividad;
    }
    
    
}
