import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N개의 레벨, 레벨을 클리어 할떄마다 점수가 주어진다.
 * 쉬운 레벨이 어려운 레벨보다 점수를 많이 받는 경우 특정 레벨 점수 감소
 */
public class Main {
    static int N;
    static int[] arr;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N - 1; i > 0; i--) {
            if (arr[i] <= arr[i - 1]) {
                count += (arr[i - 1] - arr[i] + 1);
                arr[i - 1] = arr[i] - 1;
            }
        }

        System.out.println(count);
    }
}
