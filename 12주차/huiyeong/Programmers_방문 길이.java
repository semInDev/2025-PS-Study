import java.util.*;

class Solution {
    public int solution(String dirs) {
        Set<String> visited = new HashSet<>();
        int x = 0, y = 0;

        for (char d : dirs.toCharArray()) {
            int nx = x, ny = y;

            if (d == 'U') ny++;
            else if (d == 'D') ny--;
            else if (d == 'R') nx++;
            else if (d == 'L') nx--;

            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
            
            String path1 = x + "," + y + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + x + "," + y;

            visited.add(path1);
            visited.add(path2);

            x = nx;
            y = ny;
        }

        return visited.size() / 2;
    }
}
