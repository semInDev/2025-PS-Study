import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> result = new ArrayList<>();
        Map<String, String> Id = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            if (temp[0].equals("Enter")) {
                Id.put(temp[1], temp[2]);
                result.add(temp[1] + "님이 들어왔습니다.");
            } else if (temp[0].equals("Leave")) {
                result.add(temp[1] + "님이 나갔습니다.");
            } else if (temp[0].equals("Change")) {
                Id.replace(temp[1], temp[2]);
            }
        }

        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            int idx = result.get(i).indexOf("님");
            String id = result.get(i).substring(0, idx);
            answer[i] = Id.get(id) + result.get(i).substring(idx);
        }

        return answer;
    }
}
