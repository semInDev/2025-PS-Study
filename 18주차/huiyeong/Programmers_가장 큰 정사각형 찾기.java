import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && i > 0 && j > 0) {
                    board[i][j] = Math.min(Math.min(board[i-1][j], board[i][j-1]),board[i-1][j-1]) + 1;
                }
                if (board[i][j] > max) max = board[i][j];
            }
        }
        return max * max;
    }
}
