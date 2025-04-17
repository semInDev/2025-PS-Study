import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] alphaWeight = new int[26]; // A~Z
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int len = word.length();
            for (int j = 0; j < len; j++) {
                int idx = word.charAt(j) - 'A';
                alphaWeight[idx] += Math.pow(10, len - j - 1);
            }
        }

        // 가중치를 기준으로 정렬
        Integer[] weights = new Integer[26];
        for (int i = 0; i < 26; i++) weights[i] = alphaWeight[i];
        Arrays.sort(weights, Collections.reverseOrder());

        int num = 9;
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (weights[i] == 0) break;
            sum += weights[i] * num--;
        }

        System.out.println(sum);
    }
}
