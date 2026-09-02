package Ordenamiento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ordenador {
    private static int comparar(Object[] a, Object[] b, int columna) {
        String x = a[columna] == null ? "" : a[columna].toString();
        String y = b[columna] == null ? "" : b[columna].toString();
        try {
            double dx = Double.parseDouble(x.replaceAll("[^0-9.-]", ""));
            double dy = Double.parseDouble(y.replaceAll("[^0-9.-]", ""));
            return Double.compare(dx, dy);
        } catch (Exception ex) {
            return x.compareToIgnoreCase(y);
        }
    }

    public static void ordenarTabla(JTable tabla, int columna, String metodo) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        List<Object[]> filas = new ArrayList<>();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int j = 0; j < modelo.getColumnCount(); j++) fila[j] = modelo.getValueAt(i, j);
            filas.add(fila);
        }
        if (filas.size() <= 1) return;
        Comparator<Object[]> cmp = (a,b) -> comparar(a,b,columna);
        String m = metodo == null ? "" : metodo.toLowerCase();
        if (m.contains("sele")) Seleccion.ordenar(filas, cmp);
        else if (m.contains("inser")) Insercion.ordenar(filas, cmp);
        else if (m.contains("shell")) Shell.ordenar(filas, cmp);
        else if (m.contains("quick")) QuickSort.ordenar(filas, cmp);
        else Burbuja.ordenar(filas, cmp);
        modelo.setRowCount(0);
        for (Object[] fila : filas) modelo.addRow(fila);
    }
}
