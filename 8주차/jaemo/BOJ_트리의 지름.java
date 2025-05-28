import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parentNode = Integer.parseInt(st.nextToken());
            int childNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(parentNode).add(new Edge(null, childNode, weight));
            graph.get(childNode).add(new Edge(parentNode, null, weight));
        }

        for (int i = 1; i <= n; i++) {
            List<Edge> edges = graph.get(i);
            if (hasNotChild(edges)) {
                boolean[] visited = new boolean[n + 1];
                visited[i] = true;
                dfs(graph, visited, i, 0);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(List<List<Edge>> graph, boolean[] visited, Integer node, int length) {
        if ((hasNotChild(graph.get(node)) && length > 0) || (node == 1 && graph.get(1).size() == 1)) {
            answer = Math.max(answer, length);
            return;
        }

        List<Edge> edges = graph.get(node);
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.parent != null && !visited[edge.parent]) {
                visited[edge.parent] = true;
                dfs(graph, visited, edge.parent, length + edge.weight);
                visited[edge.parent] = false;
            }
            if (edge.child != null && !visited[edge.child]) {
                visited[edge.child] = true;
                dfs(graph, visited, edge.child, length + edge.weight);
                visited[edge.child] = false;
            }
        }
    }

    private static boolean hasNotChild(List<Edge> edges) {
        for (Edge edge : edges) {
            if (edge.child != null) {
                return false;
            }
        }
        return true;
    }
}

class Edge {
    Integer parent, child;
    int weight;

    public Edge(Integer parent, Integer child, int weight) {
        this.parent = parent;
        this.child = child;
        this.weight = weight;
    }
}

// 자식 노드가 없는 노드부터 자식 노드가 없는 노드까지 탐색
// 이진 트리가 아니기 때문에, 루트 노드의 자식이 하나일 때도 고려해야 함
