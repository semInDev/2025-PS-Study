import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        visited = new boolean[n][m + 1];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < tmp[1].length(); j++) {
                if (tmp[1].charAt(j) == 'Y') {
                    visited[i][j + 1] = true;
                } else visited[i][j + 1] = false;
            }
        }

        int[] song = new int[m + 1];
        recur(0, 0, song);

        if (answer[0] > 0 && answer[1] > 0) {
            System.out.println(answer[0]);
        } else System.out.println(-1);
    }

    private static void recur(int cur, int cnt, int[] song) {
        if (cur == n) {
            int num = 0;
            for (int i = 1; i < m + 1; i++) {
                if (song[i] >= 1) num++;
            }
            if (num >= answer[1] && cnt <= answer[0]) {
                answer[0] = cnt;
                answer[1] = num;
            }
            return;
        }

        for (int i = 1; i < m + 1; i++) {
            if (visited[cur][i]) {
                song[i]++;
            }
        }
        recur(cur + 1, cnt + 1, song);

        for (int i = 1; i < m + 1; i++) {
            if (visited[cur][i]) {
                song[i]--;
            }
        }
        recur(cur + 1, cnt, song);
    }
}
