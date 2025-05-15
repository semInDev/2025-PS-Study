import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long total = 0;
        long max = 0;

        for (int i = 0; i < N; i++) {
            long count = Long.parseLong(br.readLine());
            total += count;
            if (count > max) {
                max = count;
            }
        }

        if (max > total - max) {
            System.out.println(2 * max - total);
        } else {
            System.out.println(total % 2);
        }
    }
}
