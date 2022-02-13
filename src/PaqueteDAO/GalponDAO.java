/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

import lista.Controller.Lista;
import modelo.Galpon;

/**
 *
 * @author Home
 */
public class GalponDAO extends AdaptadorGalponDao<Galpon>{
    private Galpon galpones;

//    public GalponDAO() {
//        super(Galpon.class);
//    }
    

    public Galpon getGalpones() {
        if (galpones == null) {
            galpones = new Galpon();
        }
        return galpones;
    }

    public void setGalpones(Galpon galpones) {
        this.galpones = galpones;
    }
    public boolean guardar(){
        return guardar(galpones);
    }
    public boolean update(){
        return modificar(galpones);
    }
    public Lista<Galpon> buscarPorFechas(String dato) {
  
        Lista<Galpon> lista = new Lista();
        Lista<Galpon> aux = listar();
        for (int i = 0; i < aux.tamanio(); i++) {
            Galpon p = aux.consultarDatoPosicion(i);
            if (p.getRaza().toLowerCase().contains(dato.toLowerCase())){
                lista.insertarNodo(p);
            }
        }
        return lista;
    }
}
