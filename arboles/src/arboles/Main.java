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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ArbolBinario<Integer> arbol=  new ArbolBinario<>();
       
       arbol.insertar(20);
       arbol.insertar(10);
       arbol.insertar(15);
       arbol.insertar(30);
       arbol.insertar(5);
       arbol.insertar(25);
       arbol.insertar(24);
       System.out.println(arbol.raiz.esHoja());
       if(arbol.buscar(30)){
           System.out.println("el dato existe");
       }else{
           System.out.println("el dato no está en el arbol");
       }
       
       System.out.println(arbol.recorridoEnPreOrden());
       System.out.println(arbol.recorridoEnInOrden());
       System.out.println(arbol.recorridoPorNiveles());
       System.out.println(arbol.altura());
       System.out.println(arbol.size());
    }
    
}
