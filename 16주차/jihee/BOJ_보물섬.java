import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 육지(L) 바다(W)
 * 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다.
 * 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다
 * . 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.
 */
public class Main {

    static char[][] map;
    static boolean[][] visited;
    static int N, M, answer;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];   // 지도 생성

        answer = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[N][M];
                    bfs(new Node(i, j, 0));
                }
            }
        }
        System.out.println(answer);
    }

    static class Node {
        int x, y, len;

        public Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited[node.x][node.y] = true;

        while (!q.isEmpty()) {
            Node nextN = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = nextN.x + dx[k];
                int ny = nextN.y + dy[k];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (!visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, nextN.len + 1));
                    answer = Math.max(answer, nextN.len + 1);
                }
            }
        }
    }

}
