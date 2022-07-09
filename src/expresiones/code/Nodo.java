package expresiones.code;

import java.util.HashSet;
import java.util.Set;

public class Nodo {
    Nodo hijo_izquierdo = null;
    Nodo hijo_derecho = null;
    char nombre;
    int posicion;
    Set<Integer> primera_posicion, ultima_posicion;
    boolean anulable = true;
    int n_hijos = 0;
    int indice = 0;

    public Nodo(char nombre){
        this.nombre = nombre;
        this.primera_posicion = new HashSet<>();
        this.ultima_posicion = new HashSet<>();
    }
    
    // Añade una posicion a la primera posicion de un nodo
    public void añadir_primera_posicion(int numero){
        this.primera_posicion.add(numero);
    }
    
    // Añade una posicion a la ultima posicion de un nodo
    public void añadir_ultima_posicion(int numero){
        this.ultima_posicion.add(numero);
    }

    // Asignar el número de hijos de un nodo
    public void asignar_n_hijos(int n_hijos) {
        this.n_hijos += n_hijos;
    }
    
    // Verdadero si el nodo es una hoja
    public boolean es_hoja(){
        return hijo_derecho == null && hijo_izquierdo == null;
    }
}
