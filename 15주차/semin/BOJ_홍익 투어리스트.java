import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        TreeSet<Integer> set = new TreeSet<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int point = Integer.parseInt(st.nextToken());
            if (point == 1) {
                set.add(i);
            }
        }

        int cur = 1;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int pos = Integer.parseInt(st.nextToken());
                if (set.contains(pos)) {
                    set.remove(pos);
                } else {
                    set.add(pos);
                }
            } else if (cmd == 2) {
                int x = Integer.parseInt(st.nextToken());
                cur = (cur + (x - 1)) % N + 1;
            } else if (cmd == 3) {
                Integer nextSpot = set.ceiling(cur);
                if (nextSpot != null) {
                    int dist = nextSpot - cur;
                    sb.append(dist).append("\n");
                } else {
                    nextSpot = set.ceiling(1);
                    if (nextSpot != null) {
                        int dist = N - cur + nextSpot;
                        sb.append(dist).append("\n");
                    } else {
                        sb.append(-1).append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
