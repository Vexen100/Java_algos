import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        distance[s] = 0;
        queue.add(s);

        // обход в ширину
        List<Integer> layers = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int depth = distance[u];
            if (layers.size() == depth) layers.add(0);
            layers.set(depth, layers.get(depth) + 1);

            for (int v : graph.get(u)) {
                if (distance[v] == -1) {
                    distance[v] = depth + 1;
                    queue.add(v);
                }
            }
        }

        StringBuilder output = new StringBuilder();
        output.append("distance=").append(distance[t]).append(" layers=");
        for (int i = 0; i < layers.size(); i++) {
            if (i > 0) output.append(',');
            output.append(layers.get(i));
        }
        System.out.println(output);
    }
}
