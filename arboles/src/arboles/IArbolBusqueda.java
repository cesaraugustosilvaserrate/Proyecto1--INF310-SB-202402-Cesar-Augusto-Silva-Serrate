/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IArbolBusqueda <T extends Comparable<T>>{
    
    public void insertar(T dato);
    public boolean eliminar(T dato);
    public boolean buscar(T dato);
    public boolean contiene(T dato);
    public List<T> recorridoEnInOrden();
    public List<T> recorridoEnPreOrden();
    public List<T> recorridoEnPostOrden();
    public List<T> recorridoPorNiveles();
    public int size();
    public int altura();
    public void vaciar();
    public boolean esArbolVacio();
    public int nivel();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
