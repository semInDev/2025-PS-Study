import java.io.*;
import java.util.*;

public class Main {
    static int n, m, g, r;
    static int[][] board = new int[52][52];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<int[]> cand = new ArrayList<>();
    static int candsz;
    
    static int EMPTY = 0;
    static int GREEN = 1;
    static int RED = 2;
    static int FLOWER = 3;
    
    static int[] brute = new int[10];

    static class Pair {
        int time, color;
        Pair(int t, int c) {
            this.time = t;
            this.color = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    cand.add(new int[]{i, j});
                }
            }
        }

        candsz = cand.size();
        Arrays.fill(brute, 0, candsz - g - r, EMPTY);
        Arrays.fill(brute, candsz - g - r, candsz - r, GREEN);
        Arrays.fill(brute, candsz - r, candsz, RED);

        int mx = 0;
        do {
            mx = Math.max(mx, solve());
        } while (nextPermutation(brute, candsz));

        System.out.println(mx);
    }

    static int solve() {
        int cnt = 0;
        Pair[][] state = new Pair[52][52];
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                state[i][j] = new Pair(-1, EMPTY);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < candsz; i++) {
            if (brute[i] == GREEN || brute[i] == RED) {
                int[] pos = cand.get(i);
                state[pos[0]][pos[1]] = new Pair(0, brute[i]);
                q.offer(pos);
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curtime = state[cur[0]][cur[1]].time;
            int curcolor = state[cur[0]][cur[1]].color;
            if (state[cur[0]][cur[1]].color == FLOWER) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == 0) continue;

                if (state[nx][ny].color == EMPTY) {
                    state[nx][ny] = new Pair(curtime + 1, curcolor);
                    q.offer(new int[]{nx, ny});
                } else if (state[nx][ny].color == RED) {
                    if (curcolor == GREEN && state[nx][ny].time == curtime + 1) {
                        cnt++;
                        state[nx][ny].color = FLOWER;
                    }
                } else if (state[nx][ny].color == GREEN) {
                    if (curcolor == RED && state[nx][ny].time == curtime + 1) {
                        cnt++;
                        state[nx][ny].color = FLOWER;
                    }
                }
            }
        }
        return cnt;
    }

    static boolean nextPermutation(int[] arr, int n) {
        int i = n - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i <= 0) return false;

        int j = n - 1;
        while (arr[j] <= arr[i - 1]) j--;
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = n - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
