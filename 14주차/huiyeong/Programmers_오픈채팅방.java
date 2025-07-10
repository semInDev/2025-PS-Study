import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        for (int i=0; i<record.length; i++) {
            String[] str = record[i].split(" ");
            if (str[0].equals("Enter") || str[0].equals("Change")) {
                map.put(str[1], str[2]);
            }
        }
        List<String> list = new ArrayList<>();
        for (int i=0; i<record.length; i++) {
            String[] str = record[i].split(" ");
            if (str[0].equals("Enter")) {
                list.add(map.get(str[1])+"님이 들어왔습니다.");
            }
            else if (str[0].equals("Leave"))
                list.add(map.get(str[1])+"님이 나갔습니다.");
        }
        
        return list.toArray(new String[0]);
    }
}
