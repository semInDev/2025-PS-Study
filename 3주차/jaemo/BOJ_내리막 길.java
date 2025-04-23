import java.io.*;
import java.util.*;

public class Main {
    static int ROW, COL;
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        map = new int[ROW][COL];
        dp = new int[ROW][COL]; // 메모이제이션 배열
        for (int i = 0; i < ROW; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COL; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 아직 방문 안 했다는 의미
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        if (r == ROW - 1 && c == COL - 1) return 1;

        if (dp[r][c] != -1) return dp[r][c]; // 이미 계산한 경우

        dp[r][c] = 0; // 초기값

        for (int d = 0; d < 4; d++) {
            int nr = r + dRow[d];
            int nc = c + dCol[d];

            if (nr >= 0 && nr < ROW && nc >= 0 && nc < COL) {
                if (map[r][c] > map[nr][nc]) { // 내리막
                    dp[r][c] += dfs(nr, nc);
                }
            }
        }

        return dp[r][c];
    }
}
