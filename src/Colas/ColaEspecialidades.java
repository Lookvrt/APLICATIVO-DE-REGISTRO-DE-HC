package Colas;
import java.io.Serializable;
import java.util.PriorityQueue;
public class ColaEspecialidades implements Serializable {
    PriorityQueue<Especialidad> cola;

    public ColaEspecialidades() {
        cola=new PriorityQueue(10);
    }
    
    public void Encolar(Especialidad sol){ cola.add(sol);}
    public void Desencolar() { cola.poll();  }
    public Especialidad VerPrimero() { return cola.peek(); }
    public Especialidad BuscarPorCod(String codbusc){
        for(Especialidad esp: cola){
            if(esp.getCodEspecialidad().equalsIgnoreCase(codbusc)) 
            return esp;
        }
        return null;
    }
    //getter y setter
    public PriorityQueue<Especialidad> getCola() { return cola; }
    public void setCola(PriorityQueue<Especialidad> cola) { this.cola = cola; }
}