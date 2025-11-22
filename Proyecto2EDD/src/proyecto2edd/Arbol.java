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
    
    public Arbol() {
        this.root = null;
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
            return 0;
        }
        return n.getAltura();
    }
    
    public int balance(Nodo n){
        
        if (n==null){
            return 0;
        }
        return altura(n.getHijo_der()) - altura(n.getHijo_izq());
    }
    
    public Nodo insertar(Nodo raíz, String autor){
        Collator collator = Collator.getInstance(new Locale("es", "ES"));
        if (this.root==null){
            this.setroot(new Nodo(autor));
        }
        if (esVacio(raíz)){
            return new Nodo(autor);
        }
        else if(collator.compare(autor, raíz.getautor())<0){
            raíz.setHijo_izq(insertar(raíz.getHijo_izq(), autor));
        }
        else if(collator.compare(autor,raíz.getautor())>0){
            raíz.setHijo_der(insertar(raíz.getHijo_der(), autor));
        }
        return raíz;
    }
    
    public void actualizaraltura(Nodo n){
        n.setAltura(máximo(altura(n.getHijo_izq()),altura(n.getHijo_der())));
    }
    
    public Nodo rebalancear(Nodo raíz){
        int balance = balance(raíz);
        if(balance<-1){
            if(balance(raíz.getHijo_izq())<=0){
                raíz = rotarderecha(raíz);
            }
            else{
                raíz.setHijo_izq(rotarizquierda(raíz.getHijo_izq()));
                raíz=rotarderecha(raíz);
            }
        }
        if(balance>1){
            if(balance(raíz.getHijo_der())>=0){
                raíz = rotarizquierda(raíz);
            }
            else{
                raíz.setHijo_der(rotarderecha(raíz.getHijo_der()));
                raíz = rotarizquierda(raíz);
            }
        }
        return raíz;
    }
    
    public Nodo rotarderecha(Nodo n){
        Nodo izq=n.getHijo_izq();
        n.setHijo_izq(izq.getHijo_der());
        izq.setHijo_der(n);
        actualizaraltura(n);
        actualizaraltura(izq);
        return izq;
    }
    
    public Nodo rotarizquierda(Nodo n){
        Nodo der = n.getHijo_der();
        n.setHijo_der(der.getHijo_izq());
        der.setHijo_izq(n);
        actualizaraltura(n);
        actualizaraltura(der);
        return der;
    }
    
    public Nodo InsertarNodo(Nodo raíz, String AUTOR){
        raíz = insertar(raíz, AUTOR);
        actualizaraltura(raíz);
        return rebalancear(raíz);
    }
    
}
