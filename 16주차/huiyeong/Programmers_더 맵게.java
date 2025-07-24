import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }
        
         while (pq.size() >= 2 && pq.peek() < K) {
            int first = pq.poll();    
            int second = pq.poll();  

            int mixed = first + (second * 2);
            pq.offer(mixed);      
            answer++;
        }

        if (pq.peek() < K) {
            return -1;
        }

        return answer;
    }
}
