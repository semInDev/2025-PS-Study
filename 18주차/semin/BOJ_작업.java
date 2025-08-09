import java.io.*;
import java.util.*;

public class Main {
    static int n, ans = 0;
    static int[] dur = new int[10002];
    static int[] ind = new int[10002];
    static List<Integer>[] rel = new ArrayList[10002];
    static List<Integer>[] tt = new ArrayList[1000002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            rel[i] = new ArrayList<>();
        }
        for (int i = 0; i <= 1_000_000; i++) {
            tt[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            dur[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (m == 0) {
                tt[dur[i]].add(i);
            }
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                rel[x].add(i);
                ind[i]++;
            }
        }

        for (int t = 0; t <= 1_000_000; t++) {
            for (int finished : tt[t]) {
                ans = t;
                for (int r : rel[finished]) {
                    ind[r]--;
                    if (ind[r] != 0) continue;
                    tt[t + dur[r]].add(r);
                }
            }
        }

        System.out.println(ans);
    }
}
