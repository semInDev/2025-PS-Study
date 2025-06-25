import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String[] splitted = number.split("");
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0;i<splitted.length;i++) {
            int num = Integer.parseInt(splitted[i]);
            while (!stack.isEmpty() && k > 0 && stack.peek() < num) {
                stack.pop();
                k--;
            }
            stack.push(num);
        }

        while (k-- > 0) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int num : stack) {
            sb.append(num);
        }

        return sb.reverse().toString();
    }
}
