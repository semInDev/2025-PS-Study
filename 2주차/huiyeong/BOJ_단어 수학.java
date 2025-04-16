import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        String[] str = new String[N];
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
            int len = str[i].length();
            for (int j = 0; j < len; j++) {
                char c = str[i].charAt(j);
                int power = (int) Math.pow(10, len - j - 1);
                map.put(c, map.getOrDefault(c, 0) + power);
            }
        }

        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> o2.getValue() - o1.getValue());

        int k = 9;
        for (Map.Entry<Character, Integer> entry : entries) {
            answer += entry.getValue() * k;
            k--;
        }

        System.out.println(answer);
    }
}
