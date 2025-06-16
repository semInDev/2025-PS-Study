import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readline());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int left = 1;
        int right = k;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = mid;
            }
        }
      
        System.out.println(result);
    }
}
