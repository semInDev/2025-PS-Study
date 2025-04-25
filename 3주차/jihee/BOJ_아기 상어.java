import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int size = 2;
    static Shark baby;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int eatCnt = 0;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    baby = new Shark(i, j);
                }
            }
        }

        while (true) {
            if (!move())
                break;
        }

        System.out.println(time);
    }

    public static boolean move() {

        Queue<Shark> que = new LinkedList<>();
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        int[][] visited = new int[N][N];

        visited[baby.y][baby.x] = 1;
        que.offer(baby);
        int dist = Integer.MAX_VALUE;

        while (!que.isEmpty()) {
            Shark temp = que.poll();

            if (visited[temp.y][temp.x] >= dist)
                continue;

            for (int i = 0; i < 4; i++) {
                int ny = temp.y + dy[i];
                int nx = temp.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] > 0 || size < map[ny][nx])
                    continue;

                if (map[ny][nx] > 0 && map[ny][nx] < size) {
                    pq.offer(new Shark(ny, nx));
                    dist = visited[temp.y][temp.x] + 1;
                }
                visited[ny][nx] = visited[temp.y][temp.x] + 1;
                que.offer(new Shark(ny, nx));
            }
        }

        if (!pq.isEmpty()) {
            Shark temp = pq.poll();
            time += visited[temp.y][temp.x] - 1;

            if (++eatCnt == size) {
                size++;
                eatCnt = 0;
            }

            map[temp.y][temp.x] = 0;

            baby.y = temp.y;
            baby.x = temp.x;

            return true;
        }
        return false;
    }

    public static class Shark implements Comparable<Shark> {
        int y;
        int x;

        Shark(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.y == o.y)
                return this.x - o.x;
            else
                return this.y - o.y;
        }
    }
}