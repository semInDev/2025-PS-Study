import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int[] rotate = new int[4];
            rotate[num] = direction;

            for (int j = num - 1; j >= 0; j--) {
                if (arr[j][2] != arr[j + 1][6]) {
                    rotate[j] = -rotate[j + 1];
                } else {
                    break;
                }
            }

            for (int j = num + 1; j < 4; j++) {
                if (arr[j][6] != arr[j - 1][2]) {
                    rotate[j] = -rotate[j - 1];
                } else {
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {
                if (rotate[j] != 0) {
                    direct(arr[j], rotate[j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i][0] == 1) {
                answer += Math.pow(2, i);
            }
        }

        System.out.println(answer);
    }

    private static void direct(int[] arr, int direction) {
        if (direction == 1) {
            int temp = arr[7];
            for (int i = 7; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = temp;
        } else if (direction == -1) {
            int temp = arr[0];
            for (int i = 0; i < 7; i++) {
                arr[i] = arr[i + 1];
            }
            arr[7] = temp;
        }
    }
}
