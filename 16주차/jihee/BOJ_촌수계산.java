import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int p1, p2;
    static int[][] arr;
    static boolean[] visited;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = arr[y][x] = 1;
        }

        dfs(p1, p2, 0);
        System.out.println(answer);
    }

    private static void dfs(int cur, int end, int count) {
        if (cur == end) {
            answer = count;
            return;
        }
        visited[cur] = true;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && arr[cur][i] == 1) {
                dfs(i, end, count + 1);
            }
        }
    }
}
