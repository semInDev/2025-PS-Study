import java.util.*;

class Solution {
    static int[] dRow = {1,0,-1,0};
    static int[] dCol = {0,1,0,-1};
    
    public int solution(String[] board) {
        int[][] dist = new int[board.length][board[0].length()];
        for (int i=0;i<dist.length;i++) {
            Arrays.fill(dist[i], -1);
        }
        
        Point start = null;
        Point end = null;
        for (int i=0;i<dist.length;i++) {
            for (int j=0;j<dist[i].length;j++) {
                if (board[i].charAt(j) == 'R') {
                    start = new Point(i, j);
                }
                if (board[i].charAt(j) == 'G') {
                    end = new Point(i, j);
                }
            }
        }
        
        bfs(dist, board, start, end);
        for (int i=0;i<dist.length;i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
        
        return dist[end.r][end.c];
    }
    
    public void bfs(int[][] dist, String[] board, Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        dist[start.r][start.c] = 0;
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d=0;d<4;d++) {
                int nr = now.r;
                int nc = now.c;
                while (true) {
                    nr += dRow[d];
                    nc += dCol[d];
                    if (nr < 0 || nr >= dist.length || nc < 0 || nc >= dist[0].length || board[nr].charAt(nc) == 'D') {
                        nr -= dRow[d];
                        nc -= dCol[d];
                        break;
                    }
                }
                if (dist[nr][nc] == -1 && board[nr].charAt(nc) != 'D' && board[nr].charAt(nc) != 'R') {
                    queue.offer(new Point(nr, nc));
                    dist[nr][nc] = dist[now.r][now.c] + 1;
                }
            }
        }
    }
}

class Point {
    int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
