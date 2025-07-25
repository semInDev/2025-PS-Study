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

        int left = 0;
        int right = n - 1;
        int[] answer = new int[2];
        answer[0] = arr[0];
        answer[1] = arr[n - 1];
        while (left < n && right > -1 && left + 1 < right) {
            int sum = 0;
            if (Math.abs(arr[left + 1] + arr[right]) < Math.abs(arr[left] + arr[right - 1])) {
                sum = arr[left + 1] + arr[right];
                left++;
                if (Math.abs(answer[0] + answer[1]) > Math.abs(sum)) {
                    answer[0] = arr[left];
                    answer[1] = arr[right];
                }
            } else {
                sum = arr[left] + arr[right - 1];
                right--;
                if (Math.abs(answer[0] + answer[1]) > Math.abs(sum)) {
                    answer[0] = arr[left];
                    answer[1] = arr[right];
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
