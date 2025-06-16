import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int left = 1;
        int right = K;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            long count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (count < K) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
