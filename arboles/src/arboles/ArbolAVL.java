package arboles;

import java.util.List;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinario<T> {

    // Método de inserción que se adapta para un árbol AVL
    @Override
    public void insertar(T dato) {
        this.raiz = insertar(this.raiz, dato);
    }

    // Método auxiliar de inserción para mantener el balanceo del árbol AVL
    private NodoBinario<T> insertar(NodoBinario<T> nodoActual, T dato) {
        if (nodoActual == null) {
            return new NodoBinario<>(dato);
        }

        // Insertamos en el árbol de forma normal (BST)
        if (dato.compareTo(nodoActual.getDato()) < 0) {
            nodoActual.setHijoIzquierdo(insertar(nodoActual.getHijoIzquierdo(), dato));
        } else if (dato.compareTo(nodoActual.getDato()) > 0) {
            nodoActual.setHijoDerecho(insertar(nodoActual.getHijoDerecho(), dato));
        }

        // Después de insertar, balanceamos el nodo actual
        return balancear(nodoActual);
    }

    // Método de eliminación para un árbol AVL
    @Override
    public boolean eliminar(T dato) {
        NodoBinario<T> nodoEliminado = new NodoBinario<>();
        this.raiz = eliminar(this.raiz, dato, nodoEliminado);
        return nodoEliminado != null;
    }

    // Método auxiliar de eliminación para un árbol AVL
    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual, T dato, NodoBinario<T> nodoEliminado) {
        if (nodoActual == null) {
            return null;
        }

        // Buscamos el nodo a eliminar
        if (dato.compareTo(nodoActual.getDato()) < 0) {
            nodoActual.setHijoIzquierdo(eliminar(nodoActual.getHijoIzquierdo(), dato, nodoEliminado));
        } else if (dato.compareTo(nodoActual.getDato()) > 0) {
            nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), dato, nodoEliminado));
        } else {
            nodoEliminado.setDato(nodoActual.getDato()); // Nodo encontrado

            // Caso 1: Nodo sin hijos
            if (nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
                return null;
            }

            // Caso 2: Nodo con un solo hijo
            if (nodoActual.esVacioHijoIzquierdo()) {
                return nodoActual.getHijoDerecho();
            } else if (nodoActual.esVacioHijoDerecho()) {
                return nodoActual.getHijoIzquierdo();
            }

            // Caso 3: Nodo con dos hijos, buscamos el sucesor
            NodoBinario<T> sucesor = nodoActual.getHijoDerecho();
            while (sucesor != null && !sucesor.esVacioHijoIzquierdo()) {
                sucesor = sucesor.getHijoIzquierdo();
            }

            nodoActual.setDato(sucesor.getDato());
            nodoActual.setHijoDerecho(eliminar(nodoActual.getHijoDerecho(), sucesor.getDato(), nodoEliminado));
        }

        // Balanceamos después de la eliminación
        return balancear(nodoActual);
    }

    // Método para balancear el árbol AVL
    private NodoBinario<T> balancear(NodoBinario<T> nodoActual) {
        if (nodoActual == null) {
            return null;
        }

        int balance = calcularBalance(nodoActual);

        // Rotaciones según el factor de balance
        if (balance > 1) {
            if (calcularBalance(nodoActual.getHijoIzquierdo()) < 0) {
                nodoActual.setHijoIzquierdo(rotacionSimpleIzquierda(nodoActual.getHijoIzquierdo()));
            }
            return rotacionSimpleDerecha(nodoActual);
        }
        if (balance < -1) {
            if (calcularBalance(nodoActual.getHijoDerecho()) > 0) {
                nodoActual.setHijoDerecho(rotacionSimpleDerecha(nodoActual.getHijoDerecho()));
            }
            return rotacionSimpleIzquierda(nodoActual);
        }

        return nodoActual; // Si el árbol está balanceado
    }

    // Método para calcular el factor de balance (altura izquierda - altura derecha)
    private int calcularBalance(NodoBinario<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getHijoIzquierdo()) - altura(nodo.getHijoDerecho());
    }

    // Rotación simple a la derecha
    private NodoBinario<T> rotacionSimpleDerecha(NodoBinario<T> nodo) {
        NodoBinario<T> nuevoRaiz = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(nuevoRaiz.getHijoDerecho());
        nuevoRaiz.setHijoDerecho(nodo);
        return nuevoRaiz;
    }

    // Rotación simple a la izquierda
    private NodoBinario<T> rotacionSimpleIzquierda(NodoBinario<T> nodo) {
        NodoBinario<T> nuevoRaiz = nodo.getHijoDerecho();
        nodo.setHijoDerecho(nuevoRaiz.getHijoIzquierdo());
        nuevoRaiz.setHijoIzquierdo(nodo);
        return nuevoRaiz;
    }

    // Métodos para obtener la altura de un nodo
    private int altura(NodoBinario<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzq = altura(nodo.getHijoIzquierdo());
        int alturaDer = altura(nodo.getHijoDerecho());
        return Math.max(alturaIzq, alturaDer) + 1;
    }

    @Override
    public List<T> recorridoEnInOrden() {
        return super.recorridoEnInOrden();
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        return super.recorridoEnPreOrden();
    }

    @Override
    public List<T> recorridoEnPostOrden() {
        return super.recorridoEnPostOrden();
    }

    @Override
    public List<T> recorridoPorNiveles() {
        return super.recorridoPorNiveles();
    }
    
    @Override
    public int size() {
        return super.size();
    }

    @Override
    public int altura() {
        return super.altura();
    }
}
