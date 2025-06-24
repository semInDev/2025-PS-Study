import java.util.*;

class Solution {
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,1,-1};
    
    public int solution(String dirs) {
        Set<Dir> set = new HashSet<>();
        Point now = new Point(5, 5);
        for (int i=0;i<dirs.length();i++) {
            char command = dirs.charAt(i);
            int moveDir = 0;
            if (command == 'U') {
                moveDir = 0;
            } else if (command == 'D') {
                moveDir = 1;
            } else if (command == 'R') {
                moveDir = 2;
            } else if (command == 'L') {
                moveDir = 3;
            }
            
            int nr = now.r + dRow[moveDir];
            int nc = now.c + dCol[moveDir];
            if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                continue;
            }
            Point next = new Point(nr, nc);
            set.add(new Dir(now, next));
            now = next;
        }
        
        return set.size();
    }
}

class Dir {
    Point start;
    Point end;
    
    public Dir(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public boolean equals(Object o) {
        Dir other = (Dir) o;
        return (start.equals(other.start) && end.equals(other.end)) || (start.equals(other.end) && end.equals(other.start));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start) + Objects.hash(end);
    }
}

class Point {
    int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    @Override
    public boolean equals(Object o) {
        Point other = (Point) o;
        return r == other.r && c == other.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}
