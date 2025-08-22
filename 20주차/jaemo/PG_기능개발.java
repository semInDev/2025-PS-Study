import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<n;i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            int period = (100-progress)/speed;
            if ((100-progress)%speed > 0) {
                period++;
            }
            queue.offer(period);
        }
        
        List<Integer> deployCounts = new ArrayList<>();
        while (!queue.isEmpty()) {
            int target = queue.poll();
            int deployCnt = 1;
            while (!queue.isEmpty() && queue.peek() <= target) {
                queue.poll();
                deployCnt++;
            }
            deployCounts.add(deployCnt);
        }
        
        int[] answer = new int[deployCounts.size()];
        for (int i=0;i<deployCounts.size();i++) {
            answer[i] = deployCounts.get(i);
        }
        
        return answer;
    }
}
