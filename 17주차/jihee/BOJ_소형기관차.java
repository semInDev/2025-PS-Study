import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] train, sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        train = new int[N + 1];
        sum = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + train[i];
        }
        M = Integer.parseInt(br.readLine());
        dp = new int[4][N + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * M; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + (sum[j] - sum[j - M]));
            }
        }

        System.out.println(dp[3][N]);
    }
}
