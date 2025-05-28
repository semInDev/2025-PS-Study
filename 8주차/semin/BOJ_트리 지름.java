import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int n;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static int maxDist = 0, farNode = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Edge(to, weight));
            }
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int now, int dist) {
        visited[now] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farNode = now;
        }

        for (Edge edge : graph.get(now)) {
            if (!visited[edge.to]) {
                dfs(edge.to, dist + edge.weight);
            }
        }
    }
}
