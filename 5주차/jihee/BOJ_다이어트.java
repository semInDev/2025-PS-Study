import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 식재료 영양합 min 만족하고, 최저비용 찾기
public class Main {
    static int N;
    static int[] min;//단 지 탄 비
    static int[][] arr;// 단 지 탄 비 가격
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] sum;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        min = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            min[i] = Integer.parseInt(st.nextToken());
        }

        arr = new int[N][5];
        visited = new boolean[N];
        sum = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0);
        list.sort(null);
        if (answer != Integer.MAX_VALUE) {
            bw.write(answer + "\n");
            bw.write(list.get(0));
        } else {
            bw.write(-1 + " ");
        }

        bw.write("\n");
        bw.flush();
        bw.close();

    }

    private static void recur(int cur) {

        if (cur == N) {
            int p = 0, f = 0, s = 0, v = 0, c = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    p += arr[i][0];
                    f += arr[i][1];
                    s += arr[i][2];
                    v += arr[i][3];
                    c += arr[i][4];
                }
            }

            if (p < min[0] || f < min[1] || s < min[2] || v < min[3]) {
                return;
            }

            if (c < answer) {
                StringBuilder sb = new StringBuilder();
                answer = c;
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        sb.append((i + 1) + " ");
                    }
                }

                list.clear();
                list.add(sb.toString());

            } else if (c == answer) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        sb.append((i + 1) + " ");
                    }
                }
                list.add(sb.toString());
            }
            return;
        }

        visited[cur] = true;
        recur(cur + 1);
        visited[cur] = false;
        recur(cur + 1);
    }
}