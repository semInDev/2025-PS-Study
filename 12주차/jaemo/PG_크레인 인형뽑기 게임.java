import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        List<Deque<Integer>> screen = new ArrayList<>();
        for (int i=0;i<=board[0].length;i++) {
            screen.add(new ArrayDeque<>());
        }
        
        for (int r=board.length-1;r>=0;r--) {
            for (int c=0;c<board[0].length;c++) {
                if (board[r][c] > 0) {
                    screen.get(c+1).push(board[r][c]);
                }
            }
        }
        
        int answer = 0;
        Deque<Integer> basket = new ArrayDeque<>();
        for (int i=0;i<moves.length;i++) {
            Deque<Integer> screenStack = screen.get(moves[i]);
            if (screenStack.isEmpty()) {
                continue;
            }
            int now = screenStack.pop();
            if (!basket.isEmpty() && basket.peek() == now) {
                basket.pop();
                answer += 2;
            } else {
                basket.push(now);
            }
        }
        
        return answer;
    }
}
