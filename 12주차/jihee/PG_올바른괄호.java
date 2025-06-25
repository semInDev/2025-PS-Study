import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        ArrayList<String> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.size() == 0) {
                if (s.charAt(i) == '(') {
                    stack.add("(");
                } else {
                    return false;
                }
            } else {
                if (s.charAt(i) == '(') {
                    stack.add("(");
                } else {
                    stack.remove(stack.size() - 1);
                }
            }
        }
        if (stack.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}