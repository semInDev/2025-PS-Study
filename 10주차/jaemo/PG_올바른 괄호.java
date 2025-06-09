import java.util.*;

class Solution {
    boolean solution(String s) {
        int lefts = 0;
        int rights = 0;
        
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                lefts++;
            } else {
                rights++;
            }
            
            if (lefts < rights) {
                return false;
            }
        }

        if (lefts != rights) {
            return false;
        }

        return true;
    }
}
