import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static List<int[]>[] graph;
    static boolean[] visited;
    static int maxDistance = 0;
    static int K = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());

                graph[from].add(new int[]{to, weight});
            }
        }

        visited = new boolean[V + 1];
        DFS(1, 0);

        visited = new boolean[V + 1];
        DFS(K, 0);

        System.out.println(maxDistance);
    }

    static void DFS(int node, int dist) {
        if (dist > maxDistance) {
            maxDistance = dist;
            K = node;
        }

        for (int[] next : graph[node]) {
            int to = next[0];
            int weight = next[1];
            if (!visited[to]) {
                DFS(to, dist + weight);
            }
        }
    }
}
