/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Home
 */
public class Galpon implements Serializable{
    private int id;
    private Integer numPollo;
    private Integer pollosMuertos;
    private Integer existencias;
    private String raza;
    private String peso;
    private String muestra;
    private int ctdSuministrada;
    private String tbalanceado;
    private int fDiarioAlimentacion;
    private Date fIncio;
    private Date fFin;
    /**
     * Numero de pollos existentes
     * @return 
     */
    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }
    /**
     * Numero de pollos muertos
     * @return Integer
     */
    public Integer getPollosMuertos() {
        return pollosMuertos;
    }

    public void setPollosMuertos(Integer pollosMuertos) {
        this.pollosMuertos = pollosMuertos;
    }
    /**
     * Fecha en que fue ingresado el galpon
     * @return Date
     */
    public Date getfIncio() {
        return fIncio;
    }

    public void setfIncio(Date fIncio) {
        this.fIncio = fIncio;
    }
    /**
     * Fecha en que se finalice el galpon
     * @return Date
     */
    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }
    /**
     * id de los galpones
     * @return int
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * cantidad de pollos ingresados
     * @return Integer
     */
    public Integer getNumPollo() {
        return numPollo;
    }

    public void setNumPollo(Integer numPollo) {
        this.numPollo = numPollo;
    }
    /**
     * Raza del pollo
     * @return String
     */
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String muestra) {
        this.muestra = muestra;
    }
    /**
     * Cantidad alimeto
     * @return int
     */
    public int getCtdSuministrada() {
        return ctdSuministrada;
    }

    public void setCtdSuministrada(int ctdSuministrada) {
        this.ctdSuministrada = ctdSuministrada;
    }
    /**
     * tipo de balanceado
     * @return String
     */
    public String getTbalanceado() {
        return tbalanceado;
    }

    public void setTbalanceado(String tbalanceado) {
        this.tbalanceado = tbalanceado;
    }
    /**
     * indica cuantas veces se lo alimenta al galpon
     * @return 
     */
    public int getfDiarioAlimentacion() {
        return fDiarioAlimentacion;
    }

    public void setfDiarioAlimentacion(int fDiarioAlimentacion) {
        this.fDiarioAlimentacion = fDiarioAlimentacion;
    }
    
    
}
