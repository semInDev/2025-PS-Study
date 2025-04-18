import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');
        
        int len = s.length();
        for (int j=0; j<len; j++) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i<len; i++) {
                if (!stack.isEmpty() && stack.peek() == map.get(s.charAt(i))) {
                    stack.pop();
                }else {
                    stack.push(s.charAt(i));
                }
            }
            if (stack.isEmpty())
                answer++;
            s = s.substring(1) + s.charAt(0);
        }
    
        return answer;
    }  
}
