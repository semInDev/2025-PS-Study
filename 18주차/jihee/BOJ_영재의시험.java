import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3개의 연속된 문제의 답은 같지 않게 한다
 */
public class BOJ_영재의시험 {
    static int[] arr;
    static int[] answer;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        answer = new int[10];
        recur(0, 0);

        System.out.println(result);

    }

    private static void recur(int cur, int cnt) {
        if (cur == 10) {
            if (cnt < 5) {
                return;
            }
            if (cnt >= 5) {
                result++;
            }

            return;
        }

        for (int i = 1; i <= 5; i++) {

            if (cur >= 2) {
                if (answer[cur - 2] == i && answer[cur - 1] == i) {
                    continue;
                }
            }

            answer[cur] = i;

            if (arr[cur] == answer[cur]) {
                recur(cur + 1, cnt + 1);
            } else {
                recur(cur + 1, cnt);
            }

        }
    }
}
