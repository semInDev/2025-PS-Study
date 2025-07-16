import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() >= M) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        List<String> words = new ArrayList<>(map.keySet());

        words.sort((o1, o2) -> {
            int freq1 = map.get(o1);
            int freq2 = map.get(o2);

            if (freq1 != freq2) {
                return freq2 - freq1;
            } else if (o1.length() != o2.length()) {
                return o2.length() - o1.length();
            } else {
                return o1.compareTo(o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append('\n');
        }
        System.out.print(sb);
    }
}
