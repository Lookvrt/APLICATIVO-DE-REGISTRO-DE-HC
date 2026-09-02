package Ordenamiento;
import java.util.*;
public class Insercion {
    public static <T> void ordenar(List<T> lista, Comparator<T> cmp) {
        for (int i = 1; i < lista.size(); i++) {
            T aux = lista.get(i);
            int j = i - 1;
            while (j >= 0 && cmp.compare(lista.get(j), aux) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, aux);
        }
    }
}
