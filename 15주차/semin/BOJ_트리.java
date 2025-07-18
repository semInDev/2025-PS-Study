import java.io.*;
import java.util.*;

public class Main {
    static private List<List<Integer>> graph;
    static private boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = 1;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            visited = new boolean[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    cnt += isTree(i);
                }
            }

            sb.append("Case ").append(testCase).append(": ");
            if (cnt > 1) {
                sb.append("A forest of ").append(cnt).append(" trees.");
            } else if (cnt == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("No trees.");
            }
            sb.append("\n");
            testCase++;
        }

        System.out.print(sb);
    }

    private static int isTree(int root) {
        Queue<Integer> queue = new LinkedList<>();
        int nodeCnt = 0;
        int edgeCnt = 0;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited[cur]) continue;

            visited[cur] = true;
            nodeCnt++;

            for (int nxt : graph.get(cur)) {
                edgeCnt++;
                if (!visited[nxt]) {
                    queue.offer(nxt);
                }
            }
        }

        return (edgeCnt / 2) + 1 == nodeCnt ? 1 : 0;
    }
}
