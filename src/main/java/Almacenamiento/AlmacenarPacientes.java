package Almacenamiento;

import java.io.*;
import ListasDoblesEnlazadas.ListaPacientes;
import Procesos.Mensajes;

public class AlmacenarPacientes {
    
    public static String archivo = "ListaPacientes.bin";

    // Guardar en archivo binario
    public static void GuardarLista(ListaPacientes lp) {
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lp);
            oos.close();
        } catch (Exception ex) {
            Mensajes.M1("ERROR no se puede guardar Paciente");
        }
    }

    // Recuperar del archivo binario
    public static ListaPacientes RecuperarLista() {
        ListaPacientes lista = new ListaPacientes();
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaPacientes) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            Mensajes.M1("ERROR no se puede recuperar Pacientes");
        }
        return lista;
    }
}