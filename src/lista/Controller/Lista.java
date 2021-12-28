/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.Controller;

import java.io.Serializable;
import lista.Model.Nodo;

/**
 *
 * @author Home
 * @param <T>
 */
public class Lista <T> implements Serializable{
    private Nodo cabecera;
    private Class clazz;

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    

    public boolean estaVacia(){
        return  this.cabecera == null;
    }
    /**
     * Permite insertar dato en la lista
     * @param dato El dato a ingresar 
     */
    private void insertar(T dato){
        Nodo nodo = new Nodo(dato, cabecera);
        cabecera = nodo;
    }
    private void insertarFinal(T dato){
        insertar(dato, tamanio());
    }
    /**
     * Insertar un dato por posicion
     * @param dato el dato a insertar
     * @param pos la posicion a insertar
     */
    public void insertar(T dato, int pos){
        if(estaVacia() || pos <= 0){
            insertar(dato);
        }else{
            Nodo iterador = cabecera;
            for (int i = 0; i < pos-1; i++) {
                if (iterador.getNodoSiguiente() == null) {
                    break;
                }
                iterador = iterador.getNodoSiguiente();
            }
            Nodo tmp = new Nodo(dato, iterador.getNodoSiguiente());
            iterador.setNodoSiguiente(tmp);
        }
    }
    /**
     * Agregar item a la lista ascendente, quiere decir que el primer elemento es la cabecera
     * @param dato el dato a agregar
     */
    public void insertarNodo(T dato){
        if (tamanio() > 0) {
            insertarFinal(dato);
        }else{
            insertar(dato);
        }
    }
    /***
     * Retorna el tamanio de la Lista
     * @return numero de elemento
     */
    
    public int tamanio(){
        int cont = 0;
        Nodo tmp = cabecera;
        while (!estaVacia() && tmp != null) {            
            cont++;
            tmp = tmp.getNodoSiguiente();
        }
        return cont;
    }
    /**
     * Permite extraer el primer dato de la lista
     * @return El dato
     */
    public T extraer(){
        T dato = null;
        if(!estaVacia()){
            dato =(T) cabecera.getDato();
            cabecera = cabecera.getNodoSiguiente();
        }
        return dato;
    }
    /**
     * Permite consultar un dato de la lista por posicion
     * @param pos posicion en la lista
     * @return dato encontrado en la posicion
     */
    public T consultarDatoPosicion(int pos){
        T dato = null;
        if(!estaVacia() && (pos >= 0 && pos <= tamanio()-1)){
            Nodo tmp = cabecera;
            for (int i = 0; i < pos; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null) break;
            }
            if (tmp != null) {
                dato = (T)tmp.getDato();
            }
        }
        return dato;
    }
    /**
     * 
     */
    public void imprimir(){
        Nodo tmp = cabecera;
        while (!estaVacia() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp = tmp.getNodoSiguiente();
        }
    }
    public boolean modificarPorPos(T dato, int pos){
        if(!estaVacia() && pos <= tamanio()-1 && pos >= 0){
            Nodo iterador = cabecera;
            for (int i = 0; i < pos; i++) {
                iterador = iterador.getNodoSiguiente();
                if (iterador == null) {
                    break;
                }
            }
            if (iterador != null) {
                iterador.setDato(dato);
                return true;
            }
        }
        return false;
    }
}
