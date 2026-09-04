package Arboles;

import java.io.Serializable;
import javax.swing.table.DefaultTableModel;

public class ArbolHistoria implements Serializable {

    private NodoHistoria Raiz;

    public ArbolHistoria() {
        this.Raiz = null;
    }

    public NodoHistoria getRaiz() {
        return Raiz;
    }

    public void setRaiz(NodoHistoria Raiz) {
        this.Raiz = Raiz;
    }

    // método que agrega un cliente al árbol usando DNI
    public NodoHistoria AgregarCliente(NodoHistoria Nodo, HistoriaClinica elemento) {
        if (Nodo == null) {
            return new NodoHistoria(elemento);
        } else {
            if (elemento.getDni().compareTo(Nodo.getElemento().getDni()) > 0) {
                Nodo.setDer(AgregarCliente(Nodo.getDer(), elemento));
            } else {
                Nodo.setIzq(AgregarCliente(Nodo.getIzq(), elemento));
            }
        }
        return Nodo;
    }

    //metodo que recorre en arbol en Orden -- Izq/Raiz/Der
    public void MostrarEnOrden(NodoHistoria Nodo, DefaultTableModel modelo) {
        if (Nodo != null) {
            MostrarEnOrden(Nodo.getIzq(), modelo);
            modelo.addRow(Nodo.getElemento().getRegistro());
            MostrarEnOrden(Nodo.getDer(), modelo);
        }
    }
    
    //Metodo que busca por dni
    public NodoHistoria BuscarPorDni(String dni) {
        NodoHistoria aux = Raiz;
        while (aux != null) {
            int comparacion = dni.compareTo(aux.getElemento().getDni());
            if (comparacion == 0) {
                return aux; 
            } else if (comparacion > 0) {
                aux = aux.getDer(); 
            } else {
                aux = aux.getIzq(); 
            }
        }
        return null; 
    }

    //metodo que busca el mayor elemento del lado izquierda
    public NodoHistoria BuscarMayorIzquierda(NodoHistoria auxiliar) {
        if (auxiliar != null) {
            while (auxiliar.getDer() != null) {
                auxiliar = auxiliar.getDer();
            }
        }
        return auxiliar;
    }
    
    //metodo que elimina el mayor cliente del lado izquierdo
    public NodoHistoria EliminarMayorIzquierda(NodoHistoria auxiliar) {
        if (auxiliar == null) {
            return null;
        } else if (auxiliar.getDer() != null) {
            auxiliar.setDer(EliminarMayorIzquierda(auxiliar.getDer()));
            return auxiliar;
        }
        return auxiliar.getIzq();
    }//fin metodo

    //metodo que elimina el nodo del arbol por DNI
    public NodoHistoria Eliminar(NodoHistoria auxiliar, String dato) {
        if (auxiliar == null) {
            return null;
        }
        if (dato.compareTo(auxiliar.getElemento().getDni()) < 0) {
            auxiliar.setIzq(Eliminar(auxiliar.getIzq(), dato));
        } else if (dato.compareTo(auxiliar.getElemento().getDni()) > 0) {
            auxiliar.setDer(Eliminar(auxiliar.getDer(), dato));
        } else if (auxiliar.getIzq() != null && auxiliar.getDer() != null) {
            auxiliar.setElemento(BuscarMayorIzquierda(auxiliar.getIzq()).getElemento());
            auxiliar.setIzq(EliminarMayorIzquierda(auxiliar.getIzq()));
        } else {
            auxiliar = (auxiliar.getIzq() != null) ? auxiliar.getIzq() : auxiliar.getDer();
        }
        return auxiliar;
    }
    
    public void MostrarAtencionesDelDia(NodoHistoria nodo,
        javax.swing.table.DefaultTableModel modelo,
        String doctor,
        String fecha) {

    if (nodo != null) {

        MostrarAtencionesDelDia(nodo.getIzq(), modelo, doctor, fecha);

        HistoriaClinica hc = nodo.getElemento();

        if (hc.getDoctor().equals(doctor)
                && hc.getFecha().equals(fecha)) {

            modelo.addRow(new Object[]{
                hc.getNumeroHistoria(),
                hc.getDni(),
                hc.getPaciente(),
                hc.getEspecialidad(),
                hc.getDiagnostico()
            });
        }

        MostrarAtencionesDelDia(nodo.getDer(), modelo, doctor, fecha);
    }
    }
    
    public void MostrarAtencionesPorRango(NodoHistoria nodo,
        javax.swing.table.DefaultTableModel modelo,
        String especialidad,
        String fechaDesde,
        String fechaHasta) {

    if (nodo != null) {
        MostrarAtencionesPorRango(nodo.getIzq(), modelo, especialidad, fechaDesde, fechaHasta);

        HistoriaClinica hc = nodo.getElemento();
        boolean enRangoFecha = hc.getFecha().compareTo(fechaDesde) >= 0 
                            && hc.getFecha().compareTo(fechaHasta) <= 0;

        if (hc.getEspecialidad().equals(especialidad) && enRangoFecha) {
            modelo.addRow(new Object[]{
                hc.getNumeroHistoria(),
                hc.getDni(),
                hc.getPaciente(),
                hc.getDoctor(),
                hc.getFecha(),
                hc.getDiagnostico()
            });
        }
        MostrarAtencionesPorRango(nodo.getDer(), modelo, especialidad, fechaDesde, fechaHasta);
    }
}
}
