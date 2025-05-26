import java.io.*;
import java.util.*;

public class Main {
    static int k, v, e;
    static int[] gr = new int[20002];
    static List<Integer>[] graph = new ArrayList[20002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int vv = Integer.parseInt(st.nextToken());
                graph[u].add(vv);
                graph[vv].add(u);
            }

            if (solve()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean solve() {
        Arrays.fill(gr, -1);

        for (int i = 1; i <= v; i++) {
            if (gr[i] != -1) continue;

            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            gr[i] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (Integer next : graph[cur]) {
                    if (gr[next] != -1) {
                        if (gr[next] == gr[cur]) return false;
                        continue;
                    }
                    gr[next] = (gr[cur] + 1) % 2;
                    q.add(next);
                }
            }
        }
        return true;
    }
}
