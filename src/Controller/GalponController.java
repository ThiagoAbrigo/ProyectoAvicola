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
import modelo.Galpon;

/**
 *
 * @author Home
 */
public class GalponController implements CRUD {

    private final String CARPETA = "datos" + File.separatorChar + Galpon.class.getSimpleName() + ".obj";
    private Lista<Galpon> galpones = new Lista();
    private Galpon galpon;

    public Lista<Galpon> getGalpones() {
        return galpones;
    }

    public void setGalpones(Lista<Galpon> galpones) {
        this.galpones = galpones;
    }

    public Galpon getGalpon() {
        if (galpon == null) {
            galpon = new Galpon();
        }
        return galpon;
    }

    public void setGalpon(Galpon galpon) {
        this.galpon = galpon;
    }

    @Override
    public boolean Save() {
        Lista<Galpon> aux = listar();
        try {
            galpon.setId(Long.valueOf(aux.tamanio() + 1));
            aux.insertarNodo(galpon);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
            oos.writeObject(aux);
            oos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean Update() {
        Lista<Galpon> aux = listar();
        try {
            for (int i = 0; i < aux.tamanio(); i++) {
                if (aux.consultarDatoPosicion(i).getId().intValue() == galpon.getId().intValue()) {
                    aux.modificarPorPos(galpon, i);
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
                    oos.writeObject(aux);
                    oos.close();
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
    }

    @Override
    public void Delete() {
        Lista<Galpon> aux = listar();
        try {
            for (int i = 0; i < aux.tamanio(); i++) {
                if (aux.consultarDatoPosicion(i).getId().intValue() == galpon.getId().intValue()) {
                    aux.eliminar(galpon, i);
                    
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
                    oos.writeObject(aux);
                    oos.close();
                    break;
                }
            }
            
        } catch (Exception e) {
            
            //e.printStackTrace();
        }    }

    public Lista<Galpon> listar() {
        Lista<Galpon> lista = new Lista();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Galpon>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
        return lista;
    }
}
