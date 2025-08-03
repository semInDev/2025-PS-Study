import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        int low = max;
        int high = 10000 * 100000;
        int answer = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            int count = 1;
            int withdrawalAmount = mid;
            for (int i = 0; i < n; i++) {
                withdrawalAmount -= arr[i];
                if (withdrawalAmount < 0) {
                    count++;
                    withdrawalAmount = mid - arr[i];
                }
            }

            if (count <= m) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
