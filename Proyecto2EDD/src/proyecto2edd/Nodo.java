/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 *
 * @author 
 */
public class Nodo {
    private Nodo hijo_der;
    private Nodo hijo_izq;
    private String autor;
    private int altura;

    public Nodo(String autor) {
        this.hijo_der = null;
        this.hijo_izq = null;
        this.autor = autor;
        this.altura = 0;
    }

    /**
     * @param hijo_der the hijo_der to set
     */
    public void setHijo_der(Nodo hijo_der) {
        this.hijo_der = hijo_der;
    }
    
    public Nodo getHijo_der() {
        return hijo_der; 
    }

    /**
     * @return the hijo_izq
     */
    public Nodo getHijo_izq() {
        return hijo_izq;
    }

    /**
     * @param hijo_izq the hijo_izq to set
     */
    public void setHijo_izq(Nodo hijo_izq) {
        this.hijo_izq = hijo_izq;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

       public int getAltura() {
        return altura;
    }
       
       public void setAltura(int altura){
           this.altura=altura;
       }

    
}
