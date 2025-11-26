/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 *Nodo en un AVL
 * Donde cada nodo guarda un autor y mantiene referencia en sus hijos izq y dch
 * tambien la altura ,para poder mantener el balance
 * @author 
 */
public class Nodo {
    private Nodo hijo_der;
    private Nodo hijo_izq;
    private String autor;
    private int altura;
    
    /** crea un nuevo nodo con el autor especifico
     * inicia con los hijos apuntando a null y su altura en 0
     * @param autor Nombre del autor que se guardara en el nodo
     */

    public Nodo(String autor) {
        this.hijo_der = null;
        this.hijo_izq = null;
        this.autor = autor;
        this.altura = 0;
    }

    /**
     * @param hijo_der- nuevo nodo hijo derecho
     */
    public void setHijo_der(Nodo hijo_der) {
        this.hijo_der = hijo_der;
    }
    /**
     * 
     * @return Hijo dch del nodo o null si no existe
     */
    public Nodo getHijo_der() {
        return hijo_der; 
    }

    /**
     * @return  hijo_izq del nodo
     */
    public Nodo getHijo_izq() {
        return hijo_izq;
    }

    /**
     * @param hijo_izq Nuevo nodo hijo_izq to set
     */
    public void setHijo_izq(Nodo hijo_izq) {
        this.hijo_izq = hijo_izq;
    }

    /**
     * obtiene el autor almacenado en el nodo
     * @return Nombre del autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor Nuevo nombre de autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * obtiene la altura del nodo en el arbol
     * la cual representa la longitud del el camino 
     * mas largo desde el nodo hasta una hoja
     * @return altura del nodo
     */

       public int getAltura() {
        return altura;
    }
       /** 
        * establece la altura del nodo en el arbol
        * @param altura - nueva altura del nodo
        */
       
       public void setAltura(int altura){
           this.altura=altura;
       }

    
}
