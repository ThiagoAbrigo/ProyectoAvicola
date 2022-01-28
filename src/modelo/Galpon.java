/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Home
 */
public class Galpon implements Serializable{
    private int id;
    private String numPollo;
    private String raza;
    private String peso;
    private String muestra;
    private int ctdSuministrada;
    private String tbalanceado;
    private int fDiarioAlimentacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumPollo() {
        return numPollo;
    }

    public void setNumPollo(String numPollo) {
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
