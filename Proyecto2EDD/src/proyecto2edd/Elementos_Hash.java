/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * 
 * @author Gemelos
 */
public class Elementos_Hash {
    private String titulo;
    private String[] autores;
    private String resumen;
    private String[] p_clave;
    private Elementos_Hash next;
    /** 
     * crea un nuevo elemento de resumen 
     * 
     * @param titulo - titulo del resumen
     * @param autores - arreglo de autores 
     * @param resumen - contenido textual 
     * @param p_clave - arreglo de palabras claves relacionadas
     */

    public Elementos_Hash(String titulo, String[] autores, String resumen, String[] p_clave) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.p_clave = p_clave;
        this.next = null;
    }

    /**
     * @return titulo - titulo del resumen
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo - nuevo titulo al que se quiere cambiar
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return autores- arreglo de nombres de autores
     */
    public String[] getAutores() {
        return autores;
    }

    /**
     * @param autores - el atributo autores al que se quiere cambiar
     */
    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    /**
     * @return resumen - obtiene el contenido del resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * establece un nuevo contenido para el resumen
     * @param resumen - el resumen al que se quiere cambiar
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return p_clave- obtiene el arreglo de palabras clave
     */
    public String[] getP_clave() {
        return p_clave;
    }

    /**
     * nuevo arreglo de palabras clave 
     * @param p_clave - p_clave al que se quiere cambiar
     */
    public void setP_clave(String[] p_clave) {
        this.p_clave = p_clave;
    }
}
