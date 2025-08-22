import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] book = new int[book_time.length][2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < book_time.length; i++) {
            book[i][0] = calculate(book_time[i][0]);
            book[i][1] = calculate(book_time[i][1]) + 10; 
        }
        
        Arrays.sort(book, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        for (int[] time : book) {
            if (!pq.isEmpty() && pq.peek() <= time[0]) {
                pq.poll();
            }
            pq.offer(time[1]);
        }
        
        return pq.size();
    }
    private int calculate(String s) {
        String[] str = s.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }
}
