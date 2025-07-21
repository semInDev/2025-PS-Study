import java.io.*;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first != o.first) {
                return o.first - this.first;
            }
            return o.second - this.second;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] a = new Pair[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            a[i] = new Pair(first, second);
        }

        Arrays.sort(a);

        TreeMap<Integer, Integer> teamMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int key = -a[i].second + 1;
            Integer found = teamMap.ceilingKey(key);

            if (found == null) {
                teamMap.put(-1, teamMap.getOrDefault(-1, 0) + 1);
            } else {
                int count = teamMap.get(found);
                if (count == 1) {
                    teamMap.remove(found);
                } else {
                    teamMap.put(found, count - 1);
                }
                teamMap.put(found - 1, teamMap.getOrDefault(found - 1, 0) + 1);
            }
        }

        int answer = 0;
        for (int count : teamMap.values()) {
            answer += count;
        }

        System.out.println(answer);
    }
}
