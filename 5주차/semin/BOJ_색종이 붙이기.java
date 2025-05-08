import java.io.*;
import java.util.*;

public class Main {

    static int minCount = 100;
    static int[][] board = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int paperCount = 0;
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) paperCount++;
            }
        }

        dfs(0, 0, 0, new int[]{0, 5, 5, 5, 5, 5}, paperCount);
        System.out.println((minCount == 100) ? -1 : minCount);
    }

    static void dfs(int usedCount, int row, int col, int[] papersLeft, int remaining) {
        if (remaining == 0) {
            minCount = Math.min(minCount, usedCount);
            return;
        }

        if (row >= 10) return;

        if (board[row][col] == 0) {
            if (col < 9) dfs(usedCount, row, col + 1, papersLeft, remaining);
            else dfs(usedCount, row + 1, 0, papersLeft, remaining);
            return;
        }

        for (int size = 1; size <= 5; size++) {
            if (row + size > 10 || col + size > 10 || !canPlacePaper(row, col, size)) break;
            if (papersLeft[size] > 0) {
                placePaper(row, col, size);
                papersLeft[size]--;
                if (col < 9) dfs(usedCount + 1, row, col + 1, papersLeft, remaining - size * size);
                else dfs(usedCount + 1, row + 1, 0, papersLeft, remaining - size * size);
                placePaper(row, col, size);
                papersLeft[size]++;
            }
        }
    }

    static boolean canPlacePaper(int row, int col, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void placePaper(int row, int col, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] ^= 1; // 1이면 0으로, 0이면 1로 뒤집는 토글
            }
        }
    }
}
