import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal3 {
    private static class Edge {
        int u;
        int v;
        long weight;

        Edge(int u, int v, long weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    private static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int v) {
            if (parent[v] != v) parent[v] = find(parent[v]);
            return parent[v];
        }

        boolean union(int u, int v) {
            u = find(u);
            v = find(v);
            if (u == v) return false;
            if (size[u] < size[v]) {
                int tmp = u;
                u = v;
                v = tmp;
            }
            parent[v] = u;
            size[u] += size[v];
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextLong());
        }

        Arrays.sort(edges, Comparator.comparingLong(e -> e.weight));
        DSU dsu = new DSU(n);
        long cost = 0;
        int selected = 0;

        // алгоритм краскала
        for (Edge edge : edges) {
            if (edge.u != edge.v && dsu.union(edge.u, edge.v)) {
                cost = Math.addExact(cost, edge.weight);
                selected++;
            }
        }

        System.out.println("cost=" + cost + " edges=" + selected);
    }
}
