import java.io.*;
import java.util.*;

public class Main {
    static int n, ans;
    static int[] dur = new int[10002];
    static int[] indegree = new int[10002];
    static List<Integer>[] rel = new ArrayList[10002];
    static List<Integer>[] timetable = new ArrayList[1000002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10002; i++) rel[i] = new ArrayList<>();
        for (int i = 0; i < 1000002; i++) timetable[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dur[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (m == 0) {
                timetable[dur[i]].add(i);
            }

            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                rel[x].add(i);
                indegree[i]++;
            }
        }

        for (int t = 0; t <= 1000000; t++) {
            for (int finished : timetable[t]) {
                ans = t;
                for (int r : rel[finished]) {
                    indegree[r]--;
                    if (indegree[r] != 0) continue;
                    timetable[t + dur[r]].add(r);
                }
            }
        }

        System.out.println(ans);
    }
}
