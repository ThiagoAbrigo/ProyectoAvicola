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
public class Caja implements Serializable{
    private int id_caja;
    private Double presupuesto_Actual;
    private Double pago_sueldo;
    private Double alimento;
    private Double ganancia;
    private Double ingresos;
    private Double egresos;

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }

    public Double getEgresos() {
        return egresos;
    }

    public void setEgresos(Double egresos) {
        this.egresos = egresos;
    }
    

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public Double getPresupuesto_Actual() {
        return presupuesto_Actual;
    }

    public void setPresupuesto_Actual(Double presupuesto_Actual) {
        this.presupuesto_Actual = presupuesto_Actual;
    }

    public Double getPago_sueldo() {
        return pago_sueldo;
    }

    public void setPago_sueldo(Double pago_sueldo) {
        this.pago_sueldo = pago_sueldo;
    }

    public Double getAlimento() {
        return alimento;
    }

    public void setAlimento(Double alimento) {
        this.alimento = alimento;
    }
    
}
