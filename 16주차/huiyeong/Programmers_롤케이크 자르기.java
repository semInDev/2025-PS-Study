import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> r = new HashMap<>();
        Set<Integer> l = new HashSet<>();

        for (int t : topping) {
            r.put(t, r.getOrDefault(t, 0) + 1);
        }
        
        for (int i = 0; i < topping.length; i++) {
            int t = topping[i];
            l.add(t);

            r.put(t, r.get(t) - 1);
            if (r.get(t) == 0) {
                r.remove(t);
            }

            if (l.size() == r.size()) {
                answer++;
            }
        }

        return answer;
    }
}
