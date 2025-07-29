import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            result[i] = upperBound(cards, target) - lowerBound(cards, target);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();
    }

    private static int lowerBound(int[] cards, int target) {
        int left = 0;
        int right = cards.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int upperBound(int[] cards, int target) {
        int left = 0;
        int right = cards.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (cards[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
