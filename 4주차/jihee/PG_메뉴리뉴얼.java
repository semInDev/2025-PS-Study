import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) {
            char[] ch = orders[i].toCharArray();
            Arrays.sort(ch);
            orders[i] = String.valueOf(ch);
        }


        for (int i = 0; i < course.length; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < orders.length; j++) {
                recur(0, 0, course[i], orders[j], new StringBuilder(), map);
            }
            int max = 0;
            for (int vlaue : map.values()) {
                if (vlaue >= 2) {
                    max = Math.max(max, vlaue);
                }
            }

            for (String key : map.keySet()) {
                if(max == map.get(key)){
                    list.add(key);
                }
            }
            Collections.sort(list);
        }

        return list.toArray(new String[0]);
    }

    private static void recur(int cur, int start, int count, String order, StringBuilder sb, HashMap<String, Integer> map) {

        if (cur == count) {
            String tmp = sb.toString();
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            return;
        }


        for (int i = start; i < order.length(); i++) {
            sb.append(order.charAt(i));
            recur(cur + 1, i + 1, count, order, sb,map);
            sb.deleteCharAt(sb.length() - 1);

        }
    }
}