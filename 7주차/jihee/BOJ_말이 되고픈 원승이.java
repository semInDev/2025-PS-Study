import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0}); // x, y, 점프횟수, 이동횟수
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], jump = cur[2], cnt = cur[3];

            if (x == H - 1 && y == W - 1) return cnt;

            // 일반 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < H && ny < W) {
                    if (!visited[nx][ny][jump] && map[nx][ny] == 0) {
                        visited[nx][ny][jump] = true;
                        q.add(new int[]{nx, ny, jump, cnt + 1});
                    }
                }
            }

            // 말처럼 이동
            if (jump < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hx[i], ny = y + hy[i];
                    if (nx >= 0 && ny >= 0 && nx < H && ny < W) {
                        if (!visited[nx][ny][jump + 1] && map[nx][ny] == 0) {
                            visited[nx][ny][jump + 1] = true;
                            q.add(new int[]{nx, ny, jump + 1, cnt + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}