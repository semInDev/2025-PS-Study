import java.util.*;

class Solution {
    static int[] dRow = {1, 0, -1, 0};
    static int[] dCol = {0, 1, 0, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int t=0;t<5;t++) {
            String[] place = places[t];
            String[][] map = new String[place.length][place.length];
            for (int i=0;i<place.length;i++) {
                map[i] = place[i].split("");
            }
            
            boolean isPass = true;
            for (int r=0;r<5;r++) {
                for (int c=0;c<5;c++) {
                    if (map[r][c].equals("P")) {
                        if (!check(r, c, map)) {
                            isPass = false;
                            r = 5;
                            c = 5;
                        }
                    }
                }
            }
            
            if (isPass) {
                answer[t] = 1;
            } else {
                answer[t] = 0;
            }
        }
        
        return answer;
    }
    
    public boolean check(int r, int c, String[][] map) {
        Queue<Table> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.offer(new Table(r, c, 0));
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            Table now = queue.poll();
            if (now.d >= 2) {
                continue;
            }
            for (int d=0;d<4;d++) {
                int nr = now.r + dRow[d];
                int nc = now.c + dCol[d];
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || map[nr][nc].equals("X") || visited[nr][nc]) {
                    continue;
                }
                if (map[nr][nc].equals("O")) {
                    queue.add(new Table(nr, nc, now.d+1));
                    visited[nr][nc] = true;
                }
                if (map[nr][nc].equals("P")) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

class Table {
    int r, c, d;
    
    public Table(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

// 이미 체크한 자리는 다시 체크 X
