import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] player = new int[N];
        boolean[] isCheck = new boolean[MAX];
        int[] score = new int[MAX];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            player[i] = Integer.parseInt(st.nextToken());
            isCheck[player[i]] = true;
        }
        for (int i = 0; i < N; i++) {
            int num = player[i];
            for (int j = num * 2; j < MAX; j += num) {
                if (isCheck[j]) {
                    score[num]++;
                    score[j]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[player[i]]).append(" ");
        }
        System.out.println(sb);
    }
}
