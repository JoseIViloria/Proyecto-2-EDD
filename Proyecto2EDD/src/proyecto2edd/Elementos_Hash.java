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

    public Elementos_Hash(String titulo, String[] autores, String resumen, String[] p_clave) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.p_clave = p_clave;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autores
     */
    public String[] getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    /**
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the p_clave
     */
    public String[] getP_clave() {
        return p_clave;
    }

    /**
     * @param p_clave the p_clave to set
     */
    public void setP_clave(String[] p_clave) {
        this.p_clave = p_clave;
    }
}
