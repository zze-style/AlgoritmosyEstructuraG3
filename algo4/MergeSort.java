package algo4;

public class MergeSort {

    public static void mergeSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            mergeSort(arr, inicio, medio);
            mergeSort(arr, medio + 1, fin);
            merge(arr, inicio, medio, fin);
        }
    }

    public static void merge(int[] arr, int inicio, int medio, int fin) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[inicio + i];
        for (int j = 0; j < n2; j++) R[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = inicio;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { arr[k] = L[i]; i++; }
            else               { arr[k] = R[j]; j++; }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    public static void main(String[] args) {
        int[] arr5  = {64, 34, 25, 12, 22};
        int[] arr8  = {38, 27, 43, 3, 9, 82, 10, 1};
        int[] arr10 = {70, 15, 23, 7, 42, 88, 5, 30, 61, 19};

        System.out.print("5 elementos antes: ");
        for (int x : arr5) System.out.print(x + " ");
        mergeSort(arr5, 0, arr5.length - 1);
        System.out.print("\n5 elementos despues: ");
        for (int x : arr5) System.out.print(x + " ");

        System.out.print("\n8 elementos antes: ");
        for (int x : arr8) System.out.print(x + " ");
        mergeSort(arr8, 0, arr8.length - 1);
        System.out.print("\n8 elementos despues: ");
        for (int x : arr8) System.out.print(x + " ");

        System.out.print("\n10 elementos antes: ");
        for (int x : arr10) System.out.print(x + " ");
        mergeSort(arr10, 0, arr10.length - 1);
        System.out.print("\n10 elementos despues: ");
        for (int x : arr10) System.out.print(x + " ");
    }
}