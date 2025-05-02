import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    static ArrayList<String> results = new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i : course) {
            for (String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);

                dfs(0,i,"",arr);
            }

            if (!map.isEmpty()) {
                int max = Collections.max(map.values().stream().toList());
                if (max > 1) {
                    for (String key : map.keySet()) {
                        if (map.get(key)==max) {
                        	results.add(key);
                        }
                    }
                }
            }
            map.clear();
        }

        Collections.sort(results);
        answer = new String[results.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = results.get(i);
        }

        return answer;
    }
    static void dfs(int depth, int i, String course, char[] chars) {
        if (course.length() == i) {
            if (map.containsKey(course)) {
            	map.put(course, map.get(course)+1);
            }else{
            	map.put(course, 1);
            }
            return;
        }

        if (depth < chars.length) {
        	dfs(depth + 1, i, course + chars[depth], chars);
            dfs(depth + 1, i, course, chars);
        }
    }
}