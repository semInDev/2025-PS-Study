import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int size;
    static int X;
    static int Y;
    static boolean[][] visited;
    static int[][] arr;
    static int fish = 0;
    static final int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        arr = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    X = i;
                    Y = j;
                    arr[i][j] = 0;
                }
            }
        }

        size = 2;
        int answer = 0;
        while (true) {
            int time = BFS();
            if (time == -1) break;
            answer += time;
        }
        System.out.println(answer);
    }

    private static int BFS() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{X, Y, 0});
        visited[X][Y] = true;

        List<int[]> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];

            if (arr[x][y] > 0 && arr[x][y] < size) {
                if (dist < min) {
                    list.clear();
                    min = dist;
                    list.add(new int[]{x, y});
                } else if (dist == min) {
                    list.add(new int[]{x, y});
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && arr[nx][ny] <= size) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        if (list.isEmpty()) {
            return -1;
        }

        
        int tx = list.get(0)[0];
        int ty = list.get(0)[1];
        for (int[] fish : list) {
            if (fish[0] < tx || (fish[0] == tx && fish[1] < ty)) {
                tx = fish[0];
                ty = fish[1];
            }
        }

        arr[tx][ty] = 0;
        X =  tx;
        Y =  ty;
        fish++;
        
        if (fish == size) {
            size++;
            fish = 0;
        }

        return min;
    }
}
