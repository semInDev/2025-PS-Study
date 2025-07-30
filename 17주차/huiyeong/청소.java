import java.util.*;
class Main {
    public int[] solution(int[][] board, int k){
        int[] answer = new int[2];

        int i = 0;
        int j = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int d = 1;
        while(k>0) {
            k--;
            int nx = i + dx[d];
            int ny = j + dy[d];

            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board.length || board[nx][ny] == 1){
                d = (d + 1) % 4;
                continue;
            }
            i = nx;
            j = ny;
        }

        answer[0] = i;
        answer[1] = j;

        return answer;
    }

    public static void main(String[] args){
        Main T = new Main();
        int[][] arr1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr1, 10)));
        int[][] arr2 = {{0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr2, 20)));
        int[][] arr3 = {{0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr3, 25)));

    }
}
