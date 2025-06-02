import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[10001];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                arr[child] = parent;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            List<Integer> ancestorsOfA = new ArrayList<>();
            List<Integer> ancestorsOfB = new ArrayList<>();
            dfs(arr, ancestorsOfA, a);
            dfs(arr, ancestorsOfB, b);
            ancestorsOfA.retainAll(ancestorsOfB);
            System.out.println(ancestorsOfA.get(0));
        }
    }

    public static void dfs(int[] arr, List<Integer> ancestors, int node) {
        if (node == 0) {
            return;
        }

        ancestors.add(node);
        dfs(arr, ancestors, arr[node]);
    }
}
