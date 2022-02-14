/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.Controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import lista.Model.Nodo;
import modelo.Caja;
import modelo.Galpon;

/**
 *
 * @author Home
 * @param <T>
 */
public class Lista<T> implements Serializable {

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

    public Lista<T> Ordenar_Shell(String atributo, Lista ganancias) {
        //Lista<T> auto = this;
        try {
            int i, salto;
            int n = tamanio() / 2;
            T aux;
            boolean cambio;
            for (salto = n; salto != 0; salto /= 2) {
                cambio = true;
                while (cambio) {
                    cambio = false;
                    for (i = salto; i < tamanio(); i++) {
                        Object datoT = value(consultarDatoPosicion(i - salto), atributo);
                        Object datoJ = value(consultarDatoPosicion(i), atributo);
                        if (datoT.toString().compareTo(datoJ.toString()) > 0) {
                            aux = consultarDatoPosicion(i);
                            modificarPorPos(consultarDatoPosicion(i - salto), i);
                            modificarPorPos(aux, i - salto);
                            cambio = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    /**
     * Ordena a los numero de pollos muertos
     * @param galpon es el modelo para acceder a sus atributos
     * @param primero tomar el valor inicial para ordenar
     * @param ultimo
     * @return lista de galpones
     */
    public Lista<Galpon> ordenarQuicksort3(Lista<Galpon> galpon, int primero, int ultimo) {
        int i, j, central;
        Galpon pivote = null;
        central = (primero + ultimo) / 2;
        pivote = (Galpon) galpon.consultarDatoPosicion(central);
        i = primero;
        j = ultimo;
        do {

            while (galpon.consultarDatoPosicion(i).getPollosMuertos().compareTo(pivote.getPollosMuertos()) <0) {
                i++;
            }
            while (pivote.getPollosMuertos().compareTo(galpon.consultarDatoPosicion(j).getPollosMuertos())<0) {
                j--;
            }
            if (i <= j) {
                Galpon aux = galpon.consultarDatoPosicion(i);
                galpon.modificarPorPos(galpon.consultarDatoPosicion(j), i);
                galpon.modificarPorPos(aux, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuicksort3(galpon, primero, j);
        }
        if (i < ultimo) {
            ordenarQuicksort3(galpon, i, ultimo);
        }
        return galpon;
    }
    /**
     * Ordena las ganancias por quiksort
     * @param caja recibe la lista caja
     * @param primero inicia ordenar tomanto el valor primero
     * @param ultimo termina en el ultimo valor
     * @return Lista caja
     */
    public Lista<Caja> ordenarQuicksort(Lista<Caja> caja, int primero, int ultimo) {
        int i, j, central;
        Caja pivote = null;
        central = (primero + ultimo) / 2;
        pivote = (Caja) caja.consultarDatoPosicion(central);
        i = primero;
        j = ultimo;
        do {

            while (caja.consultarDatoPosicion(i).getGanancia().compareTo(pivote.getGanancia()) <0) {
                i++;
            }
            while (pivote.getGanancia().compareTo(caja.consultarDatoPosicion(j).getGanancia())<0) {
                j--;
            }
            if (i <= j) {
                Caja aux = caja.consultarDatoPosicion(i);
                caja.modificarPorPos(caja.consultarDatoPosicion(j), i);
                caja.modificarPorPos(aux, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuicksort(caja, primero, j);
        }
        if (i < ultimo) {
            ordenarQuicksort(caja, i, ultimo);
        }
        return caja;
    }
}
