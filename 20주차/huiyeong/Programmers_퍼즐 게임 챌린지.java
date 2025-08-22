import java.util.*;
class Solution {
    static int time_prev;
    public int solution(int[] diffs, int[] times, long limit) {
        int left = diffs[0];
        int right = Arrays.stream(diffs).max().getAsInt();
        int answer = right;
        time_prev = diffs[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid, diffs, times, limit)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private boolean isPossible(int level, int[] diffs, int[] times, long limit) {
        long remainingTime = limit;

        for (int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i]) {
                remainingTime -= times[i];
            } else {
                remainingTime -= (times[i] + time_prev) * (diffs[i] - level) + times[i];
            }
            time_prev = times[i];
            if (remainingTime < 0) return false;
        }

        return remainingTime >= 0;
    }
}
