import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort1 {
    public static void main(String[] args) {
        double[][] tests = {
            {0.25, 0.10, 0.80, 0.45},
            {0.77, 0.07, 0.70}
        };
        double[][] expected = {
            {0.10, 0.25, 0.45, 0.80},
            {0.07, 0.70, 0.77}
        };

        for (int i = 0; i < tests.length; i++) {
            double[] arr = Arrays.copyOf(tests[i], tests[i].length);
            System.out.println("Тест " + (i + 1));
            System.out.println("Вход: " + Arrays.toString(arr));
            sort(arr);
            System.out.println("Результат: " + Arrays.toString(arr));
            System.out.println("Проверка: " + (Arrays.equals(arr, expected[i]) ? "верно" : "ошибка"));
            System.out.println();
        }
    }

    public static void sort(double[] arr) {
        if (arr.length == 0) return;

        int s = arr.length;
        List<List<Double>> buckets = new ArrayList<>(s);

        // создание корзин
        for (int i = 0; i < s; i++) {
            buckets.add(new ArrayList<>());
        }

        // распределение элементов
        for (double val : arr) {
            if (!(val >= 0 && val < 1.0)) {
                throw new IllegalArgumentException("Координата вне диапазона [0, 1)");
            }
            int index = Math.min(s - 1, (int) (val * s));
            buckets.get(index).add(val);
        }

        // сортировка и объединение
        int index = 0;
        for (List<Double> bucket : buckets) {
            insertionSort(bucket);
            for (double val : bucket) {
                arr[index++] = val;
            }
        }
    }

    private static void insertionSort(List<Double> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            double key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
