package expresiones.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Automata {
    public ArrayList<Estado> estados = null;
    public Set<Character> alfabeto = null;
    public Hashtable<Character,Integer> transiciones[] = null;
    public Set<Integer> raiz = null, siguiente_posicion[] = null;
    public Hashtable<Character, Set<Integer>> pos = null;
    public int n_caracteres = 0;
    public int n_estados = 0;
    
    public Automata(Arbol arbol, String expresion){
        this.pos = arbol.pos;
        this.raiz = arbol.raiz.primera_posicion;
        this.siguiente_posicion = arbol.siguiente_posicion;
        this.n_caracteres = arbol.n_caracteres;
        this.transiciones = new Hashtable[this.n_caracteres + 1];
        for(int i = 0; i <= this.n_caracteres; i++) transiciones[i] = new Hashtable<>();
        getAlfabeto(expresion);
    }
    
    // Retorna el conjunto de posiciones de un estado
    public Set<Integer> obtener_conjunto(int nombre){
        return estados.get(nombre - 1).conjunto_posicion;
    }
    
    // Verdadero si el estado ingresado es estado final
    public boolean es_estado_final(int nombre){
        return this.estados.get(nombre - 1).estado_final;
    }
    
    // Verdadero si el simbolo es una letra, digito o '#'
    private boolean es_caracter(char simbolo){
        return Character.isLetterOrDigit(simbolo) || simbolo == '#';
    }
    
    // Obtiene el alfabeto de una expresión regular
    public void getAlfabeto(String expresion){
        this.alfabeto = new HashSet<>();
        for(int i = 0; i < expresion.length(); i++) if(es_caracter(expresion.charAt(i))) this.alfabeto.add(expresion.charAt(i));
    }
    
    // Creacion de un automata a partir de un arbol
    public void crear_automata(){
        estados = new ArrayList<>();
        Estado inicial = new Estado(++n_estados);
        inicial.conjunto_posicion = (raiz);
        estados.add(inicial);
        while(true){
            Estado actual = null;
            boolean finalizado = true;
            for(Estado estado : estados){
                if(!estado.marcado){
                    actual = estado;
                    finalizado = false;
                }
            }
            if(finalizado) break;
            actual.marcado = (true);
            actual.asignar_estado_final(n_caracteres);
            for(Character simbolo : alfabeto){
                Set<Integer> u = new HashSet<>();
                for(Integer posicion : actual.conjunto_posicion){
                    if(pos.get(simbolo).contains(posicion)){
                        u.addAll(siguiente_posicion[posicion]);
                    }
                }
                if(!u.isEmpty()){
                    boolean existe = false;
                    Estado estado_vist = null;
                    for(Estado estado : estados){
                        if(u.equals(estado.conjunto_posicion)){
                            existe = true;
                            estado_vist = estado;
                            break;
                        }
                    }
                    if(!existe){
                        Estado estadoU = new Estado(++n_estados
                );
                        estadoU.conjunto_posicion = (u);
                        transiciones[actual.nombre].put(simbolo, estadoU.nombre);
                        estados.add(estadoU);
                    } else transiciones[actual.nombre].put(simbolo, estado_vist.nombre);
                }
            }
        }
    }
    
    // Retorna falso si la cadena ingresada no es aceptada por el automata
    private boolean DFS(String cadena, int i, int estado){
        if(i == cadena.length() && estados.get(estado - 1).estado_final) return true;
        else if(i < cadena.length()){
            char simbolo = cadena.charAt(i);
            if(!transiciones[estado].isEmpty() && transiciones[estado].get(simbolo) != null){
                return DFS(cadena, i + 1, transiciones[estado].get(simbolo));
            }
        }
        return false;
    }
    
    // Verificación de una cadena ingresada por el usuario
    public boolean verificar_cadena(String cadena){
        cadena = cadena.replace("&", "");
        return DFS(cadena, 0, 1);
    }
}