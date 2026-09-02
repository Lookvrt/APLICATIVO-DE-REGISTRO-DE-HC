package ListasDoblesEnlazadas;
import java.io.Serializable;
public class Nodo implements Serializable {
    public Paciente pas;
    public Nodo sig;
    public Nodo ant;
    public Nodo(Paciente p) {
        this.pas = p;
        ant = sig = null; //los dos punteros tiene que apuntar a null en un inicio
    }
}
