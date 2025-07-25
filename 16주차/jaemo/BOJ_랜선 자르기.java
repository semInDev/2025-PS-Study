import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long min = 1;
        long max = arr[k - 1];
        long answer = 0;
        while (min <= max) {
            long median = (min + max) / 2;

            long sum = 0;
            for (int i = 0; i < k; i++) {
                sum += arr[i] / median;
            }

            if (sum < n) {
                max = median - 1;
            } else {
                min = median + 1;
                answer = median;
            }
        }

        System.out.println(answer);
    }
}
