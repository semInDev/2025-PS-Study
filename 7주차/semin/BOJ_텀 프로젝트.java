import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] state;
    static int notVisited = 0;
    static int cycle = -1;
    static int n;

    public static void run(int x) {
        int cur = x;
        while (true) {
            state[cur] = x;
            cur = arr[cur];

            if (state[cur] == x) {
                while (state[cur] != cycle) {
                    state[cur] = cycle;
                    cur = arr[cur];
                }
                return;
            } else if (state[cur] != notVisited) {
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            state = new int[n + 1];
            Arrays.fill(state, notVisited);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (state[i] == notVisited) {
                    run(i);
                }
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (state[i] != cycle) {
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}
