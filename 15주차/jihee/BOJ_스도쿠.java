import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
    int y;
    int x;

    public Pair(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int[][] arr = new int[9][9];
    static ArrayList<Pair> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) list.add(new Pair(i, j));
            }
        }
        recur(0);

    }


    static boolean check(int y, int x, int num) {
        int y_start = (y / 3) * 3;
        int x_start = (x / 3) * 3;

        for (int i = 0; i < 9; i++) {

            if (arr[y][i] == num) return false;

            if (arr[i][x] == num) return false;

            if (arr[y_start + i / 3][x_start + i % 3] == num) return false;
        }
        return true;
    }

    static void recur(int cur) {
        if (cur == list.size()) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        int y = list.get(cur).y;
        int x = list.get(cur).x;
        for (int j = 1; j <= 9; j++) {
            if (check(y, x, j)) {
                arr[y][x] = j;
                recur(cur + 1);
                arr[y][x] = 0;
            }
        }
    }
}
