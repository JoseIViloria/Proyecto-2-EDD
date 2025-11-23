/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * Crea una tabla Hash, necesaria para poder enlazar palabras clave o autores con un determinado título
 * @author Gemelos
 */
public class HashAlt {
    private ElementosHalt[] table;
    private int size;

    /**
     * Constructor de la clase HashAlt
     * @param size - el tamaño de la tabla
     */
    public HashAlt(int size) {
        this.size = size;
        this.table = new ElementosHalt[size];
    }
    
    /**
     * Regresa el objeto ubicado en la posición especificada en la tabla.
     * @param index - La posición del objeto en la tabla
     * @return - un objeto de la clase Elementos_Hash
     * */
    public ElementosHalt get_table(int index){
        return table[index];
    }
    
     /**
     * Cambia un elemento en una posición específica de la tabla por otro que se especifique
     * @param elem - El elemento a insertar
     * @param atIndex -  El índice correspondiente a laposición en donde se quiere insertar.
     */
    public void set_table(ElementosHalt elem, int atIndex){
        this.table[atIndex] = elem;
    }
    
    /**
     * Función que regresa el "Hash" de un "Key" dentro de un objeto Elementos_Hash
     * @param elem - El elemento del cual se desea buscar el hash
     * @return 
     */
    public int get_index(ElementosHalt elem){
        int hash = 0;
        hash = (31*(elem.getKey().length()) + elem.getKey().toLowerCase().charAt(0))/7*(elem.getKey().charAt(elem.getKey().toLowerCase().length()-1)+1);
        return hash % this.getSize();
    }
    
    /**
     * Busca el hash de un elemento y lo inserta en la posición correspondiente de la tabla
     * @param elem  - elemento a insertar
     */
    public void insertar(ElementosHalt elem){
        int index = this.get_index(elem);
        if(this.get_table(index)==null){
            this.set_table(elem, index);
        }
        else{
            ElementosHalt aux = this.get_table(index);
            while(aux.getNext()!=null){
                aux = aux.getNext();
            }
            aux.setNext(elem);
        }
    }
    
     /**
     * A partir de un String, busca el elemento correspondiente en la tabla
     * @param elem - El campo "título" del eleento que se quiere buscar
     * @return 
     */
    public ElementosHalt buscar(String elem){
        ElementosHalt aux = new ElementosHalt(elem, null);
        int index = this.get_index(aux);
        return this.get_table(index);
    }

    /**
     * @return size - el tamaño de la tabla
     */
    public int getSize() {
        return size;
    }
    
}
