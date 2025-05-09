import java.util.*;
class Solution {
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
         for (int i = 0; i < 5; i++) {
            String[] place = places[i];
            boolean b = true;

            for (int x = 0; x < 5 && b; x++) {
                for (int y = 0; y < 5 && b; y++) {
                    if (place[x].charAt(y) == 'P') {
                        if (!BFS(place, x, y)) {
                            b = false;
                        }
                    }
                }
            }
            answer[i] = b ? 1 : 0;
        }
        return answer;
    }

    private static boolean BFS(String[] place, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                int distance = Math.abs(nx - x) + Math.abs(ny - y);

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visited[nx][ny] || distance > 2)
                    continue;

                visited[nx][ny] = true;

                char ch = place[nx].charAt(ny);
                if (ch == 'P') return false;
                if (ch == 'O') q.offer(new int[]{nx, ny});
            }
        }

        return true;
    }
}
