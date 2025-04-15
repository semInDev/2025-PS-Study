import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });

        int end = 0;
        int answer = 0;
        for (int i=0; i<N; i++) {
            if (end <= arr[i][0]) {
                end = arr[i][1];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
