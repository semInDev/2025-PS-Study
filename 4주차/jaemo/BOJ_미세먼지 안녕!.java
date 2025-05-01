import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int topRow = 0;
        int bottomRow = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (topRow != 0) {
                        bottomRow = i;
                    } else {
                        topRow = i;
                    }
                }
            }
        }

        int[][] lastMap = new int[R][C];
        while (T-- > 0) {
            // 미세먼지 확산
            int[][] newMap = new int[R][C];
            for (int i = 0; i < R; i++) {
                newMap[i] = Arrays.copyOf(map[i], map[i].length);
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    int cnt = 0;
                    if (map[r][c] > 0) {
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dRow[d];
                            int nc = c + dCol[d];
                            if (nr < 0 || nr >= R || nc < 0 || nc >= C || (r == topRow && c == 0) || (r == bottomRow && c == 0)) {
                                continue;
                            }
                            if ((nr == topRow && nc == 0) || (nr == bottomRow && nc == 0)) {
                                continue;
                            }
                            newMap[nr][nc] += Math.abs(map[r][c] / 5);
                            cnt++;
                        }
                        newMap[r][c] -= Math.abs(map[r][c] / 5) * cnt;
                    }
                }
            }

            // 공기 청정기 (위) 작동
            for (int r = topRow - 1; r >= 1; r--) {
                newMap[r][0] = newMap[r - 1][0];
            }

            for (int c = 0; c < C - 1; c++) {
                newMap[0][c] = newMap[0][c + 1];
            }

            for (int r = 0; r < topRow; r++) {
                newMap[r][C - 1] = newMap[r + 1][C - 1];
            }

            for (int c = C - 1; c >= 2; c--) {
                newMap[topRow][c] = newMap[topRow][c - 1];
            }
            newMap[topRow][1] = 0;

            // 공기 청정기 (아래) 작동
            for (int r = bottomRow + 1; r < R - 1; r++) {
                newMap[r][0] = newMap[r + 1][0];
            }

            for (int c = 0; c < C - 1; c++) {
                newMap[R - 1][c] = newMap[R - 1][c + 1];
            }

            for (int r = R - 1; r >= bottomRow + 1; r--) {
                newMap[r][C - 1] = newMap[r - 1][C - 1];
            }

            for (int c = C - 1; c >= 2; c--) {
                newMap[bottomRow][c] = newMap[bottomRow][c - 1];
            }
            newMap[bottomRow][1] = 0;

            if (T == 0) {
                for (int i = 0; i < R; i++) {
                    lastMap[i] = Arrays.copyOf(newMap[i], newMap[i].length);
                }
                break;
            }

            for (int i = 0; i < R; i++) {
                map[i] = Arrays.copyOf(newMap[i], newMap[i].length);
            }
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (lastMap[i][j] < 0) {
                    continue;
                }
                answer += lastMap[i][j];
            }
        }
        System.out.println(answer);
    }
}
