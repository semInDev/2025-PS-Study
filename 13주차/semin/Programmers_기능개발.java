import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<Integer>();
        int[] day = new int[progresses.length];

        for (int i = 0; i < day.length; i++) {
            if ((100 - progresses[i]) % speeds[i] == 0) {
                day[i] = (100 - progresses[i]) / speeds[i];
            } else {
                day[i] = ((100 - progresses[i]) / speeds[i]) + 1;
            }
            q.add(day[i]);
        }

        List<Integer> list = new ArrayList<Integer>();
        int cnt = 0;
        int before = q.peek();
        while (!q.isEmpty()) {
            if (q.peek() <= before) {
                q.poll();
                cnt++;
            } else {
                list.add(cnt);
                cnt = 1;
                before = q.poll();
            }
        }
        list.add(cnt);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
