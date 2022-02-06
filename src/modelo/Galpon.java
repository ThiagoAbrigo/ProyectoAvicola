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

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Integer getPollosMuertos() {
        return pollosMuertos;
    }

    public void setPollosMuertos(Integer pollosMuertos) {
        this.pollosMuertos = pollosMuertos;
    }
    

    public Date getfIncio() {
        return fIncio;
    }

    public void setfIncio(Date fIncio) {
        this.fIncio = fIncio;
    }

    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }
    private Date fFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumPollo() {
        return numPollo;
    }

    public void setNumPollo(Integer numPollo) {
        this.numPollo = numPollo;
    }

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

    public int getCtdSuministrada() {
        return ctdSuministrada;
    }

    public void setCtdSuministrada(int ctdSuministrada) {
        this.ctdSuministrada = ctdSuministrada;
    }

    public String getTbalanceado() {
        return tbalanceado;
    }

    public void setTbalanceado(String tbalanceado) {
        this.tbalanceado = tbalanceado;
    }

    public int getfDiarioAlimentacion() {
        return fDiarioAlimentacion;
    }

    public void setfDiarioAlimentacion(int fDiarioAlimentacion) {
        this.fDiarioAlimentacion = fDiarioAlimentacion;
    }
    
    
}
