package Arboles;
import java.io.Serializable;
public class NodoHistoria implements Serializable{
   private HistoriaClinica elemento;
   private NodoHistoria Izq;
   private NodoHistoria Der;
   public NodoHistoria(HistoriaClinica elem){
       this.elemento=elem;
       Izq=Der=null;
   }
   //getter y setter
    public HistoriaClinica getElemento()              {  return elemento;    }
    public void setElemento(HistoriaClinica elemento) {  this.elemento = elemento;    }
    public NodoHistoria getIzq()               {  return Izq;    }
    public void setIzq(NodoHistoria Izq)       {  this.Izq = Izq;    }
    public NodoHistoria getDer()               {  return Der;    }
    public void setDer(NodoHistoria Der)       {  this.Der = Der;    }
}
