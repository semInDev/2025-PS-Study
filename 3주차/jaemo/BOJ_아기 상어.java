import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int sharkSize = 2;
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, -1, 0, 1};
    static Point sharkPoint;
    static int N;
    static int eatCnt = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    sharkPoint = new Point(i, j, 0);
                }
            }
        }

        while (true) {
            sharkPoint.initDist();
            if (!eatFishes(map)) {
                break;
            }
        }

        System.out.println(answer);
    }

    // bfs 물고기 탐색 및 식사
    public static boolean eatFishes(int[][] map) {
        Queue<Point> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(sharkPoint);
        visited[sharkPoint.r][sharkPoint.c] = true;

        boolean isAte = false;
        while (!queue.isEmpty()) {
            sharkPoint = queue.poll();

            // 물고기 존재하면 먹음
            if (map[sharkPoint.r][sharkPoint.c] < sharkSize && map[sharkPoint.r][sharkPoint.c] > 0) {
                map[sharkPoint.r][sharkPoint.c] = 0;
                eatCnt++;
                answer += sharkPoint.d;
                isAte = true;
                break;
            }

            // 상하좌우 이동
            for (int d = 0; d < 4; d++) {
                int nr = sharkPoint.r + dRow[d];
                int nc = sharkPoint.c + dCol[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > sharkSize || visited[nr][nc]) {
                    continue;
                }
                queue.add(new Point(nr, nc, sharkPoint.d + 1));
                visited[nr][nc] = true;
            }
        }

        if (!isAte) {
            return false;
        }

        if (eatCnt == sharkSize) {
            sharkSize++;
            eatCnt = 0;
        }

        return true;
    }
}

class Point implements Comparable<Point> {
    int r, c, d;

    public Point(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Point o) {
        // 거리 > 위쪽 > 왼쪽
        if (this.d == o.d) {
            if (this.r == o.r) {
                return this.c - o.c;
            }
            return this.r - o.r;
        }
        return this.d - o.d;
    }

    public void initDist() {
        this.d = 0;
    }
}

// 한 칸 물고기 최대 1마리
// 크기는 자연수
// 상어 크기 최초 2
// 상어는 자신보다 큰 물고기 있는 칸 이동 X
// 상어는 자신보다 작은 물고기만 먹음
// 가장 가까운 왼쪽 위 물고기부터 먹음
// 상어 이동 시간 = 1초, 먹는 시간 X
// 상어는 자신의 크기와 같은 수의 물고기 먹으면 크기 1 증가

// bfs를 통해 상어가 최단거리 물고기를 순차적으로 먹었을 때 최종 시간 구하기
