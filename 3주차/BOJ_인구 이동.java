import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Node> node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

    public static int move() {
        int result = 0;
        while (true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);
                        if (node.size() > 1) {
                            changePopulation(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if (!isMove)
                return result;
            result++;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        node = new ArrayList<>();

        q.offer(new Node(x, y));
        node.add(new Node(x, y));
        visited[x][y] = true;

        int sum = arr[x][y];
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(arr[cur.x][cur.y] - arr[nx][ny]);
                    if (L <= diff && diff <= R) {
                        q.offer(new Node(nx, ny));
                        node.add(new Node(nx, ny));
                        sum += arr[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return sum;
    }

    public static void changePopulation(int sum) {
        int avg = sum / node.size();
        for (Node node : node) {
            arr[node.x][node.y] = avg;
        }
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}