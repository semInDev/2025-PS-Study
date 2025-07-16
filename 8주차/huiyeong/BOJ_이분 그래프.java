import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            color = new int[V + 1];
            boolean visited = true;

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    if (!BFS(i)) {
                        visited = false;
                        break;
                    }
                }
            }
            if (visited) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    
    private static boolean BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph[curr]) {
                if (color[next] == 0) {
                    color[next] = -color[curr]; 
                    queue.add(next);
                } else if (color[next] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
}
