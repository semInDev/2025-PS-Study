import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] tree;
    static int[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        people = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                tree[parent].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            people[idx] += w;
        }

        DFS(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(people[i] + " ");
        }
    }

    static void DFS(int current) {
        for (int child : tree[current]) {
            people[child] += people[current]; 
            DFS(child);
        }
    }
}
