import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            List<List<Integer>> parentList = new ArrayList<>();
            for (int i = 0; i <= N; i++) parentList.add(new ArrayList<>());

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parentList.get(child).add(parent);
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Set<Integer> aAncestors = new HashSet<>();
            aAncestors.add(a);
            while (!parentList.get(a).isEmpty()) {
                a = parentList.get(a).get(0);
                aAncestors.add(a);
            }

            int lca = b;
            while (!parentList.get(lca).isEmpty()) {
                if (aAncestors.contains(lca)) break;
                lca = parentList.get(lca).get(0);
            }

            sb.append(lca).append("\n");
        }
        System.out.println(sb);
    }
}
