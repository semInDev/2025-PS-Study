import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int ans = 0;
        Arrays.sort(targets, Comparator.comparingInt(t -> t[0]));

        int preStart = targets[0][0];
        int preEnd = targets[0][1];
        for (int[] target : targets) {
            if (ans == 0) {
                ans++;
                continue;
            }

            int curStart = targets[0];
            int curEnd = targets[1];

            if (preStart <= curStart && curEnd < preEnd) {
                preStart = Math.max(preStart, curStart);
                preEnd = Math.min(preEnd, curEnd);
            } else {
                preStart = curStart;
                preEnd = curEnd;
                ans++;
            }
        }

        return ans;
    }
}
