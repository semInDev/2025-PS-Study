import java.io.*;
import java.util.*;

public class Main {
    static boolean[] mask = new boolean[25];
    static String[] board = new String[5];
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine();
        }

        Arrays.fill(mask, 7, 25, true);

        do {
            Queue<int[]> q = new LinkedList<>();
            int dasom = 0, adj = 0;
            boolean[][] isp7 = new boolean[5][5];
            boolean[][] vis = new boolean[5][5];

            for (int i = 0; i < 25; i++) {
                if (!mask[i]) {
                    int x = i / 5, y = i % 5;
                    isp7[x][y] = true;
                    if (q.isEmpty()) {
                        q.add(new int[]{x, y});
                        vis[x][y] = true;
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                adj++;
                if (board[x].charAt(y) == 'S') dasom++;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k], ny = y + dy[k];
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                    if (vis[nx][ny] || !isp7[nx][ny]) continue;
                    q.add(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }

            if (adj >= 7 && dasom >= 4) ans++;

        } while (nextPermutation(mask));

        System.out.println(ans);
    }

    static boolean nextPermutation(boolean[] arr) {
        int i = arr.length - 1;
        while (i > 0 && !(!arr[i - 1] && arr[i])) i--;
        if (i <= 0) return false;

        int j = arr.length - 1;
        while (!(!arr[i - 1] && arr[j])) j--;

        boolean tmp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = tmp;

        j = arr.length - 1;
        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        return true;
    }
}
