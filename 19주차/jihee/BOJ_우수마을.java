import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1][2];
        tree = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, -1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur, int parent) {
        for (int child : tree[cur]) {
            if (child == parent) continue;

            dfs(child, cur);
            dp[cur][1] += dp[child][0];
            dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
        }

        dp[cur][1] += arr[cur];
    }
}
