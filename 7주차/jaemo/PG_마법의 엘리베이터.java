import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        while (storey > 0) {
            int remain = storey % 10;
            if (remain > 5) {
                int abs = Math.abs(10 - remain);
                answer += abs;
                storey = storey / 10 + 1;
            } else if (remain == 5) {
                int abs = remain;
                answer += abs;
                if (storey/10%10 >= 5) {
                    storey = storey / 10 + 1;
                } else {
                    storey = storey / 10;
                }
            } else {
                int abs = remain;
                answer += abs;
                storey = storey / 10;
            }
        }
        
        return answer;
    }
}
