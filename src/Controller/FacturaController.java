/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lista.Controller.Lista;
import modelo.Factura;

/**
 *
 * @author John
 */
public class FacturaController implements CRUD {

    private final String CARPETA = "Venta" + File.separatorChar + Factura.class.getSimpleName() + ".obj";
    private Lista<Factura> facturas = new Lista();
    private Factura factura;

    public Lista<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Lista<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFactura() {
        if (factura == null) {
            factura = new Factura();
        }
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public boolean Save() {
        Lista<Factura> aux = listar();
        try {
            factura.setId(Long.valueOf(aux.tamanio() + 1));
            aux.insertarNodo(factura);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
            oos.writeObject(aux);
            oos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Lista<Factura> listar() {
        Lista<Factura> lista = new Lista();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Factura>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public boolean Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
