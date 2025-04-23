import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] arr;
    static int[][] dp;
    static int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(DFS(0, 0));
    }

    private static int DFS(int x, int y) {
        if (x == M - 1 && y == N - 1) return 1;

        if (dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N && arr[nx][ny] < arr[x][y]) {
                dp[x][y] += DFS(nx, ny);
            }
        }
        return dp[x][y];
    }
}
