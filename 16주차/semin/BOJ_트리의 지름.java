import java.io.*;
import java.util.*;

public class Main {
    static final int MXN = 100_010;
    static boolean[] visited = new boolean[MXN];
    static int maxCost = 0;
    static int maxNode = 0;
    static List<List<int[]>> adj = new ArrayList<>();

    public static void dfs(int current, int dist) {
        if (maxCost < dist) {
            maxCost = dist;
            maxNode = current;
        }

        for (int[] next : adj.get(current)) {
            int nextDist = next[0];
            int nextNode = next[1];
            if (visited[nextNode]) continue;
            visited[nextNode] = true;
            dfs(nextNode, dist + nextDist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(u).add(new int[]{c, v});
            adj.get(v).add(new int[]{c, u});
        }

        visited[1] = true;
        dfs(1, 0);

        Arrays.fill(visited, false);
        visited[maxNode] = true;
        dfs(maxNode, 0);

        System.out.println(maxCost);
    }
}
