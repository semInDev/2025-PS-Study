import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Arrays.sort(plans, (a, b) -> toMinutes(a[1]) - toMinutes(b[1]));

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> stack = new Stack<>();

        for (String[] plan : plans) {
            String name = plan[0];
            int time = toMinutes(plan[1]);
            int duration = Integer.parseInt(plan[2]);
            queue.offer(new String[]{name, String.valueOf(time), String.valueOf(duration)});
        }

        int idx = 0;
        int nowTime = 0;

        while (!queue.isEmpty()) {
            String[] cur = queue.poll();
            String name = cur[0];
            int startTime = Integer.parseInt(cur[1]);
            int duration = Integer.parseInt(cur[2]);

            if (queue.isEmpty()) {
                answer[idx++] = name;
                nowTime = startTime + duration;
                continue;
            }

            String[] next = queue.peek();
            int nextStartTime = Integer.parseInt(next[1]);

            if (startTime + duration > nextStartTime) {
                int left = startTime + duration - nextStartTime;
                stack.push(new String[]{name, String.valueOf(left)});
                nowTime = nextStartTime;
            } else {
                answer[idx++] = name;
                nowTime = startTime + duration;

                while (!stack.isEmpty()) {
                    String[] paused = stack.pop();
                    String pausedName = paused[0];
                    int pausedTime = Integer.parseInt(paused[1]);

                    if (nowTime + pausedTime > nextStartTime) {
                        int remain = nowTime + pausedTime - nextStartTime;
                        stack.push(new String[]{pausedName, String.valueOf(remain)});
                        nowTime = nextStartTime;
                        break;
                    } else {
                        answer[idx++] = pausedName;
                        nowTime += pausedTime;
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            answer[idx++] = stack.pop()[0];
        }

        return answer;
    }

    private int toMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
