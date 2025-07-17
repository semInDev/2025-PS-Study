import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C;
    static char[] X, Y;
    static int[][] dp;
    static int xLen, yLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        X = br.readLine().toCharArray();
        Y = br.readLine().toCharArray();

        xLen = X.length;
        yLen = Y.length;

        dp = new int[xLen + 1][yLen + 1];

        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        System.out.println(recur(0, 0));
    }

    static int recur(int x, int y) {
        if (x == xLen && y == yLen) {
            return 0;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        int answer = Integer.MIN_VALUE;
        if (x < xLen && y < yLen) {
            if (X[x] == Y[y]) {
                answer = Math.max(answer, recur(x + 1, y + 1) + A);
            } else {
                answer = Math.max(answer, recur(x + 1, y + 1) + C);
            }
        }

        if (x < xLen) {
            answer = Math.max(answer, recur(x + 1, y) + B);
        }

        if (y < yLen) {
            answer = Math.max(answer, recur(x, y + 1) + B);
        }

        return dp[x][y] = answer;
    }
}
