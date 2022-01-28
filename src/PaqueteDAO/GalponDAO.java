/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

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
    
    
}
