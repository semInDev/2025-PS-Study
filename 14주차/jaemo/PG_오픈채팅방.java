import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> users = new HashMap<>();
        Queue<UserAction> log = new LinkedList<>();
        for (int i=0;i<record.length;i++) {
            String[] words = record[i].split(" ");
            String action = words[0];
            String uid = words[1];
            if (action.equals("Enter")) {
                String nickname = words[2];
                users.put(uid, nickname);
                log.offer(new UserAction(uid, action));
            } else if (action.equals("Leave")) {
                log.offer(new UserAction(uid, action));
            } else {
                String nickname = words[2];
                users.put(uid, nickname);
            }
        }
        
        String[] answer = new String[log.size()];
        int idx = 0;
        while (!log.isEmpty()) {
            UserAction userAction = log.poll();
            String nickname = users.get(userAction.uid);
            answer[idx] = String.format("%s님이 %s", nickname, userAction.action.equals("Enter") ? "들어왔습니다." : "나갔습니다.");
            idx++;
        }
        
        return answer;
    }
}

class UserAction {
    String uid, action;
    
    public UserAction(String uid, String action) {
        this.uid = uid;
        this.action = action;
    }
}
