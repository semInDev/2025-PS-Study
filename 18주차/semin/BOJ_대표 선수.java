import java.io.*;
import java.util.*;

public class Main {
    static int n, m, cnt, en, ans = Integer.MAX_VALUE;
    static int[] chk = new int[1005];
    static List<int[]> a = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                a.add(new int[]{num, i});
            }
        }

        a.sort(Comparator.comparingInt(o -> o[0]));

        for (int stIdx = 0; stIdx < n * m; stIdx++) {
            while (cnt < n && en < n * m) {
                int team = a.get(en)[1];
                if (chk[team] == 0) cnt++;
                chk[team]++;
                en++;
            }

            if (cnt != n) break;

            int diff = a.get(en - 1)[0] - a.get(stIdx)[0];
            ans = Math.min(ans, diff);

            int team = a.get(stIdx)[1];
            chk[team]--;
            if (chk[team] == 0) cnt--;
        }

        System.out.println(ans);
    }
}

