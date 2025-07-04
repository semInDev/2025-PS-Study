import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
    
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i=0; i<want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0)+1);
        }
        
        if (match(wantMap, map)) answer++;
        
        for (int i = 10; i < discount.length; i++) {
            map.put(discount[i - 10], map.get(discount[i - 10]) - 1);
            if (map.get(discount[i - 10]) == 0) map.remove(discount[i - 10]);
            
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
            if (match(wantMap, map)) answer++;
        }

        return answer;
    }
    private boolean match(Map<String, Integer> wantMap, Map<String, Integer> map) {
        for (String item : wantMap.keySet()) {
            if (map.getOrDefault(item, 0) != wantMap.get(item)) {
                return false;
            }
        }
        return true;
    }
}
