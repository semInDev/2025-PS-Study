import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[6];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                if (col + 1 >= m) {
                    continue;
                }
                moveToEast(dice, board, row, col);
                col++;
            }
            if (command == 2) {
                if (col - 1 < 0) {
                    continue;
                }
                moveToWest(dice, board, row, col);
                col--;
            }
            if (command == 3) {
                if (row - 1 < 0) {
                    continue;
                }
                moveToNorth(dice, board, row, col);
                row--;
            }
            if (command == 4) {
                if (row + 1 >= n) {
                    continue;
                }
                moveToSouth(dice, board, row, col);
                row++;
            }

            System.out.println(dice[5]);
        }
    }

    public static void moveToEast(int[] dice, int[][] board, int r, int c) {
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[2];
        dice[2] = tmp;
        copy(dice, board, r, c + 1);
    }

    public static void moveToWest(int[] dice, int[][] board, int r, int c) {
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[5];
        dice[5] = dice[4];
        dice[4] = tmp;
        copy(dice, board, r, c - 1);
    }

    public static void moveToNorth(int[] dice, int[][] board, int r, int c) {
        int tmp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[3];
        dice[3] = tmp;
        copy(dice, board, r - 1, c);
    }

    public static void moveToSouth(int[] dice, int[][] board, int r, int c) {
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[1];
        dice[1] = tmp;
        copy(dice, board, r + 1, c);
    }

    private static void copy(int[] dice, int[][] board, int r, int c) {
        if (board[r][c] == 0) {
            board[r][c] = dice[0];
        } else {
            dice[0] = board[r][c];
            board[r][c] = 0;
        }
    }
}
