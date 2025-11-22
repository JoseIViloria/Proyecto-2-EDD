/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 *
 * @author 
 */
public class HashTable {
    private Elementos_Hash[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new Elementos_Hash[size];
    }
    
    public Elementos_Hash get_table(int index){
        return table[index];
    }
    
    public void set_table(Elementos_Hash elem, int atIndex){
        this.table[atIndex] = elem;
    }
    
    public int get_index(Elementos_Hash elem){
        int hash = 0;
        for(int i=0; i<elem.getTitulo().length(); i++){
            hash = (31*(i) + elem.getTitulo().charAt(i))/2*(i+1);
        }
        return hash % this.getSize();
    }
    
    public void insertar(Elementos_Hash elem){
        int index = this.get_index(elem);
        this.set_table(elem, index);
    }
    
    public Elementos_Hash buscar(String elem){
        Elementos_Hash aux = new Elementos_Hash(elem, null, null, null);
        int index = this.get_index(aux);
        return this.get_table(index);
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
}
