import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                int target = arr[i];
                if (sum == target) {
                    if (l != i && r != i) {
                        answer++;
                        break;
                    } else if (l == i) {
                        l++;
                    } else {
                        r--;
                    }
                    continue;
                }
                if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        System.out.println(answer);
    }
}
