import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        while (queue.size() >= 2) {
            int a = queue.poll();
            int b = queue.poll();
            int sum = a + b;
            answer += sum;
            queue.add(sum);
        }

        System.out.println(answer);
    }
}
