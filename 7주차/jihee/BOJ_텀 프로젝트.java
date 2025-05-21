import java.io.*;
import java.io.IOException;
import java.util.*;


public class Main {

    static int[] arr;
    static boolean[] visited, finished;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }
            System.out.println(N - count);
        }
    }

    public static void dfs(int cur) {

        if (visited[cur]) {
            finished[cur] = true;
            count++;
        } else {
            visited[cur] = true;
        }

        if (!finished[arr[cur]]) {
            dfs(arr[cur]);
        }

        visited[cur] = false;
        finished[cur] = true;
    }

}