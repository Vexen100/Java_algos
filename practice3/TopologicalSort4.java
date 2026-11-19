import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSort4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int[] order = new int[n];
        int count = 0;

        // алгоритм кана
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[count++] = u;
            for (int v : graph.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) queue.add(v);
            }
        }

        if (count != n) {
            System.out.println("CYCLE");
        } else {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (i > 0) output.append(' ');
                output.append(order[i]);
            }
            System.out.println(output);
        }
    }
}
