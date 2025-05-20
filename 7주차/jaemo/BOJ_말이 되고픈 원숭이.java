import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static int[] dRowHorse = {2, 1, 2, 1, -2, -1, -2, -1};
    static int[] dColHorse = {1, 2, -1, -2, 1, 2, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] map = new int[w][h];
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < h; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[w][h][k + 1];
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.r == w - 1 && now.c == h - 1) {
                answer = now.d;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dRow[d];
                int nc = now.c + dCol[d];
                if (nr < 0 || nr >= w || nc < 0 || nc >= h) {
                    continue;
                }
                if (map[nr][nc] == 0 && !visited[nr][nc][now.horseCnt]) {
                    queue.offer(new Point(nr, nc, now.d + 1, now.horseCnt));
                    visited[nr][nc][now.horseCnt] = true;
                }
            }
            if (now.horseCnt < k) {
                for (int d = 0; d < 8; d++) {
                    int nr = now.r + dRowHorse[d];
                    int nc = now.c + dColHorse[d];
                    if (nr < 0 || nr >= w || nc < 0 || nc >= h) {
                        continue;
                    }
                    if (map[nr][nc] == 0 && !visited[nr][nc][now.horseCnt + 1]) {
                        queue.offer(new Point(nr, nc, now.d + 1, now.horseCnt + 1));
                        visited[nr][nc][now.horseCnt + 1] = true;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Point {
    int r, c, d, horseCnt;

    public Point(int r, int c, int d, int horseCnt) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.horseCnt = horseCnt;
    }
}

// 목적지까지 최소 동작 횟수 구하기
// 시작점은 동작 횟수 포함 X
