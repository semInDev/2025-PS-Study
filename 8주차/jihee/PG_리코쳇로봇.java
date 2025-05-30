import java.util.*;

class Solution {
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                    break;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int count = current[2];

            // 목표 지점에 도달한 경우
            if (board[x].charAt(y) == 'G') {
                return count;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x;
                int ny = y;

                // 장애물이나 벽에 부딪힐 때까지 이동
                while (true) {
                    int tx = nx + dx[dir];
                    int ty = ny + dy[dir];

                    // 보드 범위를 벗어나거나 장애물을 만난 경우
                    if (tx < 0 || tx >= n || ty < 0 || ty >= m || board[tx].charAt(ty) == 'D') {
                        break;
                    }

                    nx = tx;
                    ny = ty;
                }

                // 이동한 위치가 아직 방문하지 않은 경우
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, count + 1});
                }
            }
        }

        return -1;
    }
}