import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<prices.length; i++){
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int temp = stack.pop();
                result[temp] = i - temp; 
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int temp = stack.pop();
            result[temp] = prices.length - 1 - temp; 
        }
        return result;
    }
}
