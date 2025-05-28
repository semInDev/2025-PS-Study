import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

       for (int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
           for (int j = 0; j < M; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
           }
       }

        while (true) {
            int count = countIce();
            if (count >= 2) {
                System.out.println(time);
                return;
            }
            if (count == 0) {
                System.out.println(0);
                return;
            }

            subIce();
            time++;
        }
    }

    private static int countIce() {
        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    DFS(i, j);
                    count++;
                }
            }
        }

        return count;
    }

    static void DFS(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (map[nx][ny] > 0 && !visited[nx][ny]) {
                    DFS(nx, ny);
                }
            }
        }
    }

    static void subIce() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int count = 0;
                    for (int k=0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                            count++;
                        }
                    }
                    temp[i][j] = Math.max(0, map[i][j] - count);
                }
            }
        }
        map = temp;
    }
}
