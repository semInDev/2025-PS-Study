import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] id = new int[1002];
    static ArrayList<Integer>[] adj = new ArrayList[1002];
    static ArrayList<Integer> sq = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            if (no == 0) continue;
            int u = Integer.parseInt(st.nextToken());
            for (int j = 1; j < no; j++) {
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                id[v]++;
                int temp = u;
                u = v;
                v = temp;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (id[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sq.add(cur);
            for (int nxt : adj[cur]) {
                if (--id[nxt] == 0) {
                    q.add(nxt);
                }
            }
        }

        if (sq.size() != n) {
            System.out.println(0);
        } else {
            for (int s : sq) {
                System.out.println(s);
            }
        }
    }
}
