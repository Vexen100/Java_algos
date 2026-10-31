import java.util.Arrays;

public class CountingSort3 {
    public static void main(String[] args) {
        int[][] tests = {
            {255, 0, 128, 128},
            {7, 3, 3, 1}
        };

        int[][] expected = {
            {0, 128, 128, 255},
            {1, 3, 3, 7}
        };

        for (int i = 0; i < tests.length; i++) {
            int[] arr = Arrays.copyOf(tests[i], tests[i].length);
            System.out.println("тест " + (i + 1));
            System.out.println("до: " + Arrays.toString(arr));
            sort(arr);
            System.out.println("после: " + Arrays.toString(arr));
            System.out.println("проверка: " +
                (Arrays.equals(arr, expected[i]) ? "верно" : "ошибка"));
            System.out.println();
        }
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int[] count = new int[256];

        // подсчет значений
        for (int val : arr) {
            if (val < 0 || val > 255) {
                throw new IllegalArgumentException("яркость вне диапазона 0..255");
            }
            count[val]++;
        }

        // восстановление массива
        int index = 0;
        for (int val = 0; val < count.length; val++) {
            for (int repeat = 0; repeat < count[val]; repeat++) {
                arr[index++] = val;
            }
        }
    }
}
