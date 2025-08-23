import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Hi팀 N
 * ARC팀 M
 * 경기 횟수 : NxM
 * 완탐 -> 각 인원마다 승리 계산하기
 * 이분탐색 ->
 */
public class Main {
    static int N, M;
    static int[] hi, arc;

    public static void main(String[] args) throws IOException {
        long draw = 0;
        long hiWin = 0;
        long arcWin = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hi = new int[N];
        arc = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hi[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hi);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arc[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arc);
        //hi 기준 탐색
        for (int num : hi) {
            //나보다 크거나 같은것들 중 왼쪽
            int low = lowerBound(num);
            //나보다 큰것들 중 가장 왼쪽
            int up = upperBound(num);
            //중복값
            int cal = up - low;
            draw += cal;
            hiWin += up - cal;
            arcWin += M - up;
        }
        System.out.println(hiWin + " " + arcWin + " " + draw);
    }

    private static int lowerBound(int num) {
        int ans = M;
        int start = 0;
        int end = M - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arc[mid] >= num) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    private static int upperBound(int num) {
        int ans = M;
        int start = 0;
        int end = M - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arc[mid] > num) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

}
