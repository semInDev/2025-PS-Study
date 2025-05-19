import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] board;
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] split = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(split[j - 1]);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n + 1][m + 1][2];
        queue.add(new Point(1, 1, 1, false));
        visited[1][1][0] = true;
        visited[1][1][1] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.r == n && now.c == m) {
                return now.d;
            }
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dRow[d];
                int nc = now.c + dCol[d];
                if (nr < 1 || nc < 1 || nr > n || nc > m) {
                    continue;
                }
                if (board[nr][nc] == 0) {
                    if (!now.hasBroken && !visited[nr][nc][0]) {
                        queue.add(new Point(nr, nc, now.d + 1, false));
                        visited[nr][nc][0] = true;
                        continue;
                    }
                    if (now.hasBroken && !visited[nr][nc][1]) {
                        queue.add(new Point(nr, nc, now.d + 1, true));
                        visited[nr][nc][1] = true;
                    }
                } else {
                    if (!now.hasBroken) {
                        queue.add(new Point(nr, nc, now.d + 1, true));
                        visited[nr][nc][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}

class Point {
    int r, c, d;
    boolean hasBroken;

    public Point(int r, int c, int d, boolean hasBroken) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.hasBroken = hasBroken;
    }
}
