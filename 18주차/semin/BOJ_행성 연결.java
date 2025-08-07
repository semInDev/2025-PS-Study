import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parent = new int[n];
        Arrays.fill(parent, -1);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i != j) {
                    edges.add(new Edge(i, j, cost));
                }
            }
        }

        Collections.sort(edges);

        long totalCost = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (merge(edge.u, edge.v)) {
                totalCost += edge.cost;
                count++;
                if (count == n - 1) break;
            }
        }

        System.out.println(totalCost);
    }

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        if (parent[u] == parent[v]) parent[u]--;
        if (parent[u] < parent[v]) parent[v] = u;
        else parent[u] = v;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
