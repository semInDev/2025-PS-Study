import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;    
        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        double last = -1; 

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];
            
            if (start >= last) {
                answer++; 
                last = end - 0.5;
            }
        }
        return answer;
    }
}
