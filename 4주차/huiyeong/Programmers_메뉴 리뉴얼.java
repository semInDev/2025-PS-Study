import java.util.*;
class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int len : course) {
            Map<String, Integer> map = new HashMap<>();
            int max = 0;

            for (String order : orders) {
                char[] c = order.toCharArray();
                Arrays.sort(c); 
                List<String> list = combine(c, len);

                for (String s : list) {
                    map.put(s, map.getOrDefault(s, 0) + 1); 
                    max = Math.max(max, map.get(s)); 
                }
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max && max >= 2) {
                    answer.add(entry.getKey());
                }
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private List<String> combine(char[] c, int len) {
        List<String> result = new ArrayList<>();
        int n = c.length;
        int total = 1 << n;

        for (int i = 0; i < total; i++) {
            String s = "";
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    s += c[j]; 
                }
            }
            if (s.length() == len) {
                result.add(s);
            }
        }

        return result;
    }
}
