package BOJ.백트래킹1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 N개, i번쨰 문제 난이도 Ai
 * 문제는 두문제 이상 -> 난이도 합은 L보다 크거나 같고, R보다 작거나 같다.
 * 가장 어려운 문제와 - 쉬운 문제 난이도 >= X
 * 문제를 고르는 방법의 수
 * <p>
 * cur-> 문제수 체크
 * visited -> 중복x ?
 */

public class BOJ_캠프준비 {
    static int N, L, R, X;
    static int[] arr;
    static int answer;
    static int[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 2; i <= N; i++) {
            visited = new int[i];
            recur(0, 0, i);
        }


        System.out.println(answer);
    }

    private static void recur(int cur, int start, int count) {

        if (cur == count) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            int sum = 0;
            for (int i = 0; i < visited.length; i++) {
                sum += visited[i];
                max = Math.max(max, visited[i]);
                min = Math.min(min, visited[i]);
            }
            if (sum >= L && sum <= R && (max - min) >= X) {
                answer++;
            }
            return;
        }


        for (int i = start; i < N; i++) {
            visited[cur] = arr[i];
            recur(cur + 1, i + 1, count);
        }
    }
}
