import java.util.*;

class Solution {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length();
        Character[][] map = new Character[n][m];
        int RX = 0;
        int RY = 0;
        int GX = 0;
        int GY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    RX = i;
                    RY = j;
                }
                if (map[i][j] == 'G') {
                    GX = i;
                    GY = j;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.offer(new int[]{RX, RY, 0}); 
        visited[RX][RY] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int count = current[2];

            if (x == GX && y == GY) {
                return count;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x;
                int ny = y;

                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];
                    
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 'D') {
                        nx -= dx[dir];
                        ny -= dy[dir];
                        break;
                    }
                }
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, count + 1});
                }
            }
        }

        return -1;
    }
}
