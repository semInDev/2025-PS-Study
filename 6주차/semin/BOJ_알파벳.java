import java.io.*;
import java.util.*;

public class Main {
    static int row = 0;
    static int col = 0;
    static int result = 0;
    static char[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashSet<Character> alphabets;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        graph = new char[row][col];
        alphabets = new HashSet<>();

        for (int i = 0; i < row; i++) {
            String input = br.readLine();
            for (int j = 0; j < col; j++) {
                graph[i][j] = input.charAt(j);
            }
        }
      
        back(0, 0, 1);
        System.out.println(result);
    }
    static void back(int x, int y, int count) {
        result = Math.max(result, count);
        alphabets.add(graph[x][y]);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                if (!alphabets.contains(graph[nx][ny])) {
                    back(nx, ny, count + 1);
                }
            }
        }
        alphabets.remove(graph[x][y]);
    }
}
