package Ordenamiento;
import java.util.*;
public class Shell {
    public static <T> void ordenar(List<T> lista, Comparator<T> cmp) {
        for (int gap = lista.size() / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < lista.size(); i++) {
                T temp = lista.get(i);
                int j = i;
                while (j >= gap && cmp.compare(lista.get(j - gap), temp) > 0) {
                    lista.set(j, lista.get(j - gap));
                    j -= gap;
                }
                lista.set(j, temp);
            }
        }
    }
}
