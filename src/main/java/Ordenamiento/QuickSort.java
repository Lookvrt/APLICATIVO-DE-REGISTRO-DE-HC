package Ordenamiento;
import java.util.*;
public class QuickSort {
    public static <T> void ordenar(List<T> lista, Comparator<T> cmp) {
        quick(lista, 0, lista.size() - 1, cmp);
    }
    private static <T> void quick(List<T> a, int izq, int der, Comparator<T> cmp) {
        if (izq >= der) {
            return;
        }
        T piv = a.get((izq + der) / 2);
        int i = izq, j = der;
        while (i <= j) {
            while (cmp.compare(a.get(i), piv) < 0) {
                i++;
            }
            while (cmp.compare(a.get(j), piv) > 0) {
                j--;
            }
            if (i <= j) {
                Collections.swap(a, i, j);
                i++;
                j--;
            }
        }
        if (izq < j) {
            quick(a, izq, j, cmp);
        }
        if (i < der) {
            quick(a, i, der, cmp);
        }
    }
}
