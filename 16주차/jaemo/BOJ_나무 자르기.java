import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int goalLen = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int low = 0;
        int high = arr[n - 1];
        while (low < high) {
            int mid = (low + high) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] < mid) {
                    continue;
                }
                sum += arr[i] - mid;
            }
            if (sum < goalLen) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(low - 1);
    }
}
