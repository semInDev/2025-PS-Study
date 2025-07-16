import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int K, V, E;
    static ArrayList<Integer>[] list;
    static int[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= K; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new int[V + 1]; // 0:방문x, 1:groupA, 2:groupB
            list = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) list[i] = new ArrayList();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            for (int i = 1; i <= V; i++) {
                if (visited[i] == 0) {
                    dfs(i, 1);
                }
            }

            boolean flag = true;

            for (int i = 1; i <= V; i++) {
                for (int j : list[i]) {
                    if (visited[j] == visited[i]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void dfs(int x, int type) {

        visited[x] = type;
        for (int i = 0; i < list[x].size(); i++) {
            int nx = list[x].get(i);
            if (visited[nx] == 0) {
                dfs(nx, 3 - type);
            }
        }

    }
}