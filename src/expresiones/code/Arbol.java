package expresiones.code;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Arbol {
    String expresion;
    Nodo raiz;
    Set<Integer> siguiente_posicion[];
    Hashtable<Character, Set<Integer>> pos = null;
    int n_caracteres = 0;
    int indice = 0;
   
    public Arbol(String expresion){
        this.expresion = "(" + expresion + ")#";
        this.raiz = new Nodo('.');
        this.pos = new Hashtable<>();
        this.indice = 0;
        this.iniciar_posicion();
    }
    
    // Inicializacion de variables con respecto a la expresion
    private void iniciar_posicion(){
        for(int i = 0; i < expresion.length(); i++){
            if(es_caracter(expresion.charAt(i)) && expresion.charAt(i) != '&'){
                if(pos.get(expresion.charAt(i)) == null) pos.put(expresion.charAt(i), new HashSet<>());
                pos.get(expresion.charAt(i)).add(++n_caracteres);
            }
        }
        this.siguiente_posicion = new Set[n_caracteres + 1];
        for(int i = 0; i <= n_caracteres; i++) siguiente_posicion[i] = new HashSet<>();
    }
    
    // Verdadero si el caracter es un simbolo valido
    private boolean es_operador(char simbolo){
        return simbolo == '|' || simbolo == '.' || simbolo == '*' || simbolo == '(' || simbolo == ')' || simbolo == '?' || simbolo == '+';
    }
    
    // Verdadero si el caracter entrado es una letra o un digito
    private boolean es_caracter(char simbolo){
        return Character.isLetterOrDigit(simbolo) || simbolo == '&' || simbolo == '#';
    }
    
    // Verificar si es posible concatenar dos caracteres de la expresion
    private boolean puede_concatenar(char actual, char siguiente){
        return (es_caracter(actual) && es_caracter(siguiente)) || (actual == ')' && siguiente == '(') 
                || ((actual == '*' || actual == ')') && es_caracter(siguiente)) || (es_caracter(actual) && siguiente == '(')
                || (actual == '?' && (es_caracter(siguiente) || siguiente == '(') || (actual == '+' && (es_caracter(siguiente) || siguiente == '(')))
                || (actual == '*' && siguiente == '(');
    }
    
    // Normalizar la expresion regular entrada
    private void normalizar_expresion(){
        for(int i = 0; i < expresion.length()-1; i++){
            char actual = expresion.charAt(i);
            char siguiente = expresion.charAt(i + 1);
            String prefijo = expresion.substring(0, i + 1);
            String sufijo = expresion.substring(i + 1);
            if(puede_concatenar(actual, siguiente)) expresion = prefijo + "." + sufijo;
        }
    }
    
    // Simbolos de una expresion sin contar los parentesis
    private int simb(char simbolo){
        switch(simbolo){
            case '|':
                return 1;
            case '.':
                return 2;
            case '*':
            case '+':
            case '?':
                return 3;
        }
        return 0;
    }
   
    // Transformar la expresion de prefijo a posfijo
    private String transformar(){
        Stack<Character> pila = new Stack<>();
        String posfijo = "";
        for(int i = 0; i < expresion.length(); i++){
            char simbolo = expresion.charAt(i);
            if(es_caracter(simbolo)) posfijo+=simbolo;
            else if(simbolo == '(') pila.push(simbolo);
            else if(simbolo == ')'){
                while(!pila.empty() && pila.peek() != '(') posfijo+=pila.pop();
                if(!pila.empty() && pila.peek() != '(') JOptionPane.showMessageDialog(null, "Expresion invalida", "Error", JOptionPane.WARNING_MESSAGE);
                else pila.pop();
            }else{
                while(!pila.empty() && simb(simbolo) <= simb(pila.peek())){
                    if(pila.peek() == '(') JOptionPane.showMessageDialog(null, "Expresion invalida", "Error", JOptionPane.WARNING_MESSAGE);
                    posfijo+=pila.pop();
                }
                pila.push(simbolo);
            }
        }
        while(!pila.empty()){
            if(pila.peek() == '(') JOptionPane.showMessageDialog(null, "Expresion invalida", "Error", JOptionPane.WARNING_MESSAGE);
            posfijo+=pila.pop();
        }
        return posfijo;
    }
    
    // Creación del arbol a partir de la expresion en posfijo
    private void arbol_de_posfijo(String posfijo){
        Stack<Nodo> pila = new Stack<>();
        Nodo actual, hijo_derecho, hijo_izquierdo;
        int cnt = 1;
        for(int i = 0; i < posfijo.length(); i++){
            char simbolo = posfijo.charAt(i);
            if(!es_operador(simbolo)){
                actual = new Nodo(simbolo);
                if(simbolo != '&') actual.posicion = cnt++;
                pila.push(actual);
            }else{
                actual = new Nodo(simbolo);
                if(simbolo == '*' || simbolo == '+' || simbolo == '?'){
                    hijo_izquierdo = pila.pop();
                    actual.hijo_izquierdo = hijo_izquierdo;
                }else{
                    hijo_derecho = pila.pop();
                    hijo_izquierdo = pila.pop();
                    actual.hijo_derecho = hijo_derecho;
                    actual.hijo_izquierdo = hijo_izquierdo;
                }
                pila.push(actual);
            }
        }
        raiz = pila.pop();
    }
   
    // Creación del arbol a partir de la expresion regular
    public void crear_arbol(){
        normalizar_expresion();
        String posfijo = transformar();
        arbol_de_posfijo(posfijo);
    }
    
    // Unión de estados equivalentes
    private void union_posicion(Nodo actual, boolean es_primera_posicion){
        if(es_primera_posicion){
            Nodo hijo_izquierdo = actual.hijo_izquierdo;
            Nodo hijo_derecho = actual.hijo_derecho;
            if(hijo_izquierdo != null){
                for(int posicion : hijo_izquierdo.primera_posicion) actual.añadir_primera_posicion(posicion);
            }
            if(hijo_derecho != null){
                for(int posicion : hijo_derecho.primera_posicion) actual.añadir_primera_posicion(posicion);
            }
        }else{
            Nodo hijo_izquierdo = actual.hijo_izquierdo;
            Nodo hijo_derecho = actual.hijo_derecho;
            if(hijo_izquierdo != null){
                for(int posicion : hijo_izquierdo.ultima_posicion) actual.añadir_ultima_posicion(posicion);
            }
            if(hijo_derecho != null){
                for(int posicion : hijo_derecho.ultima_posicion) actual.añadir_ultima_posicion(posicion);
            }
        }
    }
    
    // Asignación de primera y ultima posicion de un nodo
    private void primera_ultima(Nodo actual){
        if(es_caracter(actual.nombre) && actual.nombre != '&'){
            actual.anulable = (false);
            actual.añadir_primera_posicion(actual.posicion);
            actual.añadir_ultima_posicion(actual.posicion);
        } else if(es_operador(actual.nombre)){
            boolean anulable1, anulable2;
            switch(actual.nombre){
                case '|':
                    anulable1 = actual.hijo_izquierdo.anulable;
                    anulable2 = actual.hijo_derecho.anulable;
                    actual.anulable = (anulable1 | anulable2);
                    union_posicion(actual, true);
                    union_posicion(actual, false);
                    break;
                case '.':
                    anulable1 = actual.hijo_izquierdo.anulable;
                    anulable2 = actual.hijo_derecho.anulable;
                    actual.anulable = (anulable1 & anulable2);
                    if(anulable1) union_posicion(actual, true);
                    else actual.primera_posicion = (actual.hijo_izquierdo.primera_posicion);
                    if(anulable2) union_posicion(actual, false);
                    else actual.ultima_posicion = (actual.hijo_derecho.ultima_posicion);
                    break;
                case '*':
                case '?':
                case '+':
                    actual.primera_posicion = (actual.hijo_izquierdo.primera_posicion);
                    actual.ultima_posicion = (actual.hijo_izquierdo.ultima_posicion);
                    if(actual.nombre == '+') actual.anulable = (false);
                    break;
            }
        }
    }
    
    // Retorna la posicion siguiente de un nodo
    private void siguiente_post(Nodo actual){
        Set<Integer> uposC1 = actual.hijo_izquierdo.ultima_posicion;
        switch(actual.nombre){
            case '.':
                Set<Integer> pposC2 = actual.hijo_derecho.primera_posicion;
                for(Integer i : uposC1) siguiente_posicion[i].addAll(pposC2);
                break;
            case '*':
            case '+':
                Set<Integer> pposC1 = actual.hijo_izquierdo.primera_posicion;
                for(Integer i : uposC1) siguiente_posicion[i].addAll(pposC1);
                break;
        }
    }
    
    // Calcular las posiciones de los nodos en el arbol - Private
    private void calculo_posiciones(Nodo actual){
        if(actual == null) return;
        Nodo hijo_izquierdo = actual.hijo_izquierdo;
        Nodo hijo_derecho = actual.hijo_derecho;
        calculo_posiciones(hijo_izquierdo);
        calculo_posiciones(hijo_derecho);
        actual.indice = ++indice;
        primera_ultima(actual);
        if(es_operador(actual.nombre)) siguiente_post(actual);
        if(actual.es_hoja()) actual.asignar_n_hijos(1);
        else{
            if(hijo_izquierdo != null) actual.asignar_n_hijos(hijo_izquierdo.n_hijos);
            if(hijo_derecho != null) actual.asignar_n_hijos(hijo_derecho.n_hijos);
        }
    }
    
    // Calcular las posiciones de los nodos en el arbol - Public
    public void calculo_posiciones(){
        this.calculo_posiciones(this.raiz);
    }
}