package Ordenamiento;
import java.util.*;
public class Burbuja {
    public static <T> void ordenar(List<T> lista, Comparator<T> cmp) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                if (cmp.compare(lista.get(j), lista.get(j + 1)) > 0) {
                    Collections.swap(lista, j, j + 1);
                }
            }
        }
    }
}
