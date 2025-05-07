import java.util.*;
class Solution {
    public int solution(String s) {
        String long_s = s + s;
        int result = 0; 
        for(int i=0;i<s.length();i++){
            Stack<Character> stack = new Stack<>();
            char current;
            boolean tf = true;
            for(int j=i; j<i+s.length(); j++){//회전
                current = long_s.charAt(j);
                if(current=='('||current=='{'||current=='['){
                    stack.push(current);
                }
                else if(current==')'){
                    if(stack.isEmpty() || stack.peek()!='('){
                        tf = false;
                        break;
                    }
                    //Empty아님 && (임!
                    stack.pop();
                }
                else if(current=='}'){
                    if(stack.isEmpty() || stack.peek()!='{'){
                        tf = false;
                        break;
                    }
                    stack.pop();
                }
                else if(current==']'){
                    if(stack.isEmpty() || stack.peek()!='['){
                        tf = false;
                        break;
                    }
                    stack.pop();
                }
            }
            if(tf == true && stack.isEmpty()){
                result++;
            }
        }
        return result;
    }
}