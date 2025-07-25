import java.util.*;

class Solution {
    public int solution(String name) {
        int len = name.length();
        int answer = 0;
        int idx = 0;
        int moveCnt = len-1;
        for (int i=0;i<len;i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
            
            idx = i+1;
            while (idx < len && name.charAt(idx) == 'A') {
                idx++;
            }
            
            moveCnt = Math.min(moveCnt, i*2+len-idx);
            moveCnt = Math.min(moveCnt, (len-idx)*2+i);
        }
        
        return answer + moveCnt;
    }
}
