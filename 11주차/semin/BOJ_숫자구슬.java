import java.util.*;
import java.io.*;

public class Main { 
    public static int n, m, result;
    public static int[] groupArr, beads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        beads = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
        }

        groupArr = new int[m];

        binarySearch();
        groupCheck();
        sb.append(result).append("\n");

        for (int i = 0; i < m; i++) {
            sb.append(groupArr[i]).append(" ");
        }

        System.out.print(sb);
    }

    // 그룹 개수 맞추기(0 개수 그룹 없애기)
    public static void groupCheck() {
        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (groupArr[i] == 0) {
                idx = i - 1;
                groupArr[i]++;
                while (true) {
                    if (groupArr[idx] != 1) {
                        break;
                    }
                    idx--;
                }
                groupArr[idx]--;
            }
        }
    }

    public static void binarySearch() {
        int start = 1;
        int end = 100 * n;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (possible(mid)) {
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }
    }

    public static boolean possible(int mid) {
        int groupSum = 0;
        int groupIdx = 0;
        int[] groupNum = new int[m];

        for (int i = 0; i < n; i++) {
            if (mid < beads[i]) {
                return false;
            }
            if (groupSum + beads[i] <= mid) {
                groupSum += beads[i];
            } else {
                groupSum = beads[i];
                groupIdx++;
            }
            if (groupIdx == m) {
                return false;
            }
            groupNum[groupIdx]++;
        }

        groupArr = groupNum;
        return true;
    }
}
