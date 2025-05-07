import java.io.*;
import java.util.*;

public class Main {

    static int[][] gear = new int[4][8];
    static int[] direction = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = input.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int cmd = 0; cmd < k; cmd++) {
            Arrays.fill(direction, 0);
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken()) - 1;
            direction[startIdx] = Integer.parseInt(st.nextToken());

            // 왼쪽 톱니 확인
            for (int i = startIdx - 1; i >= 0; i--) {
                if (gear[i + 1][6] != gear[i][2]) direction[i] = -direction[i + 1];
                else break;
            }

            // 오른쪽 톱니 확인
            for (int i = startIdx + 1; i < 4; i++) {
                if (gear[i][6] != gear[i - 1][2]) direction[i] = -direction[i - 1];
                else break;
            }

            // 회전
            for (int i = 0; i < 4; i++) {
                rotate(i, direction[i]);
            }
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += (1 << i) * gear[i][0];
        }

        System.out.println(result);
    }

    static void rotate(int idx, int dir) {
        if (dir == 0) return;
        if (dir == 1) { // 시계 방향 회전
            int temp = gear[idx][7];
            for (int i = 7; i > 0; i--) {
                gear[idx][i] = gear[idx][i - 1];
            }
            gear[idx][0] = temp;
        } else { // 반시계 방향 회전
            int temp = gear[idx][0];
            for (int i = 0; i < 7; i++) {
                gear[idx][i] = gear[idx][i + 1];
            }
            gear[idx][7] = temp;
        }
    }
}
