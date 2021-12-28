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
import modelo.Vacuna;

/**
 *
 * @author Home
 */
public class VacunaController implements CRUD{
    private final String CARPETA = "HistorialVacunas" + File.separatorChar + Vacuna.class.getSimpleName() + ".obj";
    private Lista<Vacuna> vacunas = new Lista();
    private Vacuna vacuna;

    public Lista<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(Lista<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public Vacuna getVacuna() {
        if (vacuna == null) {
            vacuna = new Vacuna();
        }
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }
    
    @Override
    public boolean Save() {
        Lista<Vacuna> aux = listar();
        try {
            vacuna.setId(Long.valueOf(aux.tamanio() + 1));
            aux.insertarNodo(vacuna);
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
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Lista<Vacuna> listar() {
        Lista<Vacuna> lista = new Lista();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Vacuna>) ois.readObject();
            ois.close();
        } catch (Exception e) {
        }
        return lista;
    }
}
