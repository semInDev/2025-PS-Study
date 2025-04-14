import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Meeting> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(s, e));
        }

        int lastEnd = -1;
        int answer = 0;
        while (!pq.isEmpty()) {
            Meeting now = pq.poll();
            if (now.s >= lastEnd) {
                answer++;
                lastEnd = now.e;
            }
        }

        System.out.println(answer);
    }
}

class Meeting implements Comparable<Meeting> {
    int s, e;

    public Meeting(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Meeting o) {
        if (this.e == o.e) {
            return this.s - o.s;
        }
        return this.e - o.e;
    }
}
