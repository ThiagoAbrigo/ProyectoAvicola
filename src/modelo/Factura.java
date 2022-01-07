/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author John
 */
public class Factura extends DetalleFactura {
    
    private String NombreCliente;
    private Long Id;
    private Date fechaEmision;
    private double Iva = 0.12;
    private double precioFinal;
    private String codigoFactura;

    public Factura(Long Id, String NombreCliente, Date fechaEmision, double precioFinal, String codigoFactura, int cantidad, String descripcion, double peso) {
        this.Id = Id;
        this.NombreCliente = NombreCliente;
        this.fechaEmision = fechaEmision;
        this.precioFinal = precioFinal;
        this.codigoFactura = codigoFactura;
        super.setCantidad(cantidad);
        super.setDescripcionProducto(descripcion);
        super.setId(Id);
        super.setPeso(peso);
    }

    public Factura() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getIva() {
        return Iva;
    }

    public void setIva(double Iva) {
        this.Iva = Iva;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }
}
