import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다.
//노드의 수 카운트!
public class Main {
    static int N, R, Q;
    static List<Integer>[] list;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());//트리의 정점의 수
        R = Integer.parseInt(st.nextToken()); //루트의 번호
        Q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        list = new ArrayList[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        subTree(R);
        for (int i = 0; i < Q; i++) {
            int a = Integer.parseInt(br.readLine());
            System.out.println(dp[a]);
        }
    }

    private static int subTree(int node) {
        if (dp[node] != 0) {
            return 0;
        }

        dp[node] = 1;

        for (int next : list[node]) {
            dp[node] += subTree(next);
        }

        return dp[node];

    }
}
