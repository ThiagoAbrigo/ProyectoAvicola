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
import modelo.Persona;

/**
 *
 * @author usuario
 */
public class PersonaContoller implements CRUD {

    private final String CARPETA = "datos" + File.separatorChar + Persona.class.getSimpleName() + ".obj";
    private Lista<Persona> lisPers = new Lista();
    private Persona persona;

    public Lista<Persona> getLisPers() {
        return lisPers;
    }

    public void setLisPers(Lista<Persona> lisPers) {
        this.lisPers = lisPers;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public boolean Save() {
        Lista<Persona> aux = listar();
        try {
            persona.setId(Long.valueOf(aux.tamanio() + 1));
            aux.insertarNodo(persona);
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
        Lista<Persona> aux = listar();
        try {
            for (int i = 0; i < aux.tamanio(); i++) {
                if (aux.consultarDatoPosicion(i).getId().intValue()==persona.getId().intValue()) {
                    aux.modificarPorPos(persona, i);
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
                    oos.writeObject(aux);
                    oos.close();
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Lista<Persona> listar() {
        Lista<Persona> lista = new Lista();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Persona>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public boolean Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
