import java.util.*;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int[] dRow = {1,0,-1,0};
    static int[] dCol = {0,1,0,-1};
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        
        dp = new int[100][100][101];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.min(answer, dfs(grid, k, i, j, k));
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static int dfs(int[][] grid, int k, int r, int c, int l) {
        if (dp[r][c][l] != -1) {
            return dp[r][c][l];
        }

        if (l == 1) {
            dp[r][c][l] = 0;
            return dp[r][c][l];
        }

        int best = Integer.MAX_VALUE;
        for (int d=0;d<4;d++) {
            int nr = r + dRow[d];
            int nc = c + dCol[d];
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid.length) {
                continue;
            }
            if (grid[nr][nc] > grid[r][c]) {
                int prevBest = dfs(grid, k, nr, nc, l-1);
                best = Math.min(best, Math.max(prevBest, grid[nr][nc] - grid[r][c]));
            }
        }
        
        dp[r][c][l] = best;
        return dp[r][c][l];
    }
}
