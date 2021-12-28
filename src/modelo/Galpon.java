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
    private Long id;
    private int numPollo;
    private String raza;
    private Double peso;
    private Double muestra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumPollo() {
        return numPollo;
    }

    public void setNumPollo(int numPollo) {
        this.numPollo = numPollo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getMuestra() {
        return muestra;
    }

    public void setMuestra(Double muestra) {
        this.muestra = muestra;
    }
    
}
