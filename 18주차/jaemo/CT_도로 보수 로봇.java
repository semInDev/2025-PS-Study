import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = scanner.nextInt();
            
        }
        
        Arrays.sort(positions);

        int answer = 0;
        int min = 1;
        int max = positions[N-1];
        while (min <= max) {
            int median = (min+max)/2;
            int cnt = 0;
            int cur = positions[0];
            for (int i=0;i<N;i++) {
                int position = positions[i];
                if (position < cur) {
                    continue;
                }
                cur = position + median;
                cnt++;
            }
            if (cnt > K) {
                min = median+1;
            } else {
                max = median-1;
                answer = median;
            }
        }

        System.out.println(answer);
    }
}
