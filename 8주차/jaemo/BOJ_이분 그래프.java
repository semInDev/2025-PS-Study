import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] nodes = new int[V + 1];
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            boolean isAllBiGraph = true;
            for (int i = 1; i <= V; i++) {
                if (nodes[i] == 0) {
                    boolean isBi = bfs(graph, nodes, i);
                    if (!isBi) {
                        isAllBiGraph = false;
                        break;
                    }
                }
            }

            if (isAllBiGraph) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean bfs(List<List<Integer>> graph, int[] nodes, int idx) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);
        nodes[idx] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            List<Integer> adjNodes = graph.get(now);
            for (int adj : adjNodes) {
                if (nodes[adj] == 0) {
                    nodes[adj] = nodes[now] * -1;
                    queue.offer(adj);
                }
                if (nodes[adj] == nodes[now]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
