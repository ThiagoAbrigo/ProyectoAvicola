/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * Atributos de la clase caja 
 * @author Santiago Abrigo
 */
public class Caja implements Serializable{
    private int id_caja;
    private Double ganancia;
    private Double ingresos;
    private Double egresos;
    /**
     * dato de las ventas totales
     * @return double
     */
    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }
    /**
     * El numero de egresos 
     * @return Double
     */
    public Double getEgresos() {
        return egresos;
    }

    public void setEgresos(Double egresos) {
        this.egresos = egresos;
    }
    /**
     * El numero total de ganancias
     * @return 
     */
    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }
    /**
     * id de la tabla caja
     * @return 
     */
    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }
}
