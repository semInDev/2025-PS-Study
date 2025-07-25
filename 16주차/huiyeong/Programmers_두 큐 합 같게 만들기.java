import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0, sum2 = 0;
        for (int n : queue1) {
            q1.offer(n);
            sum1 += n;
        }
        for (int n : queue2) {
            q2.offer(n);
            sum2 += n;
        }

        long target = sum1 + sum2;
        
        if (target % 2 != 0) return -1;
        target /= 2;

        int limit = (queue1.length + queue2.length) * 3;
        int count = 0;

        while (count <= limit) {
            if (sum1 == target) return count;

            if (sum1 > target) { 
                int val = q1.poll();
                sum1 -= val;
                sum2 += val;
                q2.offer(val);
            } else {
                int val = q2.poll();
                sum2 -= val;
                sum1 += val;
                q1.offer(val);
            }
            count++;
        }

        return -1;
    }
}
