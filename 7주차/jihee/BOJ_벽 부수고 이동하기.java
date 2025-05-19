import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 벽 한개까지 부술 수 있음
 *  visited[x][y][현재벽을부순개수]
 * */

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][2];
        arr = new int[N][M];
        answer = -1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;

            if (x == N - 1 && y == M - 1) {
                answer = node.count;
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 > nx || nx >= N || 0 > ny || ny >= M) continue;

                if (arr[nx][ny] == 0 && !visited[nx][ny][node.broken]) {
                    visited[nx][ny][node.broken] = true;
                    q.add(new Node(nx, ny, node.count + 1, node.broken));
                } else {

                    if (node.broken == 0 && !visited[nx][ny][node.broken + 1]) {
                        visited[nx][ny][node.broken + 1] = true;
                        q.add(new Node(nx, ny, node.count + 1, node.broken + 1));
                    }
                }

            }
        }
    }

    static class Node {
        int x;
        int y;
        int count;
        int broken;

        Node(int x, int y, int count, int broken) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.broken = broken;
        }
    }
}
