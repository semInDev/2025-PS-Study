import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
      
        map = new int[n][m];
      
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            if (bfsCount(graph) == 0) {
                answer = 0;
                break;
            }
            if (bfsCount(graph) >= 2) {
                break;
            }
            bfs();
            answer++;
        }
        System.out.println(answer);

    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] != 0) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (graph[nx][ny] == 0) {
                    if (graph[x][y] > 0)
                        graph[x][y]--;
                }
            }
        }
    }

    private static int bfsCount(int[][] map) {
        int result = 0;
        boolean[][] check = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !check[i][j]) {
                    q.offer(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] tmp = q.poll();
                        int tx = tmp[0];
                        int ty = tmp[1];
                        for (int d = 0; d < 4; d++) {
                            int nx = tx + dx[d];
                            int ny = ty + dy[d];
                            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                            if (map[nx][ny] == 0 || check[nx][ny]) continue;
                            q.offer(new int[]{nx, ny});
                            check[nx][ny] = true;
                        }
                    }
                    result++;
                }
            }
        }
        return result;
    }
}
