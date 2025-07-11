import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr; // 기간,금액
    static int[] dp; //cur,수익

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 2][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 기간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 금액
        }

        int[] dp = new int[N + 2]; //i+1일에 정산
        int max = 0;

        for (int i = 1; i <= N + 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int day = arr[i][0] + i;

            if (day <= N + 1) {
                // 현재 날짜에 기록된 금액 ,지금까지 누적금액 + 현재 날짜 금액 중 더 큰 금액 저장
                dp[day] = Math.max(dp[day], max + arr[i][1]);
            }

        }

        System.out.println(max);
    }
}