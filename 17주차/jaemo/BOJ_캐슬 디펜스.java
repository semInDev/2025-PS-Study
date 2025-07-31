import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int ARCHERS = 3;

    static int[][] board;
    static int N, M, D;
    static int answer = 0;
    static int[] dRow = {-1, 0, 0};
    static int[] dCol = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] archers = new int[ARCHERS];
        dfs(archers, 0, 0);
        System.out.println(answer);
    }

    public static void dfs(int[] archers, int start, int depth) {
        if (depth == ARCHERS) {
            int[][] newBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newBoard[i][j] = board[i][j];
                }
            }

            int deadCnt = 0;
            while (existsEnemy(newBoard)) {
                deadCnt += attack(newBoard, archers);
                moveEnemy(newBoard);
            }
            answer = Math.max(answer, deadCnt);
            return;
        }

        for (int i = start; i < M; i++) {
            archers[depth] = i;
            dfs(archers, i + 1, depth + 1);
            archers[depth] = 0;
        }
    }

    // 적 공격
    public static int attack(int[][] board, int[] archers) {
        List<Point> attackEnemies = new ArrayList<>();
        for (int i = 0; i < ARCHERS; i++) {
            List<Point> enemies = new ArrayList<>();
            Queue<Point> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];
            queue.offer(new Point(N - 1, archers[i], 0));
            visited[N - 1][archers[i]] = true;
            while (!queue.isEmpty()) {
                Point now = queue.poll();
                if (Math.abs(N - 1 - now.r) + Math.abs(archers[i] - now.c) > D) {
                    continue;
                }
                if (board[now.r][now.c] == 1) {
                    enemies.add(new Point(now.r, now.c, now.d));
                }
                for (int d = 0; d < 3; d++) {
                    int nr = now.r + dRow[d];
                    int nc = now.c + dCol[d];
                    if (nr < 0 || nr == N - 1 || nc < 0 || nc >= M) {
                        continue;
                    }
                    if (!visited[nr][nc]) {
                        queue.offer(new Point(nr, nc, now.d + 1));
                        visited[nr][nc] = true;
                    }
                }
            }
            if (enemies.isEmpty()) {
                continue;
            }
            Collections.sort(enemies);
            attackEnemies.add(enemies.get(0));
        }

        int deadCnt = 0;
        for (Point enemyPoint : attackEnemies) {
            if (board[enemyPoint.r][enemyPoint.c] == 1) {
                board[enemyPoint.r][enemyPoint.c] = 0;
                deadCnt++;
            }
        }

        return deadCnt;
    }

    // 적 이동
    public static void moveEnemy(int[][] board) {
        for (int i = N - 2; i >= 1; i--) {
            for (int j = 0; j < M; j++) {
                board[i][j] = board[i - 1][j];
            }
        }

        for (int j = 0; j < M; j++) {
            board[0][j] = 0;
        }
    }

    // 적 유무 확인
    public static boolean existsEnemy(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Point implements Comparable<Point> {
    int r, c, d;

    public Point(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Point o) {
        if (this.d == o.d) {
            if (this.c == o.c) {
                return this.r - o.r;
            }
            return this.c - o.c;
        }
        return this.d - o.d;
    }
}
