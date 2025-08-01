import java.io.*;
import java.util.*;

public class Main {
    static final int MXN = 1_000_010;
    static int N, u, v;
    static List<Integer>[] adj = new ArrayList[MXN];
    static List<Integer>[] tree = new ArrayList[MXN];
    static int[][] state = new int[MXN][2];

    static void mktree(int curr, int prev) {
        for (int nxt : adj[curr]) {
            if (nxt == prev) continue;
            tree[curr].add(nxt);
            mktree(nxt, curr);
        }
    }

    static int dp(int curr, int isEarlyAdaptor) {
        if (state[curr][isEarlyAdaptor] != -1) return state[curr][isEarlyAdaptor];

        int val = 0;
        if (isEarlyAdaptor == 1) {
            for (int nxt : tree[curr])
                val += Math.min(dp(nxt, 0), dp(nxt, 1));
        } else {
            for (int nxt : tree[curr])
                val += dp(nxt, 1);
        }

        return state[curr][isEarlyAdaptor] = val + isEarlyAdaptor;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
            state[i][0] = -1;
            state[i][1] = -1;
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        mktree(1, -1);
        System.out.println(Math.min(dp(1, 0), dp(1, 1)));
    }
}
