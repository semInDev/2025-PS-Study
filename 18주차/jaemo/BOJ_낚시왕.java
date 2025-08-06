import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static int totalSize = 0;
    static int[][] board;
    static int[] dRow = {0, -1, 1, 0, 0};
    static int[] dCol = {0, 0, 0, 1, -1};
    static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[R + 1][C + 1];
        sharks.add(new Shark(0, 0, 0, 0, 0, 0, false)); // index 0 dummy

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (d == 1 || d == 2) s %= (2 * (R - 1));
            else s %= (2 * (C - 1));

            sharks.add(new Shark(i, r, c, s, d, z, false));
            board[r][c] = i;
        }

        for (int fisher = 1; fisher <= C; fisher++) {
            catchNearestShark(fisher);
            moveAllSharks();
        }

        System.out.println(totalSize);
    }

    static void catchNearestShark(int col) {
        for (int row = 1; row <= R; row++) {
            if (board[row][col] > 0) {
                int sharkId = board[row][col];
                Shark shark = sharks.get(sharkId);
                shark.isDead = true;
                totalSize += shark.z;
                board[row][col] = 0;
                return;
            }
        }
    }

    static void moveAllSharks() {
        int[][] newBoard = new int[R + 1][C + 1];

        for (int i = 1; i < sharks.size(); i++) {
            Shark shark = sharks.get(i);
            if (shark.isDead) continue;

            int r = shark.r;
            int c = shark.c;
            board[r][c] = 0;

            for (int move = 0; move < shark.s; move++) {
                int nr = shark.r + dRow[shark.d];
                int nc = shark.c + dCol[shark.d];

                if (nr < 1 || nr > R || nc < 1 || nc > C) {
                    shark.reverseDirection();
                    nr = shark.r + dRow[shark.d];
                    nc = shark.c + dCol[shark.d];
                }

                shark.r = nr;
                shark.c = nc;
            }

            int existingSharkId = newBoard[shark.r][shark.c];
            if (existingSharkId == 0) {
                newBoard[shark.r][shark.c] = shark.num;
            } else {
                Shark other = sharks.get(existingSharkId);
                if (other.z > shark.z) {
                    shark.isDead = true;
                } else {
                    other.isDead = true;
                    newBoard[shark.r][shark.c] = shark.num;
                }
            }
        }

        board = newBoard;
    }
}

class Shark {
    int num;
    int r, c, s, d, z;
    boolean isDead;

    public Shark(int num, int r, int c, int s, int d, int z, boolean isDead) {
        this.num = num;
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
        this.isDead = isDead;
    }

    public void reverseDirection() {
        if (d == 1) d = 2;
        else if (d == 2) d = 1;
        else if (d == 3) d = 4;
        else if (d == 4) d = 3;
    }
}
