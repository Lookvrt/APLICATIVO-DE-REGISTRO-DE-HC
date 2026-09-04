package EstructuraDinamica;
import java.io.Serializable;
import java.util.ArrayList;
public class ListaDoctores implements Serializable {
    private ArrayList<Doctor> lista; 
    public ListaDoctores(){
        lista = new ArrayList();
    }
    //metodo que retorna la cantidad de objetos de la lista
    public int CantidadDoc(){
        return lista.size();
    }
    //agregar
    public void AgregarDoc(Doctor doc){
        lista.add(doc);
    }
    //actualizar
    public void ActualizarDoc(int pos,Doctor nuevodoc){
        lista.set(pos,nuevodoc);
    }
    //eliminar
    public void EliminarDoc(int pos){
        lista.remove(pos);
    }
    //recuperar
    public Doctor RecuperarDoc(int pos){
       return lista.get(pos); 
    }
    //buscar
    public int BuscarDoc(String dnibuscado){
        for(int i=0;i<lista.size();i++){
            if(dnibuscado.equals(lista.get(i).getDni()))
                return i;
        }
        return -1;
    }
    //metodos getter y setter    
    public ArrayList<Doctor> getLista() {
        return lista;
    }
    public void setLista(ArrayList<Doctor> lista) {
        this.lista = lista;
    }
}