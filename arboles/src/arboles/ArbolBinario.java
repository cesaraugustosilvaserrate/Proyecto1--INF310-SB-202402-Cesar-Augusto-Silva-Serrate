/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author ASUS
 */
public class ArbolBinario<T extends Comparable<T>> implements IArbolBusqueda<T> {

    NodoBinario<T> raiz;

    @Override
    public void insertar(T dato) {
        NodoBinario<T> nodoNuevo = new NodoBinario<>(dato);
        if (this.esArbolVacio()) {
            this.raiz = nodoNuevo;
        } else {
            NodoBinario<T> nodoActual = this.raiz;

            T datoActual;
            while (!NodoBinario.esNodoVacio(nodoActual)) {

                datoActual = nodoActual.getDato();
                if (dato.compareTo(datoActual) > 0) {
                    if (nodoActual.esVacioHijoDerecho()) {
                        nodoActual.setHijoDerecho(nodoNuevo);
                        return;
                    } else {
                        nodoActual = nodoActual.getHijoDerecho();
                    }

                } else if (dato.compareTo(datoActual) == 0) {
                    return;
                } else if (dato.compareTo(datoActual) < 0) {
                    if (nodoActual.esVacioHijoIzquierdo()) {
                        nodoActual.setHijoIzquierdo(nodoNuevo);
                    } else {
                        nodoActual = nodoActual.getHijoIzquierdo();
                    }

                }
            }
        }
    }

    @Override
public boolean eliminar(T dato) {
    NodoBinario<T> nodoActual = this.raiz;
    NodoBinario<T> nodoPadre = null;
    boolean esHijoIzquierdo = false;

    // Buscamos el nodo a eliminar
    while (nodoActual != null && !nodoActual.getDato().equals(dato)) {
        nodoPadre = nodoActual;
        if (dato.compareTo(nodoActual.getDato()) < 0) {
            nodoActual = nodoActual.getHijoIzquierdo();
            esHijoIzquierdo = true;
        } else {
            nodoActual = nodoActual.getHijoDerecho();
            esHijoIzquierdo = false;
        }
    }

    if (nodoActual == null) {
        return false; // El dato no se encontró
    }

    // Caso 1: El nodo a eliminar no tiene hijos (es una hoja)
    if (nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
        if (nodoActual == raiz) {
            raiz = null; // Si es la raíz, la hacemos null
        } else if (esHijoIzquierdo) {
            nodoPadre.setHijoIzquierdo(null); // Eliminar el hijo izquierdo
        } else {
            nodoPadre.setHijoDerecho(null); // Eliminar el hijo derecho
        }
    }
    // Caso 2: El nodo a eliminar tiene un solo hijo (solo hijo izquierdo o solo derecho)
    else if (nodoActual.esVacioHijoDerecho()) {
        if (nodoActual == raiz) {
            raiz = nodoActual.getHijoIzquierdo(); // El hijo izquierdo sube a la raíz
        } else if (esHijoIzquierdo) {
            nodoPadre.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        } else {
            nodoPadre.setHijoDerecho(nodoActual.getHijoIzquierdo());
        }
    } else if (nodoActual.esVacioHijoIzquierdo()) {
        if (nodoActual == raiz) {
            raiz = nodoActual.getHijoDerecho(); // El hijo derecho sube a la raíz
        } else if (esHijoIzquierdo) {
            nodoPadre.setHijoIzquierdo(nodoActual.getHijoDerecho());
        } else {
            nodoPadre.setHijoDerecho(nodoActual.getHijoDerecho());
        }
    }
    // Caso 3: El nodo a eliminar tiene dos hijos
    else {
        // Encontrar el sucesor (mínimo en el subárbol derecho)
        NodoBinario<T> sucesor = nodoActual.getHijoDerecho();
        NodoBinario<T> nodoSucesorPadre = nodoActual;

        // Buscar el nodo más a la izquierda en el subárbol derecho
        while (!sucesor.esVacioHijoIzquierdo()) {
            nodoSucesorPadre = sucesor;
            sucesor = sucesor.getHijoIzquierdo();
        }

        // Reemplazar el dato del nodo actual con el dato del sucesor
        nodoActual.setDato(sucesor.getDato());

        // Eliminar el sucesor, que puede tener un solo hijo o no tener hijos
        if (nodoSucesorPadre.getHijoIzquierdo() == sucesor) {
            nodoSucesorPadre.setHijoIzquierdo(sucesor.getHijoDerecho());
        } else {
            nodoSucesorPadre.setHijoDerecho(sucesor.getHijoDerecho());
        }
    }

    return true;
}

    @Override
    public boolean buscar(T dato) {

        if (!this.esArbolVacio()) {
            NodoBinario<T> nodoActual = this.raiz;

            T datoActual;
            while (!NodoBinario.esNodoVacio(nodoActual)) {

                datoActual = nodoActual.getDato();
                if (dato.compareTo(datoActual) > 0) {

                    nodoActual = nodoActual.getHijoDerecho();

                } else if (dato.compareTo(datoActual) == 0) {
                    return true;
                } else if (dato.compareTo(datoActual) < 0) {

                    nodoActual = nodoActual.getHijoIzquierdo();

                }
            }
        }
        return false;

    }

    @Override
    public boolean contiene(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> recorridoEnInOrden() {
        List<T> listaRecorrido = new ArrayList<>();
        if (!NodoBinario.esNodoVacio(raiz)) {
            NodoBinario<T> actual = this.raiz;
            Stack<NodoBinario<T>> pila = new Stack<>();

            // Recorrer hasta que todos los nodos sean procesados
            while (!pila.isEmpty() || actual != null) {
                // Desplazarse a la izquierda lo más posible
                if (actual != null) {
                    pila.push(actual);
                    actual = actual.getHijoIzquierdo();
                } else {
                    // Procesar nodo y moverse al hijo derecho
                    actual = pila.pop();
                    listaRecorrido.add(actual.getDato());
                    actual = actual.getHijoDerecho();
                }
            }
        }

        return listaRecorrido;
    }

    @Override
    public List<T> recorridoEnPreOrden() {

        List<T> listaDatos = new ArrayList<>();
        if (!NodoBinario.esNodoVacio(raiz)) {
            Stack<NodoBinario<T>> pila = new Stack<>();
            NodoBinario<T> nodoAux = this.raiz;
            //NodoBinario2<T> nodoAnterior;
            do {
                listaDatos.add(nodoAux.getDato());
                //nodoAnterior = nodoAux;
                if (!nodoAux.esVacioHijoDerecho()) {
                    pila.push(nodoAux.getHijoDerecho());
                }
                nodoAux = nodoAux.getHijoIzquierdo();

                if (NodoBinario.esNodoVacio(nodoAux)
                        && !pila.isEmpty()) {
                    nodoAux = pila.pop();
                }

            } while (!NodoBinario.esNodoVacio(nodoAux));
        }

        return listaDatos;
    }

    @Override
    public List<T> recorridoEnPostOrden() {
        List<T> listaDatos = new ArrayList<>();
        if (!NodoBinario.esNodoVacio(raiz)) {
            Stack<NodoBinario<T>> pila1 = new Stack<>();
            Stack<NodoBinario<T>> pila2 = new Stack<>();
            NodoBinario<T> nodoAux = this.raiz;

            pila1.push(nodoAux);

            // Mientras haya nodos en pila1, procesar
            while (!pila1.isEmpty()) {
                nodoAux = pila1.pop();
                pila2.push(nodoAux);

                if (!nodoAux.esVacioHijoIzquierdo()) {
                    pila1.push(nodoAux.getHijoIzquierdo());
                }
                if (!nodoAux.esVacioHijoDerecho()) {
                    pila1.push(nodoAux.getHijoDerecho());
                }
            }

            // Extraer los nodos de pila2, que están en postorden
            while (!pila2.isEmpty()) {
                listaDatos.add(pila2.pop().getDato());
            }
        }

        return listaDatos;
    }


    @Override
    public List<T> recorridoPorNiveles() {
        List<T> listaRec = new ArrayList<>();
        Queue<NodoBinario<T>> cola = new LinkedList<>();
        if (!NodoBinario.esNodoVacio(this.raiz)) {
            cola.offer(raiz);
        }
        NodoBinario<T> nodoAux = new NodoBinario<>();
        while (!cola.isEmpty()) {
            nodoAux = cola.poll();
            if (!nodoAux.esVacioHijoIzquierdo()) {
                cola.offer(nodoAux.getHijoIzquierdo());
            }
            if (!nodoAux.esVacioHijoDerecho()) {
                cola.offer(nodoAux.getHijoDerecho());
            }
            listaRec.add(nodoAux.getDato());

        }

        return listaRec;
    }

    @Override
    public int size() {
        if(NodoBinario.esNodoVacio(this.raiz)){
            return 0;
        }
        Queue<NodoBinario<T>> cola= new LinkedList<>();
        NodoBinario<T> nodoAux;
        int tamañoDenivel, contador=0;
        cola.offer(raiz);
        while(!cola.isEmpty()){
            tamañoDenivel=cola.size();
            contador+=tamañoDenivel;
            for(int i=0; i<tamañoDenivel;i++){
                nodoAux=cola.poll();
                if(!nodoAux.esVacioHijoIzquierdo()){
                    cola.offer(nodoAux.getHijoIzquierdo());
                }
                if(!nodoAux.esVacioHijoDerecho()){
                    cola.offer(nodoAux.getHijoDerecho());
                }
                
            }
            
            
        }
        
        
        return contador;
        
        
    }

    @Override
    public int altura() {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0; // Un árbol vacío tiene altura 0
        }
        Queue<NodoBinario<T>> cola = new LinkedList<>();
        cola.offer(this.raiz);
        int altura = 0;
        int tamañoNivel;
        while (!cola.isEmpty()) {
             tamañoNivel = cola.size(); // Número de nodos en el nivel actual

            // Recorrer todos los nodos en el nivel actual
            for (int i = 0; i < tamañoNivel; i++) {
                NodoBinario<T> nodoActual = cola.poll();

                // Añadir los hijos del nodo actual a la cola
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    cola.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    cola.offer(nodoActual.getHijoDerecho());
                }
            }

            // Incrementar la altura después de procesar un nivel completo
            altura++;
        }

        return altura;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int nivel() {
        if(NodoBinario.esNodoVacio(raiz)){
            return 0;
        }else{
            return altura()-1;
        }
        
    }

}
