import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] arr = new int[F + 1];

        if (S == G) {
            System.out.println(0);
        } else {

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(S);
            while (!queue.isEmpty()) {
                int now = queue.poll();

                int up = now + U;
                if (up >= 1 && up <= F && up != S && arr[up] == 0) {
                    arr[up] = arr[now] + 1;
                    queue.offer(up);
                }

                int down = now - D;
                if (down >= 1 && down <= F && down != S && arr[down] == 0) {
                    arr[down] = arr[now] + 1;
                    queue.offer(down);
                }
            }

            if (arr[G] == 0) {
                System.out.println("use the stairs");
            } else {
                System.out.println(arr[G]);
            }
        }
    }
}
