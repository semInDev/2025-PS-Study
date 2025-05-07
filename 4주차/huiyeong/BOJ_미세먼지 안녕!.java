import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T, up=-1, down=-1;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][0] == -1) {
                    if (up == -1) up = i;
                    else down = i;
                }
            }
        }

        while (T-- > 0) {
            number1();
            number2();
        }

        int result = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (arr[i][j] > 0)
                    result += arr[i][j];

        System.out.println(result);
    }

    static void number1() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    int k = arr[i][j] / 5;
                    int count = 0;
                    for (int d =0; d<4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && arr[nx][ny] != -1) {
                            temp[nx][ny] += k;
                            count++;
                        }
                    }
                    temp[i][j] += arr[i][j] - k * count;
                }
            }
        }

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (arr[i][j] != -1)
                    arr[i][j] = temp[i][j];
    }

    static void number2() {
        int t = up;
        int b = down;

        for (int i = t - 1; i > 0; i--)
            arr[i][0] = arr[i - 1][0];
        for (int j = 0; j < C - 1; j++)
            arr[0][j] = arr[0][j + 1];
        for (int i = 0; i < t; i++)
            arr[i][C - 1] = arr[i + 1][C - 1];
        for (int j = C - 1; j > 1; j--)
            arr[t][j] = arr[t][j - 1];
        arr[t][1] = 0;

        for (int i = b + 1; i < R - 1; i++)
            arr[i][0] = arr[i + 1][0];
        for (int j = 0; j < C - 1; j++)
            arr[R - 1][j] = arr[R - 1][j + 1];
        for (int i = R - 1; i > b; i--)
            arr[i][C - 1] = arr[i - 1][C - 1];
        for (int j = C - 1; j > 1; j--)
            arr[b][j] = arr[b][j - 1];
        arr[b][1] = 0;
    }
}
