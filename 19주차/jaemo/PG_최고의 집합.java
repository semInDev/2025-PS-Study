import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s/n == 0) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, s/n);
        int remain = s%n;
        for (int i=0;i<remain;i++) {
            answer[i]++;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}
