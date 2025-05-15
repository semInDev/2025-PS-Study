import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

// . 색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5

/**
 * 체크해야될 것 : x가 1일때 붙일 색종이의 크기, 인접한 색종이 크기 체크
 * 색종이 크기가 경계밖이면 안됨.
 * 불가능 -1
 */
public class Main {

    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[] papers = {0, 5, 5, 5, 5, 5}; // 색종이 개수 체크 1~5

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        map = new int[10][10];

        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }


    }

    private static void recur(int x, int y, int count) {

        if (y == 10) {
            x++;
            y = 0;
        }

        if (x == 10) {
            min = Math.min(min, count);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (papers[i] == 0) {
                    continue;
                }
                if (check(x, y, i)) {
                    attach(x, y, i, 0);
                    papers[i]--;
                    recur(x, y + 1, count + 1);
                    attach(x, y, i, 1);
                    papers[i]++;
                }
            }
        } else {
            recur(x, y + 1, count);
        }


    }

    private static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

    private static boolean check(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;

    }
}
