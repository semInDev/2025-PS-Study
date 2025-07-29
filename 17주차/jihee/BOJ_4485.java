import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] cost;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            visited = new boolean[N][N];
            cost = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            System.out.println("Problem " + cnt + ": " + cost[N - 1][N - 1]);

            cnt++;

        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited[0][0] = true;
        cost[0][0] = map[0][0];
        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.x + dy[i];
                int nx = node.y + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;

                if (node.cnt + map[ny][nx] < cost[ny][nx]) {
                    visited[ny][nx] = true;
                    cost[ny][nx] = node.cnt + map[ny][nx];
                    pq.offer(new Node(ny, nx, cost[ny][nx]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
}
