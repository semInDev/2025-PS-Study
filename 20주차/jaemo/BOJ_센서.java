import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sensorPoints = new int[n];
        for (int i = 0; i < n; i++) {
            sensorPoints[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensorPoints);

        // 좌표 중복 제거
        List<Integer> uniq = new ArrayList<>();
        for (int x : sensorPoints) {
            if (uniq.isEmpty() || uniq.get(uniq.size() - 1) != x) uniq.add(x);
        }

        int m = uniq.size();
        if (k >= m) { // 집중국 개수가 유니크 센서 개수 이상이면 0
            System.out.println(0);
            return;
        }

        // 인접 간격 계산
        int[] gaps = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            gaps[i] = uniq.get(i + 1) - uniq.get(i);
        }

        // 간격 정렬 후 가장 큰 (K-1)개를 제외한 합
        Arrays.sort(gaps);
        int answer = 0;
        for (int i = 0; i < m - k; i++) { // (m-1) - (k-1) == (m-k)
            answer += gaps[i];
        }

        System.out.println(answer);
    }
}
