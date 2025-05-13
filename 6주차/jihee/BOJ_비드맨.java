import java.io.BufferedReader;
import java.io.IOException;

/**
 * 서로 다른 종류의 구슬을 깨부셔야 된다.
 * 아무 위치나 2개씩 골라서 2씩 빼기.
 * <p>
 * 1. max > sum -max
 * 2. max <= sum - max
 */
public class Main {
    static int N;
    static long[] X;
    static long max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 가지고 있는 구슬의 종류 N
        X = new long[N];


        long sum = 0;
        for (int i = 0; i < N; i++) {
            X[i] = Long.parseLong(br.readLine());
            sum += X[i];
            max = Math.max(max, X[i]);
        }

        if (max > sum - max) {
            System.out.println(max - (sum - max));
        } else {
            System.out.println(sum % 2);
        }

    }
}
