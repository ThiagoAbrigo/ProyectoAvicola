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
    public boolean Save();
    public boolean Update();
    public boolean Delete();
    public Lista<T> listar();
}
