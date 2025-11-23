/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;
import java.util.Locale;
import java.text.Collator;

/**
 *
 * @author 
 */
public class Arbol {
    private Nodo root;
    private Collator collator;;
    
    public Arbol() {
        this.root = null;
        this.collator = Collator.getInstance(new Locale("es", "ES"));
    }
    
    public boolean esVacio(Nodo tRoot){
        return tRoot==null;
    }
    
    public Nodo getroot(){
        return this.root;
    }
    
    public void setroot(Nodo n){
        this.root=n;
    }
    
    public int máximo(int a, int b){
        if(a>b){
            return a;
        }
        else{
            return b;
        }
    }
    
    public int altura(Nodo n){
        if (n==null){
            return -1;
        }
        else{
            return n.getAltura();
        }
    }
    
    public int balance(Nodo n){
        if (n==null){
            return 0;
        }
        return altura(n.getHijo_izq()) - altura(n.getHijo_der());
    }
    
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
    
    public void actualizaraltura(Nodo n){
        if (n!=null){
            n.setAltura(máximo(altura(n.getHijo_izq()),altura(n.getHijo_der()))+1);
        }
    }
    
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
   
    
    
    public void insertarnodo(Nodo raíz, String AUTOR){
        this.root = insertar(this.root, AUTOR);
    }    
   
    public String preorden(Nodo a, String x){
        if (a!=null){
            x += a.getAutor() + " ";
            x = preorden(a.getHijo_izq(),x);
            x = preorden(a.getHijo_der(),x);
        }
        return x;
    }
}
