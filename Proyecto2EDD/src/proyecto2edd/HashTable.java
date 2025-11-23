/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * Clase que permite crear tablas hash o tablas de dispersión.
 * Almacena objetos de tipo Elementos_Hash.
 * Cada objeto se almacena en un índice que dependerá correspondiente al campo "Titulo" del objeto Elementos_Hash 
 * @see Elementos_Hash.
 * @author 
 */
public class HashTable {
    private Elementos_Hash[] table;
    private int size;

    /**
     * Constructor de la clase HashTable
     * @param size - El tamaño de la tabla
     */
    public HashTable(int size) {
        this.size = size;
        this.table = new Elementos_Hash[size];
    }
    
    /**
     * Regresa el objeto ubicado en la posición especificada en la tabla.
     * @param index - La posición del objeto en la tabla
     * @return - un objeto de la clase Elementos_Hash
     */
    public Elementos_Hash get_table(int index){
        return table[index];
    }
    
    /**
     * Cambia un elemento en una posición específica de la tabla por otro que se especifique
     * @param elem - El elemento a insertar
     * @param atIndex -  El índice correspondiente a laposición en donde se quiere insertar.
     */
    public void set_table(Elementos_Hash elem, int atIndex){
        this.table[atIndex] = elem;
    }
    
    /**
     * Función que regresa el "Hash" de um titulo dentro de un objeto Elementos_Hash
     * @param elem - El elemento del cual se desea buscar el hash
     * @return 
     */
    public int get_index(Elementos_Hash elem){
        int hash = 0;
        for(int i=0; i<elem.getTitulo().length(); i++){
            hash = (31*(i) + elem.getTitulo().charAt(i))/2*(i+1);
        }
        return hash % this.getSize();
    }
    
    /**
     * Busca el hash de un elemento y lo inserta en la posición correspondiente de la tabla
     * @param elem  - elemento a insertar
     */
    public void insertar(Elementos_Hash elem){
        int index = this.get_index(elem);
        this.set_table(elem, index);
    }
    
    /**
     * A partir de un título, busca la posición correspondiente del elemento con ese título en la tabla
     * @param elem - El campo "título" del eleento que se quiere buscar
     * @return 
     */
    public Elementos_Hash buscar(String elem){
        Elementos_Hash aux = new Elementos_Hash(elem, null, null, null);
        int index = this.get_index(aux);
        return this.get_table(index);
    }

    /**
     * @return size - el tamaño de la tabla
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size - el tamaño al que se quiere cambiar
     */
    public void setSize(int size) {
        this.size = size;
    }
}
