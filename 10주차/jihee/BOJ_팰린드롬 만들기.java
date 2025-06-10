import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[5001][5001];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        System.out.println(recur(0, N - 1));
    }

    private static int recur(int s, int e) {
        if (s >= e) {
            return 0;
        }

        if (dp[s][e] != -1) {
            return dp[s][e];
        }

        if (arr[s] == arr[e]) {
            dp[s][e] = recur(s + 1, e - 1);
        } else {
            dp[s][e] = Math.min(recur(s + 1, e) + 1, recur(s, e - 1) + 1);
        }

        return dp[s][e];
    }
}
