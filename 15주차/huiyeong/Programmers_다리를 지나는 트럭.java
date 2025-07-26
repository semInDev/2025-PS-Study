import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int answer = 0;   
        int cur = 0; 
        int index = 0;         

        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (!bridge.isEmpty()) {
            answer++;
            cur -= bridge.poll();

            if (index < truck_weights.length) {
                int next = truck_weights[index];
                if (cur + next <= weight) {
                    bridge.offer(next);
                    cur += next;
                    index++;
                } else {
                    bridge.offer(0); 
                }
            }
        }

        return answer;
    }
}
