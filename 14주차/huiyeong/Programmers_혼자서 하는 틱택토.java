class Solution {
    public int solution(String[] board) {
        int countO = 0;
        int countX = 0;
        boolean winO = false;
        boolean winX = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') countO++;
                else if (c == 'X') countX++;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                if (board[i].charAt(0) == 'O') winO = true;
                if (board[i].charAt(0) == 'X') winX = true;
            }

            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') winO = true;
                if (board[0].charAt(i) == 'X') winX = true;
            }
        }

        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if (board[0].charAt(0) == 'O') winO = true;
            if (board[0].charAt(0) == 'X') winX = true;
        }
        if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
            if (board[0].charAt(2) == 'O') winO = true;
            if (board[0].charAt(2) == 'X') winX = true;
        }

        if (countX > countO || countO - countX > 1) return 0;
        if (winO && winX) return 0; 
        if (winO && countO == countX) return 0;
        if (winX && countO > countX) return 0; 

        return 1;
    }
}
