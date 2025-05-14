import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dPos = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Position> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            queue.add(new Position(p, 0));
            visited.add(p);
        }

        long answer = 0;
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            for (int d = 0; d < 2; d++) {
                int np = now.p + dPos[d];
                if (visited.contains(np)) {
                    continue;
                }
                k--;
                answer += now.w + 1;
                visited.add(np);
                if (k == 0) {
                    System.out.println(answer);
                    System.exit(0);
                }
                queue.add(new Position(np, now.w + 1));
            }
        }
    }
}

class Position {
    int p, w;

    public Position(int p, int w) {
        this.p = p;
        this.w = w;
    }
}
