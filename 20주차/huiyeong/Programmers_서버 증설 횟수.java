import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int s = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<players.length; i++) {
            s = 0;
            map.put(i, 0);
            for (int j=0; j<k; j++)  {
                if ((i-j)<0) break;
                s += map.get(i-j);
            }
            int temp = players[i] / m;
            if ((temp-s)>=0) {
                answer += temp-s;
                map.put(i, temp-s);
            }
            
        }
        return answer;
    }
}
