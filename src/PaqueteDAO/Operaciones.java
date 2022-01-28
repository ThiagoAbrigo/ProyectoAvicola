/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDAO;

import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public interface Operaciones<T> {
    public boolean guardar(T dato);
    public boolean modificar(Object object);
    public Lista<T> listar();
}
