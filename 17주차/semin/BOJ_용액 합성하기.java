import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] a = new int[100005];
    static int ans = (int) 1e9 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int stIdx = 0, enIdx = n - 1;
        while (stIdx < enIdx) {
            int val = a[stIdx] + a[enIdx];
            if (Math.abs(val) < Math.abs(ans)) ans = val;
            if (val < 0) stIdx++;
            else enIdx--;
        }

        System.out.println(ans);
    }
}
