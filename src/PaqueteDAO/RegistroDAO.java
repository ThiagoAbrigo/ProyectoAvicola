/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

import modelo.RegistroUsuario;

/**
 *
 * @author Home
 */
public class RegistroDAO extends AdapatadorRegistroDao<RegistroUsuario>{
    private RegistroUsuario registro;

    public RegistroDAO() {
        super(RegistroUsuario.class);
    }
    

    public RegistroUsuario getRegistro() {
        if (registro == null) {
            registro = new RegistroUsuario();
        }
        return registro;
    }

    public void setRegistro(RegistroUsuario registro) {
        this.registro = registro;
    }
    
    public boolean guardar(){
        return guardar(registro);
    }
}
