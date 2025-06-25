import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        char current;
        for(int i=0; i<s.length(); i++){
            current = s.charAt(i);
            if(current == '('){
                stack.push('(');
            }else{
                if(stack.isEmpty() == true){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty() == true){
            return true;
        }else{
            return false;
        }
    }
}
