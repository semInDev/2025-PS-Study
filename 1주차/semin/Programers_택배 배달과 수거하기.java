import java.util.*;

public class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        ArrayDeque<Integer> stackD = new ArrayDeque<>();
        ArrayDeque<Integer> stackP = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<deliveries[i]; j++) {
            	stackD.addLast(i+1);
            }
            for (int j=0; j<pickups[i]; j++) {
            	stackP.addLast(i+1);
            }
        }
        
        while (!stackD.isEmpty() && !stackP.isEmpty()) {
            int maxD = stackD.peekLast();
            int maxP = stackP.peekLast();
            for (int i=0; i<cap; i++) {
                if (!stackD.isEmpty()) stackD.pollLast();
                if (!stackP.isEmpty()) stackP.pollLast();
            }
            answer += Math.max(maxD, maxP)*2;
        }

        while (!stackD.isEmpty()) {
            int maxD = stackD.peekLast();
            for (int i=0; i<cap; i++) {
                if (!stackD.isEmpty()) stackD.pollLast();
            }
            answer += maxD*2;
        }

        while (!stackP.isEmpty()) {
            int maxP = stackP.peekLast();
            for (int i=0; i<cap; i++) {
                if (!stackP.isEmpty()) stackP.pollLast();
            }
            answer += maxP*2;
        }
        return answer;
    }
}