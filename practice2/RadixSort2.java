import java.util.Arrays;

public class RadixSort2 {
    public static void main(String[] args) {
        int[][] tests = {
            {500, 12, 99999999, 42},
            {101, 10, 1}
        };

        int[][] expected = {
            {12, 42, 500, 99999999},
            {1, 10, 101}
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

        int max = arr[0];
        for (int val : arr) {
            if (val < 0 || val > 99999999) {
                throw new IllegalArgumentException("номер билета вне диапазона");
            }
            max = Math.max(val, max);
        }

        // сортировка по разрядам
        for (int place = 1; max / place > 0; place *= 10) {
            countingSortByDigit(arr, place);
        }
    }

    private static void countingSortByDigit(int[] arr, int place) {
        int[] count = new int[10];

        // подсчет цифр
        for (int val : arr) {
            count[(val / place) % 10]++;
        }

        for (int digit = 1; digit < 10; digit++) {
            count[digit] += count[digit - 1];
        }

        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            result[--count[digit]] = arr[i];
        }

        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
