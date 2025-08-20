import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] list;
    static int N;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, w));
            list[b].add(new Node(a, w));
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    private static void dfs(int cur, int res) {
        for (Node node : list[cur]) {

            if (!visited[node.idx]) {
                visited[node.idx] = true;
                dfs(node.idx, res + node.weight);
            }
        }

        if (answer < res) {
            answer = res;
        }
    }

    static class Node {
        int idx;
        int weight;

        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
