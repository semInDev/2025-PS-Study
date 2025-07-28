import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer>[] adj;
    static boolean[] vis;

    static void dfs(int cur) {
        if (vis[cur]) return;
        vis[cur] = true;
        for (int nxt : adj[cur]) {
            dfs(nxt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        vis = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        int groupcnt = 0;
        for (int i = 1; i <= n; i++) {
            if (vis[i]) continue;
            dfs(i);
            groupcnt++;
        }

        int answer = (groupcnt - 1) + (m + groupcnt - 1) - (n - 1);
        System.out.println(answer);
    }
}
