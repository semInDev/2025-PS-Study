import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for (int move : moves) {
            int col = move - 1;
            for (int i = 0; i < board.length; i++) {

                if (board[i][col] == 0) {
                    continue;
                }

                if (!stack.isEmpty() && stack.peek() == board[i][col]) {
                    stack.pop();
                    board[i][col] = 0;
                    answer += 2;
                    break;
                } else {
                    stack.push(board[i][col]);
                    board[i][col] = 0;
                    break;
                }
            }
        }

        return answer;
    }
}
