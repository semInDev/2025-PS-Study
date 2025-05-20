import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][][] visited = new boolean[H][W][K + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], k = cur[2], dist = cur[3];

            if (x == W - 1 && y == H - 1) {
                System.out.println(dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (0 <= nx && nx < W && 0 <= ny && ny < H) {
                    if (!visited[ny][nx][k] && map[ny][nx] == 0) {
                        visited[ny][nx][k] = true;
                        q.add(new int[]{nx, ny, k, dist + 1});
                    }
                }
            }

            if (k < K) {
                for (int d = 0; d < 8; d++) {
                    int nx = x + hx[d], ny = y + hy[d];
                    if (0 <= nx && nx < W && 0 <= ny && ny < H) {
                        if (!visited[ny][nx][k + 1] && map[ny][nx] == 0) {
                            visited[ny][nx][k + 1] = true;
                            q.add(new int[]{nx, ny, k + 1, dist + 1});
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
