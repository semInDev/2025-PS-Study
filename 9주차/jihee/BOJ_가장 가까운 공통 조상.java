import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 두 노드에서 가장가까운 공통조상 찾기
 */
public class Main {
    static int T, N;
    static int[] arr;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];

            visited = new boolean[N + 1];
            parents = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); //부모
                int b = Integer.parseInt(st.nextToken()); //자식
                arr[b] = a;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            while (a != 0) {
                visited[a] = true;
                a = arr[a];
            }

            while (b != 0 && !visited[b]) {
                b = arr[b];
            }
            System.out.println(b);

        }

    }
}

