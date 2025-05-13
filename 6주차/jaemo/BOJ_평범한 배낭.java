import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];

        int[][] arr = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }

        int answer = dfs(dp, arr, n, k);
        System.out.println(answer);
    }

    public static int dfs(int[][] dp, int[][] arr, int i, int w) {
        if (i < 0) {
            return 0;
        }

        if (dp[i][w] < 0) {
            if (arr[i][0] > w) {
                dp[i][w] = dfs(dp, arr, i - 1, w);
            } else {
                dp[i][w] = Math.max(dfs(dp, arr, i - 1, w), dfs(dp, arr, i - 1, w - arr[i][0]) + arr[i][1]);
            }
        }

        return dp[i][w];
    }
}
