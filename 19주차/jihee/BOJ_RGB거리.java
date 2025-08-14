import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int answer;
    static int[][] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][4];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][4];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        int answer = recur(0, 3);
        System.out.println(answer);
    }

    private static int recur(int cur, int prv) {
        //마지막 줄까지 왔을때 더이상 드는 비용은 없다
        if (cur == N) {
            return 0;
        }

        if(dp[cur][prv] != -1){
            return dp[cur][prv];
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == prv) {
                continue;
            }
            ret = Math.min(ret, recur(cur + 1, i) + arr[cur][i]);
        }

        dp[cur][prv] = ret;

        return ret;
    }
}
