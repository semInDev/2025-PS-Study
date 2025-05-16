import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        Arrays.sort(plans, Comparator.comparingInt(o -> calculate(o[1])));

        List<String> result = new ArrayList<>();
        Stack<String[]> stack = new Stack<>();
        int cur = 0; 
        
        for (String[] plan : plans) {
            String sub = plan[0]; 
            int time = calculate(plan[1]); 
            int duration = Integer.parseInt(plan[2]);

            while (!stack.isEmpty()) {
                String[] str = stack.pop();
                int remainingTime = Integer.parseInt(str[1]);

                if (cur + remainingTime <= time) {
                    cur += remainingTime;
                    result.add(str[0]);
                } else {
                    stack.push(new String[]{str[0], String.valueOf(remainingTime - (time - cur))});
                    break;
                }
            }

            cur = time;
            stack.push(new String[]{sub, String.valueOf(duration)});
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop()[0]);
        }

        return result.toArray(new String[0]);
    }

    private int calculate(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
