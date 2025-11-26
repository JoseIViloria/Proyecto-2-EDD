/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;
import java.util.Locale;
import java.text.Collator;

/**
 * Representacion de AVL
 * Para guardar y gestionar autores con ordenamiento en español
 * @author 
 */
public class Arbol {
    private Nodo root;
    private Collator collator;;
    /** 
     * inicializacion de un arbol vacio mediante constructor
     */
    
    public Arbol() {
        this.root = null;
        this.collator = Collator.getInstance(new Locale("es", "ES"));
    }
    /** 
     * Verifica si el arbol esta vacio
     * 
     * @param tRoot Nodo riaz del arbol a verificar
     * @return true si el arbol es vacio y false si no esta vacio
     */
    public boolean esVacio(Nodo tRoot){
        return tRoot==null;
    }
    /**
     * obtiene la raiz del arbol
     * @return Nodo raiz del arbol
     */
    public Nodo getroot(){
        return this.root;
    }
        /**Establece la raiz del arbol
     * 
     * @param n Nuevo nodo raiz
     */
    public void setroot(Nodo n){
        this.root=n;
    }
    /**devuelve el max entre dos numeros enteros
     * 
     * @param a primer numero
     * @param b segundo numero
     * @return El numero mayor entre a y b
     */
    
    public int máximo(int a, int b){
        if(a>b){
            return a;
        }
        else{
            return b;
        }
    }
    
    /** 
     * calcula la altura del nodo en el arbol
     * @param n Nodo del cual se calcula la altura
     * @return Altura del nodo, -1 si el nodo es nulo
     */
    
    public int altura(Nodo n){
        if (n==null){
            return -1;
        }
        else{
            return n.getAltura();
        }
    }
    
    /** 
     * calcula el balance de un nodo
     * el balance es la resta entre la altura del hijo izquierdo y la del derecho
     * @param n Nodo del cual se calculara el balance
     * @return Balance del nodo, 0 si el nodo es nulo
     */
    
    public int balance(Nodo n){
        if (n==null){
            return 0;
        }
        return altura(n.getHijo_izq()) - altura(n.getHijo_der());
    }
    
    /** inserta un nuevo autor en el arbol manteniendo el balance AVL
     * 
     * @param raíz Nodo raiz del subarbol donde se insertara
     * @param autor Autor a insertar
     * @return Nueva raiz del hijo despues de la insercion y el balance
     */
    
    public Nodo insertar(Nodo raíz, String autor){
        if (esVacio(raíz)){
            Nodo nuevo = new Nodo(autor);
            nuevo.setAltura(0);
            return nuevo;
        }
        if(this.collator.compare(autor, raíz.getAutor())<0){
            raíz.setHijo_izq(insertar(raíz.getHijo_izq(), autor));
        }
        else if(this.collator.compare(autor,raíz.getAutor())>0){
            raíz.setHijo_der(insertar(raíz.getHijo_der(), autor));
        } else{
            return raíz;
        }
        actualizaraltura(raíz);
        int balance = balance(raíz);
        if(balance>1){
            if(balance(raíz.getHijo_izq())>=0){
                return rotarderecha(raíz);
            }
            else if(balance(raíz.getHijo_izq())<0){
                raíz.setHijo_izq(rotarizquierda(raíz.getHijo_izq()));
                return rotarderecha(raíz);
            }
        }
        if(balance<-1){
            if(balance(raíz.getHijo_der())<=0){
                return rotarizquierda(raíz);
            }
            else if(balance(raíz.getHijo_der())>0){
                raíz.setHijo_der(rotarderecha(raíz.getHijo_der()));
                return rotarizquierda(raíz);
            }
        }
        return raíz;
    }
    /** 
     * actualiza la altura de un nodo , mediante la altura de sus hijos
     * @param n Nodo cuya altura se actualizara
     */
    
    public void actualizaraltura(Nodo n){
        if (n!=null){
            n.setAltura(máximo(altura(n.getHijo_izq()),altura(n.getHijo_der()))+1);
        }
    }
    /** 
     * realiza una rotacion simple a la dch para balancear el arbol
     * @param n Nodo que se rotara
     * @return Nueva raiz del subarbol despues de la rotacion
     */
    
    public Nodo rotarderecha(Nodo n){
        Nodo X;
        Nodo izq=n.getHijo_izq();
        if (izq!=null){
            X=izq.getHijo_der();
        }
        else{
            X=null;
        }
        izq.setHijo_der(n);
        n.setHijo_izq(X);
        actualizaraltura(n);
        actualizaraltura(izq);
        return izq;
    }
    /** 
     * realiza una rotacion simple a la izq para asi balancear el arbol
     * @param n Nodo que se rotara
     * @return Nueva raiz del subarbol despues de la rotacion
     */
    
    public Nodo rotarizquierda(Nodo n){
        Nodo X;
        Nodo der = n.getHijo_der();
        if(der!=null){
            X=der.getHijo_izq();
        }
        else{
            X=null;
        }
        der.setHijo_izq(n);
        n.setHijo_der(X);
        actualizaraltura(n);
        actualizaraltura(der);
        return der;
    }    
    
    /** 
     * metodo public para insertsr un autor en el arbol
     * actualiza la rais del arbol despues de insertar
     * @param raíz Nodo raiz del arbol
     * @param AUTOR Autor a insertar
     */
    
    public void insertarnodo(Nodo raíz, String AUTOR){
        this.root = insertar(this.root, AUTOR);
    }    
   /** recorrido inorden del arbol que retorna una cadena con los autores ordenados
    * 
    * @param a Nodo raiz del subarbol a recorrer
    * @param x Cadena acumuladora para el resultado
    * @return  Cadena con los autores en orden, separados con comas
    */
    public String inorden(Nodo a, String x){
        if (a!=null){
            x = inorden(a.getHijo_izq(),x);
            x += a.getAutor() + ", ";
            x = inorden(a.getHijo_der(),x);
        }
        return x;
    }
}
