import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dX = {1, 0, -1, 0};
    static int[] dY = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[][] board = new boolean[101][101];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int generations = Integer.parseInt(st.nextToken());

            // 첫 라인
            board[y][x] = true;
            Line firstLine = new Line(x + dX[d], y + dY[d], d, 1);
            board[firstLine.y][firstLine.x] = true;
            Queue<Line> lines = new PriorityQueue<>();
            lines.offer(firstLine);

            // 주어진 세대만큼 반복
            int endX = firstLine.x;
            int endY = firstLine.y;
            for (int j = 0; j < generations; j++) {
                // 이전에 만들어진 라인 개수만큼 반복
                Queue<Line> prevLines = new PriorityQueue<>(lines);
                lines.clear();
                int size = prevLines.size();
                for (int k = 0; k < size && !prevLines.isEmpty(); k++) {
                    Line nowLine = prevLines.poll();
                    int direction;
                    switch (nowLine.d) {
                        case 0:
                            direction = 1;
                            break;
                        case 1:
                            direction = 2;
                            break;
                        case 2:
                            direction = 3;
                            break;
                        default:
                            direction = 0;
                            break;
                    }

                    endX = endX + dX[direction];
                    endY = endY + dY[direction];
                    board[endY][endX] = true;
                    Line newLine = new Line(endX, endY, direction, size - k);
                    lines.offer(newLine);

                    nowLine.g = nowLine.g + size;
                    lines.offer(nowLine);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (hasSquare(board, i, j)) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean hasSquare(boolean[][] board, int x, int y) {
        if (x + 1 > 100 || y + 1 > 100) {
            return false;
        }
        if (!board[x][y]) {
            return false;
        }
        if (!board[x + 1][y]) {
            return false;
        }
        if (!board[x][y + 1]) {
            return false;
        }
        if (!board[x + 1][y + 1]) {
            return false;
        }
        return true;
    }
}

class Line implements Comparable<Line> {
    int x, y, d, g;

    public Line(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }

    @Override
    public int compareTo(Line o) {
        return this.g - o.g;
    }
}
