import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int coin = coins[i];
            dp[i][0] = 1;
            if (coin > k) {
                for (int j = 1; j <= k; j++) {
                    dp[i][j] = dp[i - 1][j];
                }
            } else {
                for (int j = 1; j < coin; j++) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            for (int j = coin; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
            }
        }

        System.out.println(dp[n][k]);
    }
}
