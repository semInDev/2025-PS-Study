import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[][] map;
    static boolean IS_END = false;
    static int H_LINE_NUM = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선
        M = Integer.parseInt(st.nextToken()); // 가로선
        H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치 개수

        map = new int[H + 1][N + 3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = H_LINE_NUM;
            map[a][b + 1] = H_LINE_NUM;
            H_LINE_NUM++;
        }

        for (int i = 0; i <= 3; i++) {
            dfs(i, 0, 0);
            if (IS_END) {
                System.out.println(i);
                System.exit(0);
            }
        }
        System.out.println(-1);
    }

    public static void dfs(int lineCnt, int depth, int sr) {
        if (IS_END) {
            return;
        }
        if (depth == lineCnt) {
            check();
            return;
        }

        for (int r = sr; r <= H; r++) {
            for (int c = 1; c <= N; c++) {
                if (c - 1 < 0) {
                    continue;
                }
                if (map[r][c] > 0 || map[r][c + 1] > 0) {
                    continue;
                }

                map[r][c] = H_LINE_NUM;
                map[r][c + 1] = H_LINE_NUM;
                H_LINE_NUM++;
                dfs(lineCnt, depth + 1, r);
                map[r][c] = 0;
                map[r][c + 1] = 0;
                H_LINE_NUM--;
            }
        }
    }

    public static void check() {
        int cnt = 0;
        for (int lineNum = 1; lineNum <= N; lineNum++) {
            int c = lineNum;
            for (int r = 1; r <= H; r++) {
                if (map[r][c] > 0) {
                    int hLineNum = map[r][c];
                    if (c - 1 > 0 && map[r][c - 1] == hLineNum) {
                        c = c - 1;
                        continue;
                    }
                    if (c + 1 <= N && map[r][c + 1] == hLineNum) {
                        c = c + 1;
                    }
                }
            }
            if (lineNum == c) {
                cnt++;
            }
        }
        if (cnt == N) {
            IS_END = true;
        }
    }
}

// 두 가로선은 연속하거나 서로 인접 X

// H 만큼의 가로선을 추가한 뒤, 사다리 게임을 실행하여 i번 세로선의 결과가 i번이 나오도록 하는 최소 경우의 수 구하기
