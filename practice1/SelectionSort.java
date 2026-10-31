import java.util.Random;
import java.util.Arrays;
public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {10, 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400};
        for (int i: array) {
            testSelectionSort(i);
        }
    }
    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) min = j;
            }
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }
    }
    static void testSelectionSort(int N) {
        //Random random = new Random(1000);
        int[] arr = new int[N];
        //for (int i = 0; i < N; i++) arr[i] = random.nextInt(100); //рандомный
        //for (int i = 0; i < N; i++) arr[i] = N - i; //обратный
        for (int i = 0; i < N; i++) arr[i] = i; //отсортированный

        long start = System.nanoTime();
        selectionSort(arr);
        //Arrays.sort(arr);
        long diff = System.nanoTime() - start;
        System.out.println(diff);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("Не отсортировано");
                return ;
            }
        }
    }
}
