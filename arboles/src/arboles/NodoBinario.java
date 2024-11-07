/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author ASUS
 */
public class NodoBinario <T extends Comparable<T>>{
    private T dato;
    private NodoBinario<T> hijoIzquierdo;
    private NodoBinario<T> hijoDerecho;
    public NodoBinario(){}
    public NodoBinario(T dato){
       this.dato=dato;
    }
    public T getDato(){
        return dato;
    }
    public NodoBinario<T> getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    public NodoBinario<T> getHijoDerecho(){
        return this.hijoDerecho;
    }
    public void setDato(T dato){
        this.dato=dato;
    }
    
    public void setHijoIzquierdo(NodoBinario<T> hijo){
        this.hijoIzquierdo=hijo;
    }
    
    public void setHijoDerecho(NodoBinario<T> hijo){
        this.hijoDerecho=hijo;
    }
    public static NodoBinario nodoVacio(){
        return null;
    }
    
    public boolean esVacioHijoIzquierdo(){
       return this.hijoIzquierdo== NodoBinario.nodoVacio();
    }
    
    public boolean esVacioHijoDerecho(){
        return this.hijoDerecho == NodoBinario.nodoVacio();
    }
    
    public boolean esHoja(){
        return esVacioHijoIzquierdo() && this.esVacioHijoDerecho();
    }
    public boolean esPadre(){
        return !esVacioHijoIzquierdo() || !this.esVacioHijoDerecho();
    }
    public boolean esPadreCompleto(){
        return !esVacioHijoIzquierdo() && !this.esVacioHijoDerecho();
    }
    
    public static boolean esNodoVacio(NodoBinario<?> nodo){
        return nodo == NodoBinario.nodoVacio();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
