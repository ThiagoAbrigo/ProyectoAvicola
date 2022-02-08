/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.Controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import lista.Model.Nodo;

/**
 *
 * @author Home
 * @param <T>
 */
public class Lista <T> implements Serializable{
    private Nodo cabecera;
    private Class clazz;
    public static final Integer ASCENDENTE = 1;
    public static final Integer DESCENDENTE = 2;

    public Nodo getCabecera() {
        return cabecera;
    }

    public void setCabecera(Nodo cabecera) {
        this.cabecera = cabecera;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public boolean estaVacia() {
        return this.cabecera == null;
    }

    /**
     * Permite insertar dato en la lista
     *
     * @param dato El dato a ingresar
     */
    private void insertar(T dato) {
        Nodo nodo = new Nodo(dato, cabecera);
        cabecera = nodo;
    }

    private void insertarFinal(T dato) {
        insertar(dato, tamanio());
    }

    /**
     * Insertar un dato por posicion
     *
     * @param dato el dato a insertar
     * @param pos la posicion a insertar
     */
    public void insertar(T dato, int pos) {
        if (estaVacia() || pos <= 0) {
            insertar(dato);
        } else {
            Nodo iterador = cabecera;
            for (int i = 0; i < pos - 1; i++) {
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
     * Agregar item a la lista ascendente, quiere decir que el primer elemento
     * es la cabecera
     *
     * @param dato el dato a agregar
     */
    public void insertarNodo(T dato) {
        if (tamanio() > 0) {
            insertarFinal(dato);
        } else {
            insertar(dato);
        }
    }

    /**
     * *
     * Retorna el tamanio de la Lista
     *
     * @return numero de elemento
     */
    public int tamanio() {
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
     *
     * @return El dato
     */
    public T extraer() {
        T dato = null;
        if (!estaVacia()) {
            dato = (T) cabecera.getDato();
            cabecera = cabecera.getNodoSiguiente();
        }
        return dato;
    }

    /**
     * Permite consultar un dato de la lista por posicion
     *
     * @param pos posicion en la lista
     * @return dato encontrado en la posicion
     */
    public T consultarDatoPosicion(int pos) {
        T dato = null;
        if (!estaVacia() && (pos >= 0 && pos <= tamanio() - 1)) {
            Nodo tmp = cabecera;
            for (int i = 0; i < pos; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null) {
                    break;
                }
            }
            if (tmp != null) {
                dato = (T) tmp.getDato();
            }
        }
        return dato;
    }

    /**
     *
     */
    public void imprimir() {
        Nodo tmp = cabecera;
        while (!estaVacia() && tmp != null) {
            System.out.println(tmp.getDato());
            tmp = tmp.getNodoSiguiente();
        }
    }

    public boolean modificarPorPos(T dato, int pos) {
        if (!estaVacia() && pos <= tamanio() - 1 && pos >= 0) {
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

    public void borrar(int posicion) {
        if (!estaVacia() && (posicion >= 0 && posicion <= tamanio() - 1)) {
            if (posicion == 0) {
                setCabecera(getCabecera().getNodoSiguiente());
            } else {
                Nodo aux = getCabecera();
                Nodo nodoAux = null;
                for (int i = 0; i < tamanio(); i++) {
                    if (posicion != i) {
                        Nodo nodo = new Nodo(aux.getDato(), nodoAux);
                        nodoAux = nodo;
                    }
                    aux = aux.getNodoSiguiente();
                    if (aux == null) {
                        break;
                    }
                }
                this.setCabecera(nodoAux);
            }
        }
    }

    ////////////////Ordenamiento/////////////
    private Field getField(String nombre) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(nombre)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

//    public void testReflect(T dato, String atributo) {
//        try {
//            System.out.println(getField(atributo).get(dato).toString());
//        } catch (Exception e) {
//            System.out.println("Error " + e);
//        }
//        for(Field field:clazz.getFields()){
//            System.out.println(field.getName()+" "+field.getType());
//    }
    public Object value(T dato, String atributo) throws Exception {
        return getField(atributo).get(dato);
    }

    public Lista<T> seleccion_clase(String atributo, Integer ordenacion) {
        //Lista<T> a = this;
        try {
            int i, j, k = 0;
            T t = null;
            int n = tamanio();
            for (i = 0; i < n - 1; i++) {
                k = i;
                t = consultarDatoPosicion(i);
                for (j = i + 1; j < n; j++) {
                    boolean band = false;
                    Object datoT = value(t, atributo);
                    Object datoJ = value(consultarDatoPosicion(j), atributo);
                    if (datoT instanceof Number) {
                        Number aux = (Number) datoT;
                        Number numero = (Number) datoJ;
                        band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                                ? numero.doubleValue() < aux.doubleValue()
                                : numero.doubleValue() > aux.doubleValue();
                    } else {
                        band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                                ? datoT.toString().compareTo(datoJ.toString()) > 0
                                : datoT.toString().compareTo(datoJ.toString()) < 0;
                    }
                    if (band) {
                        t = consultarDatoPosicion(j);
                        k = j;
                    }
                }
                modificarPorPos(consultarDatoPosicion(i), k);
                modificarPorPos(t, i);
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
            e.printStackTrace();
        }
        return null;

    }

    //////////
}
