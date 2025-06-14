import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] board = new int[n];
        dfs(n, board, 0);
        System.out.println(answer);
    }

    public static void dfs(int n, int[] board, int depth) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;
            if (isPossible(board, depth)) {
                dfs(n, board, depth + 1);
            }
        }
    }

    public static boolean isPossible(int[] board, int col) {
        for (int i = 0; i < col; i++) {
            if (board[i] == board[col]) {
                return false;
            }
            if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
