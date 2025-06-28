import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<moves.length; i++){
            int current = 0;
            for(int j=0; j<board.length; j++){
                if(board[j][moves[i]-1]!=0){
                    current = board[j][moves[i]-1];
                    board[j][moves[i]-1] = 0;
                    break;
                }
            }
            if(current!=0){
                if(stack.isEmpty()){
                    stack.push(current);
                }else{
                    if(stack.peek()==current){
                        stack.pop();
                        answer+=2;
                    }else{
                        stack.push(current);
                    }
                }
            }
        }
        return answer;
    }
}
