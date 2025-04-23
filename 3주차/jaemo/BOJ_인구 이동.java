import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean isEnd = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<Point> unionPoints = open(map, visited, new Point(i, j, map[i][j]));
                        if (unionPoints.size() > 1) {
                            move(unionPoints, map);
                            isEnd = false;
                        }
                    }
                }
            }

            if (isEnd) {
                break;
            }

            answer++;
        }

        System.out.println(answer);
    }

    // 국경선 open
    public static List<Point> open(int[][] map, boolean[][] visited, Point startPoint) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        visited[startPoint.r][startPoint.c] = true;

        List<Point> unionPoints = new ArrayList<>();
        unionPoints.add(startPoint);

        while (!queue.isEmpty()) {
            Point polled = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = polled.r + dRow[d];
                int nc = polled.c + dCol[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                int diff = Math.abs(map[nr][nc] - map[polled.r][polled.c]);
                if (!visited[nr][nc] && (L <= diff && diff <= R)) {
                    Point adjPoint = new Point(nr, nc, map[nr][nc]);
                    queue.add(adjPoint);
                    visited[nr][nc] = true;
                    unionPoints.add(adjPoint);
                }
            }
        }

        return unionPoints;
    }

    // 인구 이동
    public static void move(List<Point> unionPoints, int[][] map) {
        if (unionPoints.isEmpty()) {
            return;
        }
        int total = 0;
        for (Point point : unionPoints) {
            total += point.n;
        }
        int movedNumber = total / unionPoints.size();
        for (Point point : unionPoints) {
            map[point.r][point.c] = movedNumber;
        }
    }
}

class Point {
    int r, c, n;

    public Point(int r, int c, int n) {
        this.r = r;
        this.c = c;
        this.n = n;
    }
}

// if L <= 인접한 두 나라의 인구 차이 <= R -> 국경선 open
// 모든 국경선이 열리면 이동 시작
// 연합을 이루는 각 칸의 인구수 = 연합 인구수/연합을 이루는 칸 개수 (소수점 버림)

// 인구 이동이 없을 때까지 며칠 걸림?
