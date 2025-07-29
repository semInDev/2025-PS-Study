import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] p = new int[100002];
    static int[] score = new int[100002];
    static List<Integer>[] child = new ArrayList[100002];

    static void dfs(int cur) {
        if (p[cur] != -1)
            score[cur] += score[p[cur]];
        for (int nxt : child[cur])
            dfs(nxt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            child[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            if (p[i] != -1) {
                child[p[i]].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            score[idx] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
