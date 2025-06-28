import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<moves.length; i++) {
            for (int j=0; j<board.length; j++) {
                if (board[j][moves[i]-1] != 0) {
                    int k = board[j][moves[i]-1];
        
                    if (!stack.isEmpty() && stack.peek() == k) {
                        stack.pop();
                        answer+=2;
                    }
                    else 
                        stack.push(k);
                    board[j][moves[i]-1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}
/*
[0,0,0,0,0],
[0,0,1,0,3],
[0,2,5,0,1],
[4,2,4,4,2],
[3,5,1,3,1]

[1,5,3,5,1,2,1,4]
*/
