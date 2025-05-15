import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static Character[][] arr;
    static boolean[] visited = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new Character[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        visited[arr[0][0] - 'A'] = true;
        DFS(0, 0, 1);

        System.out.println(max);
    }

    static void DFS(int x, int y, int depth) {
        max = Math.max(max, depth);

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            int index = arr[nx][ny] - 'A';
            if (!visited[index]) {
                visited[index] = true;
                DFS(nx, ny, depth + 1);
                visited[index] = false;
            }
        }
    }

}
