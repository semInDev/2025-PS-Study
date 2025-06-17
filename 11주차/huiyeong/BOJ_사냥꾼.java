import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] gun = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            gun[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(gun);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (y > L) continue; 

            int left = 0;
            int right = M - 1;
            while (left <= right) {
                int mid = (right+left)/2;
                int dis = Math.abs(gun[mid] - x) + y;
                if (dis <= L) {
                    answer++;
                    break;
                }
                if (gun[mid] < x) left = mid + 1;
                else right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
