package Almacenamiento;
import Arboles.*;
import Procesos.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AlmacenarHistoriasClinicas {
    public static String archivo="ListaHistoriaClinica.bin";
    //guardando en un archivo binario
    public static void GuardarLista(ArbolHistoria le){
        try{
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos =  new ObjectOutputStream(fos);
            oos.writeObject(le);
            oos.close();            
        }catch(Exception ex){
            Mensajes.M1("ERROR no se puede guardar Historias Clinicas");
        }
    }
   //recuperar los datos del archivo binario
    public static ArbolHistoria RecuperarLista(){
        ArbolHistoria lista=new ArbolHistoria();
        try{
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArbolHistoria)ois.readObject();
            ois.close();            
        }catch(Exception ex){
          Mensajes.M1("ERROR no se puede recuperar Historia Clinica");
         }
        return lista;
    }    
}
