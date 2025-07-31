class Main {
    public int solution(int[][] board){
        int answer = 0;

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == 2){
                    x1 = i;
                    y1 = j;
                }
                if(board[i][j] == 3){
                    x2 = i;
                    y2 = j;
                }
            }
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int d1 = 0;
        int d2 = 0;

        while(true){
            int nx1 = dx[d1] + x1;
            int ny1 = dy[d1] + y1;

            if (nx1 < 0 || nx1 > board.length-1 || ny1 < 0 || ny1 > board[0].length-1 || board[nx1][ny1] == 1){
                d1 = (d1+1) % 4;
            }
            else {
                x1 = nx1;
                y1 = ny1;
            }

            int nx2 = dx[d2] + x2;
            int ny2 = dy[d2] + y2;

            if (nx2 < 0 || nx2 > board.length-1 || ny2 < 0 || ny2 > board[0].length-1 || board[nx2][ny2] == 1){
                d2 = (d2+1) % 4;
            }
            else {
                x2 = nx2;
                y2 = ny2;
            }
            answer++;
            if (x1 == x2 && y1 == y2){
                return answer;
            }
            if (answer >= 10000) return 0;
        }

    }

    public static void main(String[] args){
        Main T = new Main();
        int[][] arr1 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution(arr1));
        int[][] arr2 = {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution(arr2));
    }
}
