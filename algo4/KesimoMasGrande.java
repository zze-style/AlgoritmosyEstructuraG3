package algo4;
import java.util.Random;

public class KesimoMasGrande {

    // Particiona el arreglo en torno al pivote y retorna su posicion final
    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] >= pivot) {   // >= para orden descendente
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1]; arr[i + 1] = arr[right]; arr[right] = tmp;
        return i + 1;
    }

    // QuickSelect: O(n) promedio, O(n^2) peor caso
    static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);
        int rank = pivotIndex - left + 1; // rango del pivote (posicion 1-based)

        if (rank == k)      return arr[pivotIndex];
        else if (k < rank)  return quickSelect(arr, left, pivotIndex - 1, k);
        else                return quickSelect(arr, pivotIndex + 1, right, k - rank);
    }

    public static int kesimoMasGrande(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k);
    }

    public static void main(String[] args) {
        System.out.println(kesimoMasGrande(new int[]{4, 2, 7, 10, 4, 17}, 3));   // 7
        System.out.println(kesimoMasGrande(new int[]{4, 2, 7, 10, 4, 1, 6}, 5)); // 4
        System.out.println(kesimoMasGrande(new int[]{4, 2, 7, 1, 4, 6}, 1));     // 7
        System.out.println(kesimoMasGrande(new int[]{9, 2, 7, 1, 7}, 4));         // 2
    }
}