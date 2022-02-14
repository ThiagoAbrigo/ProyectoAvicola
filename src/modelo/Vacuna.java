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
public class Vacuna implements Serializable{
    private int id_vacuna;
    private String nombre;
    private String farmaco;
    private String justificacion;
    private Double dosis;
    private Date onevacuna;
    private Date twovacuna;

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public Date getOnevacuna() {
        return onevacuna;
    }

    public void setOnevacuna(Date onevacuna) {
        this.onevacuna = onevacuna;
    }

    public Date getTwovacuna() {
        return twovacuna;
    }

    public void setTwovacuna(Date twovacuna) {
        this.twovacuna = twovacuna;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }
    
    
}
