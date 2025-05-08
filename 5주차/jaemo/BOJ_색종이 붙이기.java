import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[11][11];
    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 1; i <= 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new int[]{0, 5, 5, 5, 5, 5}, 1, 1, 0);

        if (ANSWER == Integer.MAX_VALUE) {
            ANSWER = -1;
        }

        System.out.println(ANSWER);
    }

    public static void dfs(int[] papers, int r, int c, int paperCnt) {
        if (r >= 10 && c > 10) {
            // 최솟값 갱신
            if (isAllAttached()) {
                ANSWER = Math.min(ANSWER, paperCnt);
            }
            return;
        }

        if (c > 10) {
            dfs(papers, r + 1, 0, paperCnt);
            return;
        }

        if (map[r][c] == 1) {
            for (int k = 5; k >= 1; k--) {
                if (papers[k] > 0 && canAttach(r, c, k)) {
                    papers[k]--;
                    attach(r, c, k, 0);
                    dfs(papers, r, c + k, paperCnt + 1);
                    papers[k]++;
                    attach(r, c, k, 1);
                }
            }
        } else {
            dfs(papers, r, c + 1, paperCnt);
        }
    }

    private static boolean isAllAttached() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (map[0][0] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canAttach(int r, int c, int length) {
        if (r + length > 11 || c + length > 11) {
            return false;
        }
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void attach(int r, int c, int length, int flag) {
        for (int i = r; i < r + length; i++) {
            for (int j = c; j < c + length; j++) {
                map[i][j] = flag;
            }
        }
    }
}
