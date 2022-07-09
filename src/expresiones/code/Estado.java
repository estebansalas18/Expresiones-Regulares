package expresiones.code;

import java.util.HashSet;
import java.util.Set;

public class Estado {
    int nombre;
    Set<Integer> conjunto_posicion = null;
    boolean marcado = false;
    boolean estado_final = false;
    
    public Estado(int nombre){
        this.nombre = nombre;
        this.conjunto_posicion = new HashSet<>();
    }

    // Añade posibles posiciones a un estado
    public void añadir_conjunto(int posicion){
        this.conjunto_posicion.add(posicion);
    }

    // Asigna si el estado es final
    public void asignar_estado_final(int posNumeral) {
        if(conjunto_posicion.contains(posNumeral)) this.estado_final = true;
    }
}
