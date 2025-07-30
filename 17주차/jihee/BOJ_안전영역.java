import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static int[][] map;
    public static boolean[][] map_clone; // map에서 안전영역은 1, 아닌곳은 0을 넣은 배열
    public static int N;
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};
    //dx[], dy[]를 전역변수로 선언해서 사용해야 메모리를 많이많이 줄일 수 있다. 중요!

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N]; // map 배열 초기화


        int max = 0;
        for(int i = 0; i < N; i++) { // 입력된 값 map 배열에 넣고 안전영역 max 높이 찾아놓기
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }


        int answer = 0;
        for(int i = 0; i < max + 1; i++) { // 제일 높은곳까지만 넣어서 확인
            int count = 0;

            //map_clone 초기화
            map_clone = new boolean[N][N];

            //안전 영역 계산값 count[] 배열에 넣기
            for(int j = 0; j < N; j++)
                for(int k = 0; k < N; k++)
                    if(!map_clone[j][k] && map[j][k] >= i) {  // 방문x & 높이 이상
                        count++;
                        safe_dfs(i, j, k);
                    }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);

    }

    public static void safe_dfs(int h, int x, int y) {

        map_clone[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx <= -1 || nx >= N || ny <= -1 || ny >= N) continue;
            if(!map_clone[nx][ny] && map[nx][ny] >= h) {
                safe_dfs(h, x + dx[i], y + dy[i]);
                map_clone[x][y] = true;
            }
        }

    }
}