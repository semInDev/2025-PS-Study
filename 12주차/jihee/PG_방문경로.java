class Solution {
    public static boolean visited[][][] = new boolean[11][11][4];
    public static int dx[] = {-1, 0, 0, 1};
    public static int dy[] = {0, -1, 1, 0};

    public int solution(String dirs) {
        int answer = 0;
        int x = 5;
        int y = 5;

        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int dir = 0;

            switch (c) {
                case 'L':
                    dir = 0;
                    break;
                case 'U':
                    dir = 1;
                    break;
                case 'D':
                    dir = 2;
                    break;
                case 'R':
                    dir = 3;
                    break;
                default:
                    break;
            }

            int cx = x + dx[dir];
            int cy = y + dy[dir];

            if (cx >= 0 && cy >= 0 && cx < 11 && cy < 11) {
                if (!visited[cx][cy][3 - dir] && !visited[x][y][dir]) {
                    answer++;
                    visited[cx][cy][3 - dir] = true;
                    visited[x][y][dir] = true;
                }
                x = cx;
                y = cy;
            }
        }
        return answer;
    }
}