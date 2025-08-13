import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long answer = 0;
        long min = 1;
        long max = times[times.length-1] * (long) n;
        while (min <= max) {
            long median = (min+max)/2;
            long total = 0;
            for (int i=0;i<times.length;i++) {
                total += median/times[i];
            }
            
            if (total < n) {
                min = median+1;
            } else {
                max = median-1;
                answer = median;
            }
        }
        
        return min;
    }
}
