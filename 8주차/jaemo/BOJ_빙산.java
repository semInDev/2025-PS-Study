import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int ROW, COL;
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());
        int[][] map = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COL; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean hasSplit = false;
        while (true) {
            if (isAllZero(map)) {
                break;
            }

            if (hasSplit(map)) {
                hasSplit = true;
                break;
            }

            answer++;

            melt(map);
        }

        if (hasSplit) {
            System.out.println(answer);
        } else {
            System.out.println(0);
        }
    }

    private static boolean isAllZero(int[][] map) {
        for (int r= 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (map[r][c] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasSplit(int[][] map) {
        int iceCnt = 0;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[ROW][COL];
        for (int r= 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (map[r][c] > 0 && !visited[r][c]) {
                    iceCnt++;
                    queue.offer(new Point(r, c));
                    visited[r][c] = true;
                    while (!queue.isEmpty()) {
                        Point now = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = now.r + dRow[d];
                            int nc = now.c + dCol[d];
                            if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL) {
                                continue;
                            }
                            if (!visited[nr][nc] && map[nr][nc] > 0) {
                                queue.offer(new Point(nr, nc));
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
                if (iceCnt >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void melt(int[][] map) {
        int[][] newMap = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }

        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (map[r][c] > 0) {
                    int oceanCnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dRow[d];
                        int nc = c + dCol[d];
                        if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL) {
                            continue;
                        }
                        if (map[nr][nc] == 0) {
                            oceanCnt++;
                        }
                    }
                    newMap[r][c] -= oceanCnt;
                    if (newMap[r][c] < 0) {
                        newMap[r][c] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < ROW; i++) {
            map[i] = Arrays.copyOf(newMap[i], newMap[i].length);
        }
    }
}

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

// 한 덩어리의 빙산이 둘 이상으로 나눠지는 최소 년수 구하기
// 빙산은 1년마다 인접 바다 개수만큼 녹음
// 끝까지 둘 이상으로 안 나눠지면 0 출력

//        0 0 0 0 0 0 0
//        0 2 4 5 3 0 0
//        0 3 0 2 5 2 0
//        0 7 6 2 4 0 0
//        0 0 0 0 0 0 0
