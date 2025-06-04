import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] tree;
    static boolean[] visited;
    static double count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[v].add(u);
            tree[u].add(v);
        }

        DFS(1);

        System.out.printf("%.10f\n", (double)w / count);

    }

    private static void DFS(int node) {
        visited[node] = true;
        int child = 0;

        for (int next : tree[node]) {
            if (!visited[next]) {
                child++;
                DFS(next);
            }
        }

        if (child == 0) count++;
    }
}
