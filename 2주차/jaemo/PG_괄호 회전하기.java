import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int x=0;x<s.length();x++) {
            // 큐 초기화
            Queue<String> queue = new LinkedList<>();
            for (int i=0;i<s.length();i++) {
                queue.add(String.valueOf(s.charAt(i)));
            }
            
            // 왼쪽으로 회전
            for (int i=0;i<x;i++) {
                queue.add(queue.poll());
            }
            
            // 올바른 괄호 문자열 확인
            Deque<String> stack = new ArrayDeque<>();
            stack.push(queue.poll());
            while (!queue.isEmpty()) {
                String now = queue.poll();
                if (stack.isEmpty() && (now.equals(")") || now.equals("}") || now.equals("]"))) {
                    stack.push(now);
                    break;
                }
                if (now.equals(")")) {
                    if (stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        stack.push(now);
                    }
                } else if (now.equals("}")) {
                    if (stack.peek().equals("{")) {
                        stack.pop();
                    } else {
                        stack.push(now);
                    }
                } else if (now.equals("]")) {
                    if (stack.peek().equals("[")) {
                        stack.pop();
                    } else {
                        stack.push(now);
                    }
                } else {
                    stack.push(now);
                }
            }
            
            // 올바른 문자열이면 answer 증가
            if (stack.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}
