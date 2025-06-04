import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        int first, second;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());

            list.get(first).add(second);
            list.get(second).add(first);
        }

        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (list.get(i).size() == 1) cnt++;
        }

        double answer = (double)W / cnt;
        System.out.print(answer);
    }
}
