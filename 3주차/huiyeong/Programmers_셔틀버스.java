import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i=0; i<timetable.length; i++) {
            String[] str = timetable[i].split(":");
            queue.add(Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]));
        }
        
        int end = 9 * 60 + (n - 1) * t; 
        int last = -1; 
        for (int i = 0; i < n; i++) {
            int time = 9 * 60 + i * t;
            int count = 0; 
            while (!queue.isEmpty() && queue.peek() <= time && count < m) {
                last = queue.poll(); 
                count++;
            }
            if (time == end && count < m) {
                return formatTime(time);
            }
        }

        if (last == -1 || last > end) {
            last = end; 
        } else {
            last--; 
        }
        return formatTime(last);
    }
    private String formatTime(int time) {
        int hour = time / 60;
        int minute = time % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
