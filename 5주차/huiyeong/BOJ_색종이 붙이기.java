import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer=-1;
    static int[][] arr;
    static int[] paper = {5, 5, 5, 5, 5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10][10];
        paper = new int[5];

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0,0);
        System.out.println(answer);
    }

    private static void DFS(int count, int row, int col) {
        if (arr[row][col] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (paper[size - 1] > 0) {
                    method(row, col, size, 0);
                    paper[size - 1]--;
                    DFS(count + 1, row, col + 1);
                    method(row, col, size, 1); // 복원
                    paper[size - 1]++;
                }
            }
        } else {
            DFS(count, row, col + 1);
        }
    }
    static void method(int x, int y, int size, int val) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arr[i][j] = val;
            }
        }
    }
}
