import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            q.add(i + 1);
        }

        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                int person = q.poll();
                q.add(person);
            }
            list.add(q.poll());
        }
        System.out.print("<");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(">");
    }
}
