import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
    
        int curEnd = targets[0][1];
        answer++;
        
        for (int[] target : targets) {
            if (target[0] >= curEnd) {
                curEnd = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}
