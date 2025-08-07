package BOJ.백트래킹2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 키의 개수N
 * 퀘스트M
 * 퀘스트당 스킬 K
 * 키를 먼저 고르고, 그 키로 퀘스트가 몇개 깨지는지 체크
 */

public class BOJ_단풍잎이야기 {
    static int[][] skills;
    static int N, M, K;
    static boolean[] visited;
    static int[] arr;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        skills = new int[M][K];
        visited = new boolean[2 * N + 1];
        arr = new int[N];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                skills[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0);

        System.out.println(answer);

    }

    // 2N + 1까지 스킬 탐색,
    private static void recur(int cur, int cnt) {
        if (cur > 2 * N) {
            return;
        }

        if (cnt == N) {
            int result = 0;
            for (int i = 0; i < M; i++) {
                boolean flag = true;
                for (int j = 0; j < K; j++) {
                    if (!visited[skills[i][j]]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    result++;
                }
            }

            answer = Math.max(answer, result);
        }

        visited[cur] = true;
        recur(cur + 1, cnt + 1);
        visited[cur] = false;
        recur(cur + 1, cnt);

    }
}
