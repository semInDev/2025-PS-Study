import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            maxLen = Math.max(maxLen, words[i].length());
        }

        Arrays.sort(words, (a, b) -> b.length() - a.length());

        char[][] chars = new char[n][maxLen];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int idx = maxLen - word.length();
            int idxOfWord = 0;
            for (int j = 0; j < idx; j++) {
                chars[i][j] = '.';
            }
            for (int j = idx; j < maxLen; j++) {
                chars[i][j] = word.charAt(idxOfWord);
                idxOfWord++;
            }
        }

        Integer[] scores = new Integer[26];
        Arrays.fill(scores, 0);

        int weight = 1;
        for (int i = maxLen - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                char ch = chars[j][i];
                if (ch == '.') {
                    continue;
                }
                scores[ch - 'A'] += weight;
            }
            weight *= 10;
        }

        Arrays.sort(scores, Collections.reverseOrder());

        int answer = 0;
        int num = 9;
        for (Integer score : scores) {
            if (score == 0) {
                break;
            }
            answer += score * num;
            num--;
            if (num < 0) {
                break;
            }
        }

        System.out.println(answer);
    }
}
