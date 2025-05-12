import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            max = Math.max(max, num);
        }

        if (max >= (sum - max)) {
            sb.append(max - (sum - max));
        } else {
            sb.append(sum % 2);
        }

        System.out.println(sb);
    }
}
