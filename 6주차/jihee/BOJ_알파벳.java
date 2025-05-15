import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다
 */
public class BOJ_알파벳 {
    static int N, M;
    static char[][] map;
    static boolean[] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[26];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;

        recur(1, 0, 0);
        System.out.println(answer);

    }

    private static void recur(int count, int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            int index = map[nx][ny] - 'A';
            if (!visited[index]) {
                visited[index] = true;
                recur(count + 1, nx, ny);
                visited[index] = false;
            }
        }

        answer = Math.max(answer, count);
    }
}