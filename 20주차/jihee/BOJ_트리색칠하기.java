import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] tree;
    static int[] color, treeColor;
    static boolean[] visited;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        color = new int[N];
        tree = new ArrayList[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            tree[a].add(b);
            tree[b].add(a);
        }


        treeColor = new int[N];
        visited = new boolean[N];

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        visited[0] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (treeColor[cur] != color[cur]) {
                treeColor[cur] = color[cur];
                count++;
            }

            for (int i = 0; i < tree[cur].size(); i++) {
                int idx = tree[cur].get(i);
                if (visited[idx]) continue;
                treeColor[idx] = treeColor[cur];
                visited[idx] = true;
                queue.add(idx);
            }
        }

        return count;
    }
}
