/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public interface CRUD<T>{
    /**
     * Guardar BDD
     * @return boolean
     */
    public boolean Save();
    /**
     * Actualiza BDD
     * @return boolean
     */
    public boolean Update();
    /**
     * 
     * @return 
     */
    public boolean Delete();
    /**
     * Lista de las tablas
     * @return Lista
     */
    public Lista<T> listar();
}
