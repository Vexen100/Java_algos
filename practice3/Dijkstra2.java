import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra2 {
    private static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class State {
        int vertex;
        long distance;

        State(int vertex, long distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long w = scanner.nextLong();
            if (w < 0) throw new IllegalArgumentException("отрицательный вес");
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        long[] distance = new long[n];
        boolean[] reachable = new boolean[n];
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a.distance));
        reachable[s] = true;
        queue.add(new State(s, 0));

        // алгоритм дейкстры
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.distance != distance[current.vertex]) continue;

            for (Edge edge : graph.get(current.vertex)) {
                long next = Math.addExact(current.distance, edge.weight);
                if (!reachable[edge.to] || next < distance[edge.to]) {
                    distance[edge.to] = next;
                    reachable[edge.to] = true;
                    queue.add(new State(edge.to, next));
                }
            }
        }

        int unreachable = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) output.append(' ');
            if (reachable[i]) {
                output.append(distance[i]);
            } else {
                output.append(-1);
                unreachable++;
            }
        }
        output.append(" unreachable=").append(unreachable);
        System.out.println(output);
    }
}
