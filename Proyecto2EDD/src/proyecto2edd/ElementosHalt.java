/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 *  Esta clase funciona para crear elementos dentro de un hash table, lo que permite relacionar a autores o palabras clave con un título.
 * @author Gemelos
 */
public class ElementosHalt {
    private String key;
    private String titulo;
    private ElementosHalt next;
    private int frecuencia;
    /** constructor para crear un nuevo elemento para la tabla hash
     * 
     * @param key clave del elemento (autor o palabra clave)
     * @param titulo Titulo del resumen asociado a la key
     * 
     */
    
    public ElementosHalt(String key, String titulo) {
        this.key = key;
        this.titulo = titulo;
        this.next = null;
        this.frecuencia = 0;
    }
    
    /**
     * obtencio de la frecuencia de apariciones del elemento 
     * @return frecuencia actual del elemento
     */
    public int getFrecuencia() {
        return frecuencia;
    }
    
    /**
     * 
     * @param frecuencia - la nueva frecuencia
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    

    /**
     * obtener la clave del elemeto (autor o palabra clave)
     * @return key del elemento
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key - el nuevo "key"
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return titulo - el titulo del resumen al que pertenece "key"
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo - el nuevo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Cambia el "apuntador" a un siguiente elemento
     * @param next - el siguiente elemento ElementosHalt
     */
    public void setNext(ElementosHalt next) {
        this.next = next;
    }

    /**
     * @return next - el siguiente elemento
     */
    public ElementosHalt getNext() {
        return next;
    }
}
