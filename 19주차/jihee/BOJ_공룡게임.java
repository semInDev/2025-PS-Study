import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 선인장 높이는 1 or 2
 * 최대 2개 인접한 선인장 뛰어넘기 O,
 * 높이 합 4 이상이면 X -> 높이 3까지만 가능
 * 높이 2인 선인장이 적어도 하나는 등장해야됨
 * 바닥인 N+1 지점 도착하면 클리어
 * 1,=000,000,007로 나눈 나머지를 출력
 */
public class Main {

    static int N;
    static long[][][][] dp;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //N,연속한 선인장 갯수,높이 2짜리 선인장 개수, 높이 2 유무
        dp = new long[N + 1][4][3][2];

        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 2; l++) {
                        if (i == N && l == 1) {
                            if (k >= 2 || j > 2) {
                                continue;
                            }
                            dp[i][j][k][l] = 1;
                        }
                    }
                }
            }
        }

        for (int i = N - 1; i >= 1; i--) {
            for (int j = 2; j >= 0; j--) {
                for (int k = 1; k >= 0; k--) {
                    for (int l = 1; l >= 0; l--) {
                        dp[i][j][k][l] = dp[i + 1][0][0][l] % MOD;
                        dp[i][j][k][l] = (dp[i][j][k][l] + dp[i + 1][j + 1][0][l]) % MOD;
                        dp[i][j][k][l] = (dp[i][j][k][l] + dp[i + 1][j + 1][k + 1][1]) % MOD;
                    }
                }
            }
        }
        System.out.println(dp[1][0][0][0]);

    }
}
