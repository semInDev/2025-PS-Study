import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        int[] alpha = new int[26];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int len = words[i].length();
            for (int j = 0; j < len; j++) {
                char c = words[i].charAt(j);
                alpha[c - 'A'] += Math.pow(10, len - j - 1); // 자릿수만큼 가중치 누적
            }
        }

        // 가중치 높은 순으로 정렬
        Arrays.sort(alpha);

        int num = 9;
        int sum = 0;
        for (int i = 25; i >= 0; i--) {
            if (alpha[i] == 0) break; // 가중치 0이면 더 이상 없음
            sum += alpha[i] * num;
            num--;
        }

        System.out.println(sum);
    }
}