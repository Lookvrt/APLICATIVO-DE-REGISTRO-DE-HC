package Ordenamiento;
import java.util.*;
public class Seleccion {
    public static <T> void ordenar(List<T> lista, Comparator<T> cmp) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (cmp.compare(lista.get(j), lista.get(min)) < 0) {
                    min = j;
                }
            }
            Collections.swap(lista, i, min);
        }
    }
}
