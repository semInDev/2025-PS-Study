import java.util.*;
import java.time.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
        
        Deque<OngoingTask> ongoingTasks = new ArrayDeque<>();
        for (int i=0;i<plans.length-1;i++) {
            String[] now = plans[i];
            String[] next = plans[i+1];
            
            LocalTime nowStartTime = convertToLocalTime(now[1]);
            LocalTime nextStartTime = convertToLocalTime(next[1]);
            int nowMinute = nowStartTime.getHour()*60+nowStartTime.getMinute();
            int nextMinute = nextStartTime.getHour()*60+nextStartTime.getMinute();
            int diffTime = Math.abs(nowMinute-nextMinute);
            int nowPlaytime = Integer.parseInt(now[2]);
            
            // 두 시간 차이가 현재 과제의 playtime보다 클 경우, 남는 시간 동안 진행중이던 과제 처리
            if (diffTime >= nowPlaytime) {
                answer.add(now[0]);
                int remainTime = diffTime - nowPlaytime;
                while (remainTime > 0 && !ongoingTasks.isEmpty()) {
                    if (remainTime < ongoingTasks.peek().remainPlaytime) {
                        OngoingTask prev = ongoingTasks.peek();
                        prev.remainPlaytime -= remainTime;
                        remainTime = 0;
                    } else {
                        OngoingTask prev = ongoingTasks.pop();
                        remainTime -= prev.remainPlaytime;
                        answer.add(prev.name);
                    }
                }
            } else {
                ongoingTasks.push(new OngoingTask(now[0], nowPlaytime-diffTime));
            }
        }
        
        answer.add(plans[plans.length-1][0]);
        while (!ongoingTasks.isEmpty()) {
            answer.add(ongoingTasks.pop().name);
        }
        
        return answer.stream().map(s -> s).toArray(String[]::new);
    }
    
    public LocalTime convertToLocalTime(String string) {
        String[] splitArr = string.split(":");
        return LocalTime.of(Integer.parseInt(splitArr[0]), Integer.parseInt(splitArr[1]));
    }
}

class OngoingTask {
    String name;
    int remainPlaytime;
    
    public OngoingTask(String name, int remainPlaytime) {
        this.name = name;
        this.remainPlaytime = remainPlaytime;
    }
}

// 진행 중이던 과제 복원을 위해 stack 사용
